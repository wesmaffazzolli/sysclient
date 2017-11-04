/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
import bd.ConnectionFactory;
import beans.Usuario;
import dao.AcessoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
@WebServlet(urlPatterns = {"/ProcessaLoginServlet"})
public class ProcessaLoginServlet extends HttpServlet {

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
        Usuario usuario = new Usuario(request.getParameter("login"), request.getParameter("senha"));
        try {
            Connection conexao = new ConnectionFactory().getConnection();
            AcessoDao chave = new AcessoDao(usuario);
            if (chave.isUser(chave.login(conexao))) { //Valida o processo de login do usuário
                chave.setSession(request.getSession());
                if (chave.isConnected()) { //Valida se a sessão foi aberta e está com os dados do usuário
                    RequestDispatcher rd = getServletContext().
                            getRequestDispatcher("/PortalServlet");
                    rd.forward(request, response);
                }
            } else {
                RequestDispatcher rd = getServletContext().
                        getRequestDispatcher("/ProcessaErroServlet");
                rd.forward(request, response);
            }
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Houve um erro de SQL.");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada.");
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
