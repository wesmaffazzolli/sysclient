/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.web.controller;

import br.ufpr.tads.web.facede.EmpresaFacede;
import br.ufpr.tads.web.model.Empresa;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "EmpresaController", urlPatterns = {"/empresa"})
public class EmpresaController extends Controller {

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
        if (session.getAttribute("usuario") == null) {
            request.setAttribute("mensagem", "O usuário não está logado. Faça o login para prosseguir.");
            RequestDispatcher rd = getServletContext().
                    getRequestDispatcher("/erro.jsp");
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        request.setAttribute("basePath", getBasePath());
        EmpresaFacede facede = new EmpresaFacede();
        Empresa empresa = null;
        List<Empresa> lista;
        RequestDispatcher rd;

        if (request.getParameter("action") == null) {
            lista = facede.findAll();
            request.setAttribute("lista", lista);
            if (lista != null) {
                rd = getServletContext().getRequestDispatcher(viewPath("/empresa/home.jsp"));
                rd.forward(request, response);
            } else {
                out.println("Erro ao buscar a findAll list.");
            }
        } else {
            String action = request.getParameter("action");
            switch (action) {
                case "add":
                    rd = getServletContext().getRequestDispatcher(viewPath("/empresa/insert.jsp"));
                    rd.forward(request, response);
                    break;
                case "update":
                    empresa = new Empresa();
                    String id = request.getParameter("id");
                    empresa = facede.findById(id);
                    request.setAttribute("empresa", empresa);
                    if (empresa != null) {
                        rd = getServletContext().getRequestDispatcher(viewPath("/empresa/update.jsp"));
                        rd.forward(request, response);
                    } else {
                        out.println("Ocorreu um erro com a alteração.");
                    }
                    break;
                case "delete":
                    empresa = facede.findById(request.getParameter("id"));
                    if (facede.remove(empresa.getId())) {
                        request.setAttribute("mensagem", "A empresa " + empresa.getRazaoSocial() + " foi removida com sucesso.");
                    } else {
                        request.setAttribute("mensagem", "Houve um erro e a empresa " + empresa.getRazaoSocial() + " não foi removida.");
                    }
                    rd = getServletContext().getRequestDispatcher(viewPath("/empresa/mensagem.jsp"));
                    rd.forward(request, response);
                    break;
            }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        request.setAttribute("basePath", getBasePath());
        EmpresaFacede facede = new EmpresaFacede();
        Empresa empresa = null;
        List<Empresa> lista;
        RequestDispatcher rd;

        if (request.getParameter("action") == null) {
            lista = facede.findAll();
            request.setAttribute("lista", lista);
            if (lista != null) {
                rd = getServletContext().getRequestDispatcher(viewPath("/empresa/home.jsp"));
                rd.forward(request, response);
            } else {
                out.println("Erro ao buscar a findAll list.");
            }
        } else {
            String action = request.getParameter("action");
            switch (action) {
                case "find":
                    String search = request.getParameter("search");
                    lista = facede.findByName(search);
                    request.setAttribute("lista", lista);
                    if (lista != null) {
                        rd = getServletContext().getRequestDispatcher(viewPath("/empresa/search.jsp"));
                        rd.forward(request, response);
                    } else {
                        out.println("Erro ao buscar a findByName list.");
                    }
                    break;
                case "add":
                    empresa = new Empresa();
                    empresa.setCnpj(request.getParameter("cnpj"));
                    empresa.setEmail(request.getParameter("email"));
                    empresa.setEndereco(request.getParameter("endereco"));
                    empresa.setRazaoSocial(request.getParameter("razao_social"));

                    if (facede.insert(empresa)) {
                        request.setAttribute("mensagem", "A empresa " + empresa.getRazaoSocial() + " foi adicionada com sucesso.");
                    } else {
                        request.setAttribute("mensagem", "Houve um erro ao inserir a empresa.");
                    }
                    rd = getServletContext().getRequestDispatcher(viewPath("/empresa/mensagem.jsp"));
                    rd.forward(request, response);
                    break;
                case "update":
                    empresa = new Empresa();
                    empresa.setId(request.getParameter("id"));
                    empresa.setCnpj(request.getParameter("cnpj"));
                    empresa.setEmail(request.getParameter("email"));
                    empresa.setEndereco(request.getParameter("endereco"));
                    empresa.setRazaoSocial(request.getParameter("razao_social"));

                    if (facede.update(empresa)) {
                        request.setAttribute("mensagem", "A empresa foi atualizada com sucesso.");
                    } else {
                        request.setAttribute("mensagem", "Houve um erro ao atualizar a empresa.");
                    }
                    rd = getServletContext().getRequestDispatcher(viewPath("/empresa/mensagem.jsp"));
                    rd.forward(request, response);
                    break;
                case "delete":
                    break;
                default:
                    lista = facede.findAll();
                    request.setAttribute("lista", lista);
                    if (lista != null) {
                        rd = getServletContext().getRequestDispatcher(viewPath("/empresa/portal.jsp"));
                        rd.forward(request, response);
                    } else {
                        out.println("Erro ao buscar a findAll list.");
                    }
                    break;
            }
        }
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

    public String getBasePath() {
        return "empresa";
    }// </editor-fold>
}
