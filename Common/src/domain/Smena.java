/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dell
 */
public class Smena implements GenericEntity,Serializable{
    
    private int idSmena;
    private Long brojRadnihSati;

    public Smena() {
    }

    public Smena(int idSmena, Long brojRadnihSati) {
        this.idSmena = idSmena;
        this.brojRadnihSati = brojRadnihSati;
    }

    public int getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(int idSmena) {
        this.idSmena = idSmena;
    }

    public Long getBrojRadnihSati() {
        return brojRadnihSati;
    }

    public void setBrojRadnihSati(Long brojRadnihSati) {
        this.brojRadnihSati = brojRadnihSati;
    }

    @Override
    public String toString() {
        return "smena ID: " + idSmena + "broj radnih sati: " + brojRadnihSati;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Smena other = (Smena) obj;
        if (!Objects.equals(this.idSmena, other.idSmena)) {
            return false;
        }
        return Objects.equals(this.brojRadnihSati, other.brojRadnihSati);
        
    }

    @Override
    public String vratiNazivTabele() {
        return "smena";
    }

    @Override
    public List<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        List<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idSmena");
            Long brojRadnihSati = rs.getLong("brojRadnihSati");
            
            Smena s = new Smena(id, brojRadnihSati);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "brojRadnihSati";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "" + brojRadnihSati;
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idSmena = id;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "brojRadnihSati=" + brojRadnihSati;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idSmena=" + idSmena;
    }
    
    
    
}
