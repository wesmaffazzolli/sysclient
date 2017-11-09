/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.web.controller;

import br.ufpr.tads.web.bd.ConnectionFactory;
import br.ufpr.tads.web.dao.AcessoDao;
import br.ufpr.tads.web.model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wesley
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login", "/logout"})
public class LoginController extends Controller {

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
            if (String.format("%s/login", request.getContextPath()).equals(request.getRequestURI())) {
                doLogin(request, response);
            } else {
                doLogout(request, response);
            }
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            if (String.format("%s/login", request.getContextPath()).equals(request.getRequestURI())) {
                doLogin(request, response);
            } else {
                doLogout(request, response);
            }
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    

    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Usuario usuario = new Usuario(request.getParameter("login"), request.getParameter("senha"));

        try {
            Connection conexao = new ConnectionFactory().getConnection();
            AcessoDao chave = new AcessoDao(usuario);
            if (chave.isUser(chave.login(conexao))) { //Valida o processo de login do usuário
                chave.setSession(request.getSession());
                if (chave.isConnected()) { //Valida se a sessão foi aberta e está com os dados do usuário
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuario);
                    RequestDispatcher rd = getServletContext().
                            getRequestDispatcher("/portal");
                    rd.forward(request, response);
                }
            } else {
                request.setAttribute("mensagem", "O usuário não está logado. Faça o login para prosseguir.");
                RequestDispatcher rd = getServletContext().
                        getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
            }
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Houve um erro de SQL.");
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada.");
        }
    }

    protected void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath());
    }
}
