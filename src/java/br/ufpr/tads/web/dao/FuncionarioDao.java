/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.web.dao;

/**
 *
 * @author Wesley
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import br.ufpr.tads.web.bd.ConnectionFactory;
import br.ufpr.tads.web.model.Funcionario;
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
public class FuncionarioDao extends GenericDao {

    @Override
    public List<Funcionario> findAll() {
        try {
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FUNCIONARIO");

            List<Funcionario> list = new ArrayList<Funcionario>();
            Funcionario funcionario;
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setId(rs.getString("codigo"));
                funcionario.setEmpresaCodigo(rs.getString("empresa_codigo"));
                list.add(funcionario);
            }

            connection.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Funcionario> findByName(String n) {
        try {
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FUNCIONARIO WHERE NOME LIKE '%" + n + "%' ORDER BY NOME");

            List<Funcionario> list = new ArrayList<Funcionario>();
            Funcionario funcionario;
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setId(rs.getString("codigo"));
                funcionario.setEmpresaCodigo(rs.getString("empresa_codigo"));
                list.add(funcionario);
            }

            connection.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Funcionario findById(String id) {
        try {
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM FUNCIONARIO WHERE CODIGO='" + id + "';";
            ResultSet rs = stmt.executeQuery(query);

            Funcionario funcionario = null;
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setId(rs.getString("codigo"));
                funcionario.setEmpresaCodigo(rs.getString("empresa_codigo"));
            }

            connection.close();
            return funcionario;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Funcionario> findByEmpresa(String id) {
        try {
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FUNCIONARIO WHERE EMPRESA_CODIGO='" + id + "';");

            List<Funcionario> list = new ArrayList<Funcionario>();
            Funcionario funcionario;
            while (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setCelular(rs.getString("celular"));
                funcionario.setEmail(rs.getString("email"));
                funcionario.setEndereco(rs.getString("endereco"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setId(rs.getString("id"));
                funcionario.setEmpresaCodigo(rs.getString("empresa_codigo"));
                list.add(funcionario);
            }

            connection.close();
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean insert(Object ob) {
        try {
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            Funcionario funcionario = (Funcionario) ob;
            String query = "INSERT INTO FUNCIONARIO(CPF,NOME,ENDERECO,EMAIL,CELULAR,EMPRESA_CODIGO) "
                    + "VALUES('"
                    + funcionario.getCpf() + "','"
                    + funcionario.getNome() + "','"
                    + funcionario.getEndereco() + "','"
                    + funcionario.getEmail() + "','"
                    + funcionario.getCelular() + "','"
                    + funcionario.getEmpresaCodigo() + "');";
            stmt.executeUpdate(query);

            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Object ob) {
        try {
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            Funcionario funcionario = (Funcionario) ob;
            String query = "UPDATE FUNCIONARIO SET CPF='" + funcionario.getCpf()
                    + "', NOME='" + funcionario.getNome()
                    + "', ENDERECO='" + funcionario.getEndereco()
                    + "', EMAIL='" + funcionario.getEmail()
                    + "', CELULAR='" + funcionario.getCelular()
                    + "', EMPRESA_CODIGO='" + funcionario.getEmpresaCodigo()
                    + "' WHERE CODIGO='" + funcionario.getId()
                    + "';";
            stmt.executeUpdate(query);

            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean remove(String id) {
        try {
            Connection connection = this.callConnection();
            Statement stmt = connection.createStatement();
            String query = "DELETE FROM FUNCIONARIO WHERE CODIGO='" + id + "';";
            stmt.executeUpdate(query);

            connection.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Connection callConnection() throws SQLException, ClassNotFoundException {
        return new ConnectionFactory().getConnection();
    }
}

