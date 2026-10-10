/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domain.GenericEntity;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import repository.db.DbConnection;
import repository.db.DbRepository;
import java.sql.Connection;

/**
 *
 * @author Dell
 */
public class RepositoryDbGeneric implements DbRepository<GenericEntity>{

    @Override
    public List<GenericEntity> getAll(GenericEntity param, String uslov) throws Exception {
        List<GenericEntity> lista;
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if(uslov != null){
            upit += " " + uslov;
        }
        Statement st = DbConnection.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);
        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void add(GenericEntity param) throws Exception {
        String upit = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje() + ") VALUES (" 
                + param.vratiVrednostiZaUbacivanje() + ")";
        Statement st = DbConnection.getInstance().getConnection().createStatement();
        st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        if(rs.next()){
            int generisaniId = rs.getInt(1);
            param.vratiGenerisaniKljuc(generisaniId);
        }
        rs.close();
        st.close();
    }

    @Override
    public void edit(GenericEntity param) throws Exception {
        Connection con = DbConnection.getInstance().getConnection();
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu() + " WHERE " + param.vratiPrimarniKljuc();
        
        Statement st = con.createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void delete(GenericEntity param) throws Exception {
        Connection con = DbConnection.getInstance().getConnection();
        String upit = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        
        Statement st = con.createStatement();
        st.executeUpdate(upit);
        st.close();
    }
    
    
    
}
