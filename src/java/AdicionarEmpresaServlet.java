/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.ufpr.tads.web.bd.ConnectionFactory;
import br.ufpr.tads.web.model.Empresa;
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
@WebServlet(urlPatterns = {"/AdicionarEmpresaServlet"})
public class AdicionarEmpresaServlet extends HttpServlet {

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
            out.println("<html><head><title>AdicionarEmpresaServlet</title></head>");

            Empresa empresa = new Empresa();
            empresa.setId(request.getParameter("id"));
            empresa.setCnpj(request.getParameter("cnpj"));
            empresa.setRazaoSocial(request.getParameter("razao_social"));
            empresa.setEndereco(request.getParameter("endereco"));
            empresa.setEmail(request.getParameter("email"));

            EmpresaDao empresaDao = new EmpresaDao();
            if (empresaDao.insert(empresa)) {
                out.println("<body><h2>Empresa " + (String) request.getParameter("razao_social") + " adicionada com sucesso.</h2>");
            } else {
                out.println("<body><h2>A empresa " + (String) request.getParameter("razao_social") + "não foi adicionada.</h2>");
            }
            out.println("<br/><br/><a href=\"PortalServlet\">Voltar para o Portal</a>");
            out.println("</body></html>");
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
