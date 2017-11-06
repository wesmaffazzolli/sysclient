/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.ufpr.tads.web.bd.ConnectionFactory;
import br.ufpr.tads.web.dao.EmpresaDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(urlPatterns = {"/BuscarEmpresaServlet"})
public class BuscarEmpresaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Existe alguma forma de passar a conexão do banco de dados para outra Servlet?
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("login") != null && session.getAttribute("nome") != null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String busca = request.getParameter("buscar");
            try {
                Connection connection = new ConnectionFactory().getConnection();
                EmpresaDao empresaDao = new EmpresaDao();
                String query = "SELECT * FROM EMPRESA WHERE RAZAO_SOCIAL LIKE '%" + busca + "%' ORDER BY RAZAO_SOCIAL";

                out.println("<html><head><title>BuscarEmpresaServlet</title></head>");
                out.println("<body><h2>Empresas</h2>" + empresaDao.listAllTable(empresaDao.select(connection, query)));
                out.println("<br/><br/><a href=\"PortalServlet\">Mostrar Todos</a>");
                out.println("</body></html>");
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(BuscarEmpresaServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BuscarEmpresaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/ProcessaErroServlet");
            request.setAttribute("errorMessage", "Você não está logado. Efetue o login para prosseguir.");
            rd.forward(request, response);
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
