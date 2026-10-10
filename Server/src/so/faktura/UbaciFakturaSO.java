/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.faktura;

import domain.Faktura;
import domain.StavkaFakture;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class UbaciFakturaSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof Faktura)){
            throw new Exception("Parametar mora biti tipa Faktura.");
        }
        Faktura faktura = (Faktura) param;
        
        if (faktura.getKnjizara() == null) {
            throw new Exception("Sistem ne može da zapamti fakturu.");
        }
        if (faktura.getDatumKreiranja() == null) {
            throw new Exception("Datum nije u dobrom formatu (YYYY-MM-DD).");
        }
        if (faktura.getRokIsplate() == null) {
            throw new Exception("Datum nije u dobrom formatu (YYYY-MM-DD).");
        }
        if (faktura.getRokIsplate().isBefore(faktura.getDatumKreiranja())) {
            throw new Exception("Rok isplate ne može biti pre datuma kreiranja.");
        }
        if (faktura.getUkupanIznos() < 0) {
            throw new Exception("Ukupan iznos mora biti veci od nule.");
        }
        if (faktura.getStavke() == null || faktura.getStavke().isEmpty()) {
            throw new Exception("Faktura mora imati bar jednu stavku.");
        }
        
        
        for (StavkaFakture sf : faktura.getStavke()) {
            if (sf.getKnjiga() == null) {
                throw new Exception("Svaka stavka mora imati izabranu knjigu.");
            }
            if (sf.getKolicina() <= 0) {
                throw new Exception("Broj knjiga po stavci mora biti veći od 0.");
            }
            if (sf.getCena() <= 0) {
                throw new Exception("Cena svake stavke mora biti veca od 0");
            }
        }

    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Faktura faktura = (Faktura) param;
        repository.add(faktura);
        for (StavkaFakture sf : faktura.getStavke())
        {
            sf.setFaktura(faktura);
            repository.add(sf);
        }
    }
    
}
