/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racunovodja;

import domain.Racunovodja;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class UbaciRacunovodjuSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Racunovodja)) {
            throw new Exception("Parametar mora biti tip Racunovodja");
        }

        Racunovodja r = (Racunovodja) param;

        if (r.getIme() == null || r.getIme().trim().isEmpty()) {
            throw new Exception("Ime ne sme biti null niti prazno");
        }
        if (r.getPrezime() == null || r.getPrezime().trim().isEmpty()) {
            throw new Exception("Prezime ne sme biti null niti prazno");
        }
        if (r.getKorisnickoIme() == null || r.getKorisnickoIme().trim().isEmpty()) {
            throw new Exception("Korisničko ime ne sme biti null niti prazno");
        }
        if (r.getLozinka() == null || r.getLozinka().trim().isEmpty()) {
            throw new Exception("Lozinka ne sme biti null niti prazna");
        }
        if (r.getBrutoPlata() <= 0) {
            throw new Exception("Bruto plata mora biti veća od 0");
        }
        if (r.getKoeficijentRadnogStaza() <= 0) {
            throw new Exception("Koeficijent radnog staža mora biti veći od 0.");
        }
        if (r.getPocetnaPlata() <= 0) {
            throw new Exception("Početna plata mora biti veća od 0.");
        }
        
        List<Racunovodja> sviRacunovodje = (List<Racunovodja>) (List<?>) repository.getAll(new Racunovodja(), "");
        for (Racunovodja postojeci : sviRacunovodje) {
            if (postojeci.getKorisnickoIme() != null 
                    && postojeci.getKorisnickoIme().equalsIgnoreCase(r.getKorisnickoIme().trim())) {
                throw new Exception("Računovođa sa unetim korisničkim imenom već postoji u sistemu.");
            }
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Racunovodja racunovodja = (Racunovodja) param;
        repository.add(racunovodja);
    }
    
}
