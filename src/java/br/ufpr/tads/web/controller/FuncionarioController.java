/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.web.controller;

import br.ufpr.tads.web.dao.FuncionarioDao;
import br.ufpr.tads.web.model.Funcionario;
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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Wesley
 */
@WebServlet(name = "FuncionarioController", urlPatterns = {"/funcionario"})
public class FuncionarioController extends Controller {

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

        Funcionario funcionario = null;
        List<Funcionario> lista;
        String id;
        RequestDispatcher rd;
        Client client = ClientBuilder.newClient();

        if (request.getParameter("action") == null) {
            Response resp = client.target("http://localhost:8081/wsysclient/webresources/funcionarios").request(MediaType.APPLICATION_JSON).get();
            lista = resp.readEntity(new GenericType<List<Funcionario>>() {
            });
            if (lista != null) {
                request.setAttribute("lista", lista);
                rd = getServletContext().getRequestDispatcher(viewPath("/funcionario/home.jsp"));
                rd.forward(request, response);
            } else {
                out.println("Erro ao buscar a findAll list.");
            }
        } else {
            String action = request.getParameter("action");
            switch (action) {
                case "add":
                    rd = getServletContext().getRequestDispatcher(viewPath("/funcionario/insert.jsp"));
                    rd.forward(request, response);
                    break;
                case "update":
                    funcionario = new Funcionario();
                    id = request.getParameter("id");
                    funcionario = client.target("http://localhost:8081/wsysclient/webresources/funcionarios/" + id).request(MediaType.APPLICATION_JSON).get(Funcionario.class);
                    //FuncionarioDao funcionarioDao = new FuncionarioDao();
                    //funcionario = funcionarioDao.findById(id);
                    if (funcionario != null) {
                        request.setAttribute("funcionario", funcionario);
                        rd = getServletContext().getRequestDispatcher(viewPath("/funcionario/update.jsp"));
                        rd.forward(request, response);
                    } else {
                        out.println("Ocorreu um erro com a alteração.");
                    }
                    break;
                case "delete":
                    funcionario = new Funcionario();
                    id = request.getParameter("id");
                    funcionario = client.target("http://localhost:8081/wsysclient/webresources/funcionarios/" + id).request(MediaType.APPLICATION_JSON).get(Funcionario.class);
                    FuncionarioDao funcionarioDao = new FuncionarioDao();
                    if (funcionarioDao.remove(id)) {
                        request.setAttribute("mensagem", "O funcionário " + funcionario.getNome() + " foi removido com sucesso.");
                    } else {
                        request.setAttribute("mensagem", "Erro! O funcionário " + funcionario.getNome() + " não foi removido.");
                    }
                    rd = getServletContext().getRequestDispatcher(viewPath("/funcionario/mensagem.jsp"));
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

        Funcionario funcionario = null;
        List<Funcionario> lista;
        RequestDispatcher rd;
        Client client = ClientBuilder.newClient();

        if (request.getParameter("action") == null) {
            Response resp = client.target("http://localhost:8081/wsysclient/webresources/funcionarios").request(MediaType.APPLICATION_JSON).get();
            lista = resp.readEntity(new GenericType<List<Funcionario>>() {
            });
            if (lista != null) {
                request.setAttribute("lista", lista);
                rd = getServletContext().getRequestDispatcher(viewPath("/funcionario/home.jsp"));
                rd.forward(request, response);
            } else {
                out.println("Erro ao buscar a findAll list.");
            }
        } else {
            String action = request.getParameter("action");
            switch (action) {
                case "find":
                    String search = request.getParameter("search");
                    Response resp = client.target("http://localhost:8081/wsysclient/webresources/funcionarios/search/" + search).request(MediaType.APPLICATION_JSON + ";charset=utf-8").get();
                    lista = resp.readEntity(new GenericType<List<Funcionario>>() {
                    });
                    if (lista != null) {
                        request.setAttribute("lista", lista);
                        rd = getServletContext().getRequestDispatcher(viewPath("/funcionario/search.jsp"));
                        rd.forward(request, response);
                    } else {
                        out.println("Erro ao buscar a findByName list.");
                    }
                    break;
                case "add":
                    funcionario = new Funcionario();
                    funcionario.setCpf(request.getParameter("cpf"));
                    funcionario.setEmail(request.getParameter("email"));
                    funcionario.setEndereco(request.getParameter("endereco"));
                    funcionario.setNome(request.getParameter("nome"));
                    funcionario.setCelular(request.getParameter("celular"));
                    funcionario.setEmpresaCodigo(request.getParameter("empresaCodigo"));

                    funcionario = client.target("http://localhost:8081/wsysclient/webresources/funcionarios").request(MediaType.APPLICATION_JSON).post(Entity.json(funcionario), Funcionario.class);
                    if (funcionario != null) {
                        request.setAttribute("mensagem", "O funcionário " + funcionario.getNome() + " foi adicionado com sucesso.");
                    } else {
                        request.setAttribute("mensagem", "Houve um erro ao inserir o funcionário.");
                    }
                    rd = getServletContext().getRequestDispatcher(viewPath("/funcionario/mensagem.jsp"));
                    rd.forward(request, response);
                    break;
                case "update":
                    funcionario = new Funcionario();
                    funcionario.setId(request.getParameter("id"));
                    funcionario.setCpf(request.getParameter("cpf"));
                    funcionario.setEmail(request.getParameter("email"));
                    funcionario.setEndereco(request.getParameter("endereco"));
                    funcionario.setNome(request.getParameter("nome"));
                    funcionario.setCelular(request.getParameter("celular"));
                    funcionario.setEmpresaCodigo(request.getParameter("empresaCodigo"));

                    //FuncionarioDao funcionarioDao = new FuncionarioDao();
                    funcionario = client.target("http://localhost:8081/wsysclient/webresources/funcionarios").request(MediaType.APPLICATION_JSON).put(Entity.json(funcionario), Funcionario.class);
                    if (funcionario != null) {
                        //if(funcionarioDao.update(funcionario)) {
                        request.setAttribute("mensagem", "O funcionário " + funcionario.getNome() + " foi atualizado com sucesso.");
                    } else {
                        request.setAttribute("mensagem", "Erro ao atualizar o funcionário " + funcionario.getNome() + ".");
                    }
                    rd = getServletContext().getRequestDispatcher(viewPath("/funcionario/mensagem.jsp"));
                    rd.forward(request, response);
                    break;
                case "delete":
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
        return "funcionario";
    }// </editor-fold>
}
