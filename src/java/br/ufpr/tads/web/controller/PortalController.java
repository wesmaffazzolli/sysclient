/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.web.controller;

import br.ufpr.tads.web.bd.ConnectionFactory;
import br.ufpr.tads.web.dao.EmpresaDao;
import br.ufpr.tads.web.facede.CrudFacede;
import br.ufpr.tads.web.facede.EmpresaFacede;
import br.ufpr.tads.web.model.Empresa;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "PortalController", urlPatterns = {"/portal"})
public class PortalController extends Controller {

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //request.setAttribute("baseDir", "web/WEB-INF/view");

        request.setAttribute("basePath", getBasePath());
        String action = request.getParameter("action");
        EmpresaFacede facede = new EmpresaFacede();
        Empresa empresa = null;
        List<Empresa> lista;
        RequestDispatcher rd;

        switch (action) {
            case "add":
                rd = getServletContext().getRequestDispatcher(viewPath("/portal/insert.jsp"));
                rd.forward(request, response);
                break;
            case "update":
                empresa = new Empresa();
                String id = request.getParameter("id");
                empresa = facede.findById(id);
                request.setAttribute("empresa", empresa);
                if (empresa != null) {
                    rd = getServletContext().getRequestDispatcher(viewPath("/portal/update.jsp"));
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
                rd = getServletContext().getRequestDispatcher(viewPath("/portal/mensagem.jsp"));
                rd.forward(request, response);
                break;
            default:
                lista = facede.findAll();
                request.setAttribute("lista", lista);
                if (lista != null) {
                    rd = getServletContext().getRequestDispatcher(viewPath("/portal/portal.jsp"));
                    rd.forward(request, response);
                } else {
                    out.println("Erro ao buscar a findAll list.");
                }
                break;
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //request.setAttribute("baseDir", "web/WEB-INF/view");

        EmpresaFacede facede = new EmpresaFacede();
        List<Empresa> lista;
        Empresa empresa = null;
        RequestDispatcher rd;
        request.setAttribute("basePath", getBasePath());

        String action = (String) request.getParameter("action");

        switch (action) {
            case "find":
                String search = request.getParameter("search");
                lista = facede.findByName(search);
                request.setAttribute("lista", lista);
                if (lista != null) {
                    rd = getServletContext().getRequestDispatcher(viewPath("/portal/search.jsp"));
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
                rd = getServletContext().getRequestDispatcher(viewPath("/portal/mensagem.jsp"));
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
                rd = getServletContext().getRequestDispatcher(viewPath("/portal/mensagem.jsp"));
                rd.forward(request, response);
                break;
            case "delete":
                break;
            default:
                lista = facede.findAll();
                request.setAttribute("lista", lista);
                if (lista != null) {
                    rd = getServletContext().getRequestDispatcher(viewPath("/portal/portal.jsp"));
                    rd.forward(request, response);
                } else {
                    out.println("Erro ao buscar a findAll list.");
                }
                break;
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
        return "portal";
    }// </editor-fold>
}
