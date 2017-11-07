/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.web.dao;

import br.ufpr.tads.web.bd.ConnectionFactory;
import br.ufpr.tads.web.model.Empresa;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wesley
 */
public class EmpresaDao extends GenericDao {

    @Override
    public List<Empresa> findAll() {
        try {
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMPRESA ORDER BY RAZAO_SOCIAL");
            List<Empresa> list = new ArrayList<Empresa>();
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getString("codigo"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setEmail(rs.getString("email"));
                empresa.setEndereco(rs.getString("endereco"));
                empresa.setRazaoSocial(rs.getString("razao_social"));
                list.add(empresa);
            }

            connection.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Empresa> findByName(String n) {
        try {
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMPRESA WHERE RAZAO_SOCIAL LIKE '%" + n + "%' ORDER BY RAZAO_SOCIAL");
            List<Empresa> list = new ArrayList<Empresa>();
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getString("codigo"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setEmail(rs.getString("email"));
                empresa.setEndereco(rs.getString("endereco"));
                empresa.setRazaoSocial(rs.getString("razao_social"));
                list.add(empresa);
            }

            connection.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Empresa findById(String id) {
        try {
            Empresa empresa = null;
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM EMPRESA WHERE CODIGO='" + id + "';";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                empresa = new Empresa();
                empresa.setId(rs.getString("codigo"));
                empresa.setCnpj(rs.getString("cnpj"));
                empresa.setEmail(rs.getString("email"));
                empresa.setEndereco(rs.getString("endereco"));
                empresa.setRazaoSocial(rs.getString("razao_social"));
            }
            connection.close();
            return empresa;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean insert(Object ob) {
        try {
            Empresa empresa = (Empresa) ob;
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            String query = "INSERT INTO EMPRESA(CNPJ,RAZAO_SOCIAL,ENDERECO,EMAIL) "
                    + "VALUES('"
                    + empresa.getCnpj() + "','"
                    + empresa.getRazaoSocial() + "','"
                    + empresa.getEndereco() + "','"
                    + empresa.getEmail() + "');";
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Object ob) {
        try {
            Empresa empresa = (Empresa) ob;
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            String query = "UPDATE EMPRESA SET CNPJ='" + empresa.getCnpj()
                    + "', RAZAO_SOCIAL='" + empresa.getRazaoSocial()
                    + "', ENDERECO='" + empresa.getEndereco()
                    + "', EMAIL='" + empresa.getEmail()
                    + "' WHERE CODIGO='" + empresa.getId()
                    + "';";
            stmt.executeUpdate(query);

            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean remove(String id) {
        try {
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            String query = "DELETE FROM EMPRESA WHERE CODIGO='" + id + "';";
            stmt.executeUpdate(query);

            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpresaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Connection callConnection() throws SQLException, ClassNotFoundException {
        return new ConnectionFactory().getConnection();
    }

    public void dml(Connection connection, String query) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }

    public ResultSet select(Connection connection, String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    public String listAllTable(ResultSet rs) throws SQLException {
        String tablePrinted
                = "<table style=\"width:100%; text-align:left;\" border=\"1\""
                + "<tr>"
                + "<th>CNPJ</th>"
                + "<th>Razão Social</th>"
                + "<th>Endereço</th>"
                + "<th>Email</th>"
                + "<th>Ação</th>"
                + "</tr>";
        while (rs.next()) {
            tablePrinted
                    = tablePrinted + "<tr>"
                    + "<td>" + rs.getString("cnpj") + "</td>"
                    + "<td>" + rs.getString("razao_social") + "</td>"
                    + "<td>" + rs.getString("endereco") + "</td>"
                    + "<td>" + rs.getString("email") + "</td>"
                    + "<td value><a href=\"RemoverEmpresaServlet?id=" + rs.getString("id") + "\">Remover</a>&nbsp&nbsp<a href=\"FormularioAlterarEmpresa?id=" + rs.getString("id") + "\">Alterar</a></td>"
                    + "</tr>";
        }
        return tablePrinted + "</table>";
    }
}
