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
import java.sql.ResultSet;
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
@WebServlet(urlPatterns = {"/FormularioAlterarEmpresa"})
public class FormularioAlterarEmpresa extends HttpServlet {

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
        HttpSession session = request.getSession();
        if (session.getAttribute("login") != null && session.getAttribute("nome") != null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            String id = request.getParameter("id");
            String query = "SELECT * FROM EMPRESA WHERE ID='" + id + "';";

            try {
                Connection connection = new ConnectionFactory().getConnection();
                EmpresaDao empresaDao = new EmpresaDao();
                ResultSet rs = empresaDao.select(connection, query);

                while (rs.next()) {
                    out.println("<html><body>"
                            + "<h1>Alterar Empresa</h1>"
                            + "<form action=\"AlterarEmpresaServlet?id=" + id + "\" method=\"POST\">");
                    out.println("CNPJ:<input type=\"text\" name=\"cnpj\" value=\"" + rs.getString("cnpj") + "\"><br/>"
                            + "Razão Social:<input type=\"text\" name=\"razao_social\" value=\"" + rs.getString("razao_social") + "\"><br>"
                            + "Endereço:<input type=\"text\" name=\"endereco\" value=\"" + rs.getString("endereco") + "\"><br>"
                            + "Email:<input type=\"text\" name=\"email\" value=\"" + rs.getString("email") + "\"><br><br>"
                            + "<input type=\"submit\" value=\"Alterar\"><a href=\"PortalServlet\"><input type=\"button\" value=\"Cancelar\"</a>");
                    out.println("</form></body></html>");
                }

                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdicionarEmpresaServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AdicionarEmpresaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
