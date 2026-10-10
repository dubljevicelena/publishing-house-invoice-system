/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import java.sql.SQLException;
import repository.Repository;

/**
 *
 * @author Dell
 */
public interface DbRepository<T> extends Repository<T> {
    default public void connect() throws ClassNotFoundException, SQLException {
        DbConnection.getInstance().getConnection();
    }
    
    default public void disconnect() throws ClassNotFoundException, SQLException {
        DbConnection.getInstance().getConnection().close();
    }
    
    default public void commit() throws ClassNotFoundException, SQLException {
        DbConnection.getInstance().getConnection().commit();
    }
    
    default public void rollback() throws ClassNotFoundException, SQLException {
        DbConnection.getInstance().getConnection().rollback();
    }
    
    
}
