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
public class Knjiga implements GenericEntity,Serializable{
    private int idKnjiga;
    private String naziv;
    private Long godinaIzdanja;
    private Long tiraz;
    private double cena;

    public Knjiga() {
    }

    public Knjiga(int idKnjiga, String naziv, Long godinaIzdanja, Long tiraz, double cena) {
        this.idKnjiga = idKnjiga;
        this.naziv = naziv;
        this.godinaIzdanja = godinaIzdanja;
        this.tiraz = tiraz;
        this.cena = cena;
    }

    public int getIdKnjiga() {
        return idKnjiga;
    }

    public void setIdKnjiga(int idKnjiga) {
        this.idKnjiga = idKnjiga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Long getGodinaIzdanja() {
        return godinaIzdanja;
    }

    public void setGodinaIzdanja(Long godinaIzdanja) {
        this.godinaIzdanja = godinaIzdanja;
    }

    public Long getTiraz() {
        return tiraz;
    }

    public void setTiraz(Long tiraz) {
        this.tiraz = tiraz;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
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
        final Knjiga other = (Knjiga) obj;
        if (this.idKnjiga != other.idKnjiga) {
            return false;
        }
        if (this.naziv != other.naziv) {
            return false;
        }
        if (this.godinaIzdanja != other.godinaIzdanja) {
            return false;
        }
        if (this.tiraz != other.tiraz) {
            return false;
        }
        return Objects.equals(this.cena, other.cena);
    }

    @Override
    public String vratiNazivTabele() {
        return "knjiga";
    }

    @Override
    public List<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        List<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idKnjiga");
            String naziv = rs.getString("nazivKnjige");
            Long godinaIzdanja = rs.getLong("godinaIzdanja");
            Long tiraz = rs.getLong("tiraz");
            double cena = rs.getDouble("cena");
            
            Knjiga k = new Knjiga();
            k.setIdKnjiga(id);
            k.setNaziv(naziv);
            k.setGodinaIzdanja(godinaIzdanja);
            k.setTiraz(tiraz);
            k.setCena(cena);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivKnjige,godinaIzdanja,tiraz,cena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return naziv + ", " + godinaIzdanja + ", " + tiraz + ", " + cena;
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idKnjiga = id;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivKnjige=" + naziv + "godinaIzdanja=" + godinaIzdanja +
                "tiraz=" + tiraz + "cena=" + cena;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idKnjiga=" + idKnjiga;
    }
    
    
            
    
}
