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
public class Faktura implements GenericEntity,Serializable{
    
    private int idFaktura;
    private LocalDate datumKreiranja;
    private LocalDate rokIsplate;
    private double ukupanIznos;
    private Racunovodja racunovodja;
    private Knjizara knjizara;
    private List<StavkaFakture> stavke;

    public Faktura() {
    }

    public Faktura(int idFaktura, LocalDate datumKreiranja, LocalDate rokIsplate, double ukupanIznos, Racunovodja racunovodja, Knjizara knjizara) {
        this.idFaktura = idFaktura;
        this.datumKreiranja = datumKreiranja;
        this.rokIsplate = rokIsplate;
        this.ukupanIznos = ukupanIznos;
        this.racunovodja = racunovodja;
        this.knjizara = knjizara;
    }

    public int getIdFaktura() {
        return idFaktura;
    }

    public void setIdFaktura(int idFaktura) {
        this.idFaktura = idFaktura;
    }

    public LocalDate getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(LocalDate datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public LocalDate getRokIsplate() {
        return rokIsplate;
    }

    public void setRokIsplate(LocalDate rokIsplate) {
        this.rokIsplate = rokIsplate;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Racunovodja getRacunovodja() {
        return racunovodja;
    }

    public void setRacunovodja(Racunovodja racunovodja) {
        this.racunovodja = racunovodja;
    }

    public Knjizara getKnjizara() {
        return knjizara;
    }

    public void setKnjizara(Knjizara knjizara) {
        this.knjizara = knjizara;
    }

    public List<StavkaFakture> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaFakture> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Faktura \n" + "idFaktura: " + idFaktura + "\ndatumKreiranja: " + datumKreiranja + 
               "\nrokIsplate: " + rokIsplate + "\nukupanIznos: " + ukupanIznos + "\nracunovodja: " + 
               racunovodja + "\nknjizara: " + knjizara;
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
        final Faktura other = (Faktura) obj;
        if (!Objects.equals(this.idFaktura, other.idFaktura)) {
            return false;
        }
        if (!Objects.equals(this.datumKreiranja, other.datumKreiranja)) {
            return false;
        }
        if (!Objects.equals(this.rokIsplate, other.rokIsplate)) {
            return false;
        }
        if (!Objects.equals(this.ukupanIznos, other.ukupanIznos)) {
            return false;
        }
        if (!Objects.equals(this.racunovodja, other.racunovodja)) {
            return false;
        }
        if (!Objects.equals(this.stavke, other.stavke)) {
            return false;
        }
        return Objects.equals(this.knjizara, other.knjizara);
    }

    @Override
    public String vratiNazivTabele() {
        return "faktura";
    }

    @Override
    public List<GenericEntity> vratiListu(ResultSet rs) throws Exception {
        List<GenericEntity> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idFaktura");
            LocalDate datumKreiranja = rs.getDate("datumKreiranja").toLocalDate();
            LocalDate rokIsplate = rs.getDate("rokIsplate").toLocalDate();
            double ukupanIznos = rs.getDouble("ukupanIznos");
            
            Racunovodja r = new Racunovodja();
            r.setIdRacunovodja(rs.getInt("racunovodja.idRacunovodja"));
            r.setIme(rs.getString("racunovodja.imeRacunovodje"));
            r.setPrezime(rs.getString("racunovodja.prezimeRacunovodje"));
            r.setKorisnickoIme(rs.getString("racunovodja.korisnickoIme"));
            r.setLozinka(rs.getString("racunovodja.lozinka"));
            r.setBrutoPlata(rs.getDouble("racunovodja.brutoPlata"));
            r.setKoeficijentRadnogStaza(rs.getDouble("racunovodja.koeficijentRadnogStaza"));
            r.setPocetnaPlata(rs.getDouble("racunovodja.pocetnaPlata"));
            
            Knjizara k = new Knjizara();
            k.setIdKnjizara(rs.getInt("knjizara.idKnjizara"));
            k.setNaziv(rs.getString("knjizara.nazivKnjizare"));
            k.setAdresa(rs.getString("knjizara.adresaKnjizare"));
            k.setEmail(rs.getString("knjizara.emailKnjizare"));
            
            Faktura f  = new Faktura(id, datumKreiranja, rokIsplate, ukupanIznos, r, k);
            lista.add(f);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "datumKreiranja,rokIsplate,ukupanIznos,idRacunovodja,idKnjizara";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + datumKreiranja + "', '" + rokIsplate + "', " + ukupanIznos + ", " + racunovodja.getIdRacunovodja() + ", " + knjizara.getIdKnjizara();
    }

    @Override
    public void vratiGenerisaniKljuc(int id) {
        this.idFaktura = id;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumKreiranja='" + datumKreiranja +
                "', rokIsplate='" + rokIsplate +
                "', ukupanIznos=" + ukupanIznos +
                ", idRacunovodja=" + racunovodja.getIdRacunovodja() +
                ", idKnjizara=" + knjizara.getIdKnjizara();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return " idFaktura=" + idFaktura;
    }
    
    
    
}
