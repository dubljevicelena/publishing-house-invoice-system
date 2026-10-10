/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.faktura;

import domain.Faktura;
import domain.GenericEntity;
import domain.Knjiga;
import domain.StavkaFakture;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class PretraziFakturaSO extends AbstractSO {
    private Faktura faktura;

    public Faktura getFaktura() {
        return faktura;
    }

    @Override
    protected void precondition(Object param) throws Exception{
        if(param == null || !(param instanceof Integer)){
            throw new Exception("Parametar mora biti tipa id fakture.");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        int idFaktura = (int) param;
        
        String uslov = " JOIN racunovodja ON faktura.idRacunovodja = racunovodja.idRacunovodja "
            + "JOIN knjizara ON faktura.idKnjizara = knjizara.idKnjizara "
            + " WHERE idFaktura = " + idFaktura;
        
        List<GenericEntity> objekti = repository.getAll(new Faktura(), uslov);
        if (objekti.isEmpty()) {
            throw new Exception("Faktura ne postoji");
        }
        faktura = (Faktura) objekti.get(0);
        
        
        String uslovStavke = " JOIN knjiga ON stavkafakture.idKnjiga = knjiga.idKnjiga "
                   + " WHERE idFaktura = " + idFaktura + " ORDER BY rbStavkeFakture";
        List<GenericEntity> stavkeObj = repository.getAll(new StavkaFakture(), uslovStavke);
        List<StavkaFakture> stavke = new java.util.ArrayList<>();
        
        for (GenericEntity g : stavkeObj) {
            stavke.add((StavkaFakture) g);
        }
        
        faktura.setStavke(stavke);
        /*
        for (StavkaFakture s : stavke) {
            int idKnjiga = s.getKnjiga().getIdKnjiga();
            String uslovV = " WHERE idKnjiga = " + idKnjiga;
            List<GenericEntity> knjige = repository.getAll(new Knjiga(), uslovV);
            if (!knjige.isEmpty()) {
                Knjiga k  = (Knjiga) knjige.get(0);
                s.setIdKnjiga(k);
            }
        }
        */
    }
    
}
