/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class KnjizaraPretraga implements Serializable{
    private int idKnjizara;
    private String naziv;
    private String adresa;
    private String email;
    private Grad grad;

    public KnjizaraPretraga() {
    }

    public KnjizaraPretraga(int idKnjizara, String naziv, String adresa, String email, Grad grad) {
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
    
    
}
