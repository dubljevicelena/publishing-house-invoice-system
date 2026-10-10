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
public class Knjizara implements GenericEntity,Serializable{
    private int idKnjizara;
    private String naziv;
    private String adresa;
    private String email;
    private Grad grad;

    public Knjizara() {
    }

    public Knjizara(int idKnjizara, String naziv, String adresa, String email, Grad grad) {
        this.idKnjizara = idKnjizara;
        this.naziv = naziv;
        this.adresa = adresa;
        this.email = email;
        this.grad = grad;
    }

    public int getIdKnjizara() {
        return idKnjizara;
    }

    public void setIdKnjizara(int idKnjizara) {
        this.idKnjizara = idKnjizara;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }

    @Override
    public String toString() {
        return naziv + ", " + adresa;
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
        final Knjizara other = (Knjizara) obj;
        if (!Objects.equals(this.idKnjizara, other.idKnjizara)) {
            return false;
        }
        if (!Objects.equals(this.naziv, other.naziv)) {
            return false;
        }
        if (!Objects.equals(this.adresa, other.adresa)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.grad, other.grad);
    }

    @Override
    public String vratiNazivTabele() {
        return "`knjizara`";
    }

    @Override
    public List<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        List<GenericEntity> knjizare = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idKnjizara");
            String naziv = rs.getString("nazivKnjizare");
            String adresa = rs.getString("adresaKnjizare");
            String email = rs.getString("emailKnjizare");
            
            Grad g = new Grad();
            g.setIdGrad(rs.getInt("idGrad"));
            g.setNaziv(rs.getString("nazivGrada"));
            
            Knjizara k = new Knjizara(id, naziv, adresa, email, g);
            knjizare.add(k);
        }
        return knjizare;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivKnjizare,adresaKnjizare,emailKnjizare,idGrad";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "', '" + adresa + "', '" + email + "', " + grad.getIdGrad();
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idKnjizara = id;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivKnjizare='" + naziv + "', adresaKnjizare='" + adresa + "', emailKnjizare='" + email + "', idGrad=" + grad.getIdGrad();
    
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idKnjizara=" + idKnjizara;
    }
    
}
