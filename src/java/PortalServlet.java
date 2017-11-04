/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import bd.ConnectionFactory;
import beans.Empresa;
import dao.EmpresaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wesley
 */
@WebServlet(urlPatterns = {"/PortalServlet"})
public class PortalServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String nome = (String)session.getAttribute("nome");
        
        try {
        Connection connection = new ConnectionFactory().getConnection();
        EmpresaDao empresaDao = new EmpresaDao();
        
        out.println("<html><head><title>PortalServlet</title></head>");
        out.println("<body><h1>Seja bem-vindo ao Portal, " + nome + "</h1><br/>");
        out.println("<h2>Buscar</h2>");
        out.println("<div><form action=\"BuscarEmpresaServlet\" method=\"POST\"><input type=\"text\" name=\"buscar\"><input type=\"submit\" value=\"Buscar\"></form>");
        out.println("<a href=\"inserir.html\"><input type=\"submit\" value=\"Adicionar Empresa\"></a></div>");
        out.println("<h2>Todas as Empresas</h2>" + empresaDao.listAllTable(empresaDao.select(connection, "SELECT * FROM EMPRESA ORDER BY RAZAO_SOCIAL")));
        out.println("<br/><br/><a href=\"ProcessaLogoutServlet\">Logout</a>");
        out.println("</body></html>");
        connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PortalServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PortalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
