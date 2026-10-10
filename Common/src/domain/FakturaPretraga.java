/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Dell
 */
public class FakturaPretraga implements Serializable{
    private LocalDate datumKreiranjaOd;
    private LocalDate datumKreiranjaDo;
    private LocalDate datumIsplateOd;
    private LocalDate datumIsplateDo;
    private double ukupanIznosOd;
    private double ukupanIznosDo;
    private Knjizara knjizara;
    private Racunovodja racunovodja;

    public FakturaPretraga() {
    }

    public FakturaPretraga(LocalDate datumKreiranjaOd, LocalDate datumKreiranjaDo, LocalDate datumIsplateOd, LocalDate datumIsplateDo, double ukupanIznosOd, double ukupanIznosDo, Knjizara knjizara, Racunovodja racunovodja) {
        this.datumKreiranjaOd = datumKreiranjaOd;
        this.datumKreiranjaDo = datumKreiranjaDo;
        this.datumIsplateOd = datumIsplateOd;
        this.datumIsplateDo = datumIsplateDo;
        this.ukupanIznosOd = ukupanIznosOd;
        this.ukupanIznosDo = ukupanIznosDo;
        this.knjizara = knjizara;
        this.racunovodja = racunovodja;
    }

    public LocalDate getDatumKreiranjaOd() {
        return datumKreiranjaOd;
    }

    public void setDatumKreiranjaOd(LocalDate datumKreiranjaOd) {
        this.datumKreiranjaOd = datumKreiranjaOd;
    }

    public LocalDate getDatumKreiranjaDo() {
        return datumKreiranjaDo;
    }

    public void setDatumKreiranjaDo(LocalDate datumKreiranjaDo) {
        this.datumKreiranjaDo = datumKreiranjaDo;
    }

    public LocalDate getDatumIsplateOd() {
        return datumIsplateOd;
    }

    public void setDatumIsplateOd(LocalDate datumIsplateOd) {
        this.datumIsplateOd = datumIsplateOd;
    }

    public LocalDate getDatumIsplateDo() {
        return datumIsplateDo;
    }

    public void setDatumIsplateDo(LocalDate datumIsplateDo) {
        this.datumIsplateDo = datumIsplateDo;
    }

    public double getUkupanIznosOd() {
        return ukupanIznosOd;
    }

    public void setUkupanIznosOd(double ukupanIznosOd) {
        this.ukupanIznosOd = ukupanIznosOd;
    }

    public double getUkupanIznosDo() {
        return ukupanIznosDo;
    }

    public void setUkupanIznosDo(double ukupanIznosDo) {
        this.ukupanIznosDo = ukupanIznosDo;
    }

    public Knjizara getKnjizara() {
        return knjizara;
    }

    public void setKnjizara(Knjizara knjizara) {
        this.knjizara = knjizara;
    }

    public Racunovodja getRacunovodja() {
        return racunovodja;
    }

    public void setRacunovodja(Racunovodja racunovodja) {
        this.racunovodja = racunovodja;
    }
    
}
