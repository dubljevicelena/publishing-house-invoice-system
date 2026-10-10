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
public class Grad implements GenericEntity,Serializable {

    private int idGrad;
    private String naziv;

    public Grad() {
    }

    public Grad(int idGrad, String naziv) {
        this.idGrad = idGrad;
        this.naziv = naziv;
    }

    public int getIdGrad() {
        return idGrad;
    }

    public void setIdGrad(int idGrad) {
        this.idGrad = idGrad;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
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
        final Grad other = (Grad) obj;
        if (this.idGrad != other.idGrad) {
            return false;
        }
        return Objects.equals(this.naziv, other.naziv);

    }

    @Override
    public String vratiNazivTabele() {
        return "grad";
    }

    @Override
    public List<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        List<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idGrad");
            String nazivGrada = rs.getString("nazivGrada");
            
            Grad g = new Grad(id, nazivGrada);
            lista.add(g);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivGrada";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "'";
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idGrad = id;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv=" + naziv;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return " idGrad=" + idGrad;
    }
    
    
    
}
