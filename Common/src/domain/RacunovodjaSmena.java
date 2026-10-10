/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dell
 */
public class RacunovodjaSmena implements GenericEntity,Serializable{
    private Racunovodja racunovodja;
    private Smena smena;
    private LocalDate datumRada;

    public RacunovodjaSmena() {
    }

    public RacunovodjaSmena(Racunovodja racunovodja, Smena smena, LocalDate datumRada) {
        this.racunovodja = racunovodja;
        this.smena = smena;
        this.datumRada = datumRada;
    }

    public Racunovodja getRacunovodja() {
        return racunovodja;
    }

    public void setRacunovodja(Racunovodja racunovodja) {
        this.racunovodja = racunovodja;
    }

    public Smena getSmena() {
        return smena;
    }

    public void setSmena(Smena smena) {
        this.smena = smena;
    }

    public LocalDate getDatumRada() {
        return datumRada;
    }

    public void setDatumRada(LocalDate datumRada) {
        this.datumRada = datumRada;
    }

    @Override
    public String toString() {
        return "racunovodja: " + racunovodja.toString() + "\nsmena: " + smena + "\ndatum rada: " + datumRada;
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
        final RacunovodjaSmena other = (RacunovodjaSmena) obj;
        if (!Objects.equals(this.racunovodja, other.racunovodja)) {
            return false;
        }
        if (!Objects.equals(this.smena, other.smena)) {
            return false;
        }
        return Objects.equals(this.datumRada, other.datumRada);
    }

    @Override
    public String vratiNazivTabele() {
        return "racunovodjasmena";
    }

    @Override
    public List<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        List<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            Racunovodja r = new Racunovodja();
            r.setIdRacunovodja(rs.getInt("idRacunovodja"));
            
            Smena s = new Smena();
            s.setIdSmena(rs.getInt("idSmena"));
            
            LocalDate datumRada = rs.getDate("brojRadnihSati").toLocalDate();
            
            RacunovodjaSmena racSm = new RacunovodjaSmena(r, s, datumRada);
            lista.add(racSm);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "idRacunovodja,idSmena,datumRada";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return racunovodja.getIdRacunovodja() + "," + smena.getIdSmena() + "'" + datumRada + "'";
    }

    @Override
    public void vratiGenerisaniKljuc(int generisaniId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "idRacunovodja=" + racunovodja.getIdRacunovodja() + 
                ", idSmena=" + smena.getIdSmena() + 
                ", datumRada='" + datumRada + "'";
    } 

    @Override
    public String vratiPrimarniKljuc() {
        return "idRacunovodja=" + racunovodja.getIdRacunovodja() +
                "idSmena=" + smena.getIdSmena() +
                "datumRada=" + datumRada;
    }
    
    
}
