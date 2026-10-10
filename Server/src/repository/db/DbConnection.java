/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;

/**
 *
 * @author Dell
 */
public class DbConnection {
    private static DbConnection instance;
    private Connection connection;
    
    private DbConnection() throws ClassNotFoundException {
        try {
            if(connection == null || connection.isClosed()){
                Class.forName("com.mysql.cj.jdbc.Driver");
                
                String url = configuration.Configuration.getInstance().getProperty("url");
                String username = configuration.Configuration.getInstance().getProperty("username");
                String password = configuration.Configuration.getInstance().getProperty("password");
                
                connection = DriverManager.getConnection(url, username, password);
                connection.setAutoCommit(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DbConnection getInstance() throws ClassNotFoundException {
        if(instance == null){
            instance = new DbConnection();
        }
        return instance;
    }
    
    public Connection getConnection() throws SQLException{
        if(connection == null || connection.isClosed()){
            String url = configuration.Configuration.getInstance().getProperty("url");
            String username = configuration.Configuration.getInstance().getProperty("username");
            String password = configuration.Configuration.getInstance().getProperty("password");
            
            connection = DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);
        }
        return connection;
    }
    
}
