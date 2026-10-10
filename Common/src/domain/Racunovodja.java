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
public class Racunovodja implements GenericEntity,Serializable{
    
    private int idRacunovodja;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;
    private double brutoPlata;
    private double koeficijentRadnogStaza;
    private double pocetnaPlata;

    public Racunovodja() {
    }

    public Racunovodja(int idRacunovodja, String ime, String prezime, String korisnickoIme, String lozinka, double brutoPlata, double koeficijentRadnogStaza, double pocetnaPlata) {
        this.idRacunovodja = idRacunovodja;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.brutoPlata = brutoPlata;
        this.koeficijentRadnogStaza = koeficijentRadnogStaza;
        this.pocetnaPlata = pocetnaPlata;
    }
    
    public Racunovodja(String korisnickoIme, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    public int getIdRacunovodja() {
        return idRacunovodja;
    }

    public void setIdRacunovodja(int idRacunovodja) {
        this.idRacunovodja = idRacunovodja;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public double getBrutoPlata() {
        return brutoPlata;
    }

    public void setBrutoPlata(double brutoPlata) {
        this.brutoPlata = brutoPlata;
    }

    public double getKoeficijentRadnogStaza() {
        return koeficijentRadnogStaza;
    }

    public void setKoeficijentRadnogStaza(double koeficijentRadnogStaza) {
        this.koeficijentRadnogStaza = koeficijentRadnogStaza;
    }

    public double getPocetnaPlata() {
        return pocetnaPlata;
    }

    public void setPocetnaPlata(double pocetnaPlata) {
        this.pocetnaPlata = pocetnaPlata;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        //proverava da li prosledjen objekat pripada istoj klasi kao trenutni
        //ako ne pripada, objekti nisu jednaki
        if(getClass() != obj.getClass()){
            return false;
        }
        final Racunovodja other = (Racunovodja) obj;
        //proverava da li se korisnicka imena poklapaju
        if(!Objects.equals(this.korisnickoIme, other.korisnickoIme)){
            return false;
        }
        //ako se i lozinke poklapaju, vraca true, ako ne vraca false
        return Objects.equals(this.lozinka, other.lozinka);
    }

    @Override
    public String vratiNazivTabele() {
        return "racunovodja";
    }

    @Override
    public List<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        List<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idRacunovodja");
            String ime = rs.getString("imeRacunovodje");
            String prezime = rs.getString("prezimeRacunovodje");
            String korisnickoIme = rs.getString("korisnickoIme");
            String lozinka = rs.getString("lozinka");
            double brutoPlata = rs.getDouble("brutoPlata");
            double koef = rs.getDouble("koeficijentRadnogStaza");
            double pocetnaPlata = rs.getDouble("pocetnaPlata");
            
            Racunovodja r = new Racunovodja(id, ime, prezime, korisnickoIme, lozinka, brutoPlata, koef, pocetnaPlata);
            lista.add(r);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imeRacunovodje,prezimeRacunovodje,korisnickoIme,lozinka,brutoPlata,koeficijentRadnogStaza,pocetnaPlata";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "', '" + prezime + "', '" + korisnickoIme + "', '" + lozinka + "', " 
            + brutoPlata + ", " + koeficijentRadnogStaza + ", " + pocetnaPlata;
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idRacunovodja = id;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imeRacunovodje=" + ime +
                "prezimeRacunovodje=" + prezime +
                "korisnickoIme=" + korisnickoIme +
                "lozinka=" + lozinka +
                "brutoPlata=" + brutoPlata +
                "koeficijentRadnogStaza=" + koeficijentRadnogStaza +
                "pocetnaPlata=" + pocetnaPlata;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idRacunovodja=" + idRacunovodja;
    }
    
    
    
}
