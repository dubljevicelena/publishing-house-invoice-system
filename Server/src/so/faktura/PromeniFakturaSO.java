/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.faktura;

import domain.Faktura;
import domain.StavkaFakture;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class PromeniFakturaSO extends AbstractSO {

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
        repository.edit(faktura);

        String uslov = " JOIN knjiga ON stavkafakture.idKnjiga = knjiga.idKnjiga "
                + "WHERE idFaktura = " + faktura.getIdFaktura();
        List<StavkaFakture> stareStavke = (List<StavkaFakture>) repository.getAll(new StavkaFakture(), uslov);

        List<StavkaFakture> noveStavke = faktura.getStavke();

        for (StavkaFakture stara : stareStavke) {
            boolean postojalaUNovoj = false;
            for (StavkaFakture nova : noveStavke) {
                if (nova.getRedniBroj() == stara.getRedniBroj() && nova.getRedniBroj() > 0) {
                    postojalaUNovoj = true;
                    break;
                }
            }
            if (!postojalaUNovoj) {
                stara.setFaktura(faktura);
                repository.delete(stara);
            }
        }
        for (StavkaFakture nova : noveStavke) {
            nova.setFaktura(faktura);
            boolean postojalaUStaroj = false;
            for (StavkaFakture stara : stareStavke) {
                if (nova.getRedniBroj() == stara.getRedniBroj() && nova.getRedniBroj() > 0) {
                    postojalaUStaroj = true;
                    break;
                }
            }

            if (postojalaUStaroj) {
                repository.edit(nova);
            } else {
                nova.setRedniBroj(0);
                repository.add(nova);
            }
        }
    }
    
}
