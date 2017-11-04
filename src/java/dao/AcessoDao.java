/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wesley
 */
public class AcessoDao {
    
    private Usuario usuario;
    private HttpSession session;

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
        this.getSession().setAttribute("login", this.getUsuario().getLogin());
        this.getSession().setAttribute("nome", this.getUsuario().getNome());
    }
    
    public AcessoDao(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public ResultSet login(Connection connection) throws SQLException {
        String query = "SELECT nome FROM usuario WHERE LOGIN='" + this.getUsuario().getLogin() + "' AND SENHA='" + this.getUsuario().getSenha() + "'";
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    public boolean isUser(ResultSet result) throws SQLException {
        if (result.next()) {
            this.getUsuario().setNome(result.getString("nome"));
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isConnected()
    {
        if(this.getSession().getAttribute("login").equals(this.getUsuario().getLogin())){
            return true;
        } else {
            return false;
        }
    }
}
