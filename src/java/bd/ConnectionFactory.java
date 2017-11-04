/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Wesley
 */
public class ConnectionFactory {

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            //Class.forName("org.postgresql.Driver");
            //String url = "jdbc:postgresql://localhost:5432/Exercicio";
            //String user = "postgres";
            //String password = "root";
            
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mydb";
            String user = "root";
            String password = "root";
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
