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
public class StavkaFakture implements GenericEntity,Serializable{
    private Faktura faktura;
    private int redniBroj;
    private Long kolicina;
    private double iznosStavke;
    private double cena;
    private Knjiga knjiga;

    public StavkaFakture() {
    }

    public StavkaFakture(Faktura faktura, int redniBroj, Long kolicina, double iznosStavke, double cena, Knjiga knjiga) {
        this.faktura = faktura;
        this.redniBroj = redniBroj;
        this.kolicina = kolicina;
        this.iznosStavke = iznosStavke;
        this.cena = cena;
        this.knjiga = knjiga;
    }

    public Faktura getFaktura() {
        return faktura;
    }

    public void setFaktura(Faktura faktura) {
        this.faktura = faktura;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    public Long getKolicina() {
        return kolicina;
    }

    public void setKolicina(Long kolicina) {
        this.kolicina = kolicina;
    }

    public double getIznosStavke() {
        return iznosStavke;
    }

    public void setIznosStavke(double iznosStavke) {
        this.iznosStavke = iznosStavke;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setIdKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    @Override
    public String toString() {
        return "Stavka fakture(ID fakture: " + faktura.getIdFaktura() + ", redni broj stavke: " + redniBroj + 
                ", kolicina: " + kolicina + ", iznos stavke: " + iznosStavke + ", cena: " + cena + ", knjiga: " 
                + knjiga.getNaziv() + ")";
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
        final StavkaFakture other = (StavkaFakture) obj;
        if (!Objects.equals(this.faktura, other.faktura)) {
            return false;
        }
        if (this.redniBroj != other.redniBroj) {
            return false;
        }
        if (this.kolicina != other.kolicina) {
            return false;
        }
        if (this.iznosStavke != other.iznosStavke) {
            return false;
        }
        if (!Objects.equals(this.knjiga, other.knjiga)) {
            return false;
        }
        return Objects.equals(this.cena, other.cena);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkafakture";
    }

    @Override
    public List<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        List<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            Faktura f = new Faktura();
            f.setIdFaktura(rs.getInt("idFaktura"));
            
            Knjiga k = new Knjiga();
            k.setIdKnjiga(rs.getInt("idKnjiga"));
            k.setNaziv(rs.getString("nazivKnjige"));
            k.setGodinaIzdanja(rs.getLong("godinaIzdanja"));
            k.setTiraz(rs.getLong("tiraz"));
            k.setCena(rs.getDouble("cena"));
            
            int redniBroj = rs.getInt("rbStavkeFakture");
            Long kolicina = rs.getLong("kolicina");
            double iznos = rs.getDouble("iznosStavke");
            double cena = rs.getDouble("cena");
            
            StavkaFakture sf = new StavkaFakture(f, redniBroj, kolicina, iznos, cena, k);
            lista.add(sf);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idFaktura,kolicina,iznosStavke,cena,idKnjiga";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return faktura.getIdFaktura() + ", " + kolicina + ", " + 
                iznosStavke + ", " + cena + ", " + knjiga.getIdKnjiga();
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "idFaktura=" + faktura.getIdFaktura() +
                ",kolicina=" + kolicina +
                ",iznosStavke=" + iznosStavke +
                ",cena=" + cena +
                ",idKnjiga=" + knjiga.getIdKnjiga();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idFaktura = " + faktura.getIdFaktura() +
                " AND rbStavkeFakture = " + redniBroj;
    }
            
    
}
