/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.faktura;

import domain.Faktura;
import domain.FakturaPretraga;
import domain.StavkaFakture;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class VratiListuFakturaSO extends AbstractSO {
    private final List<Faktura> fakture = new ArrayList<>();

    public List<Faktura> getFakture() {
        return fakture;
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof FakturaPretraga)){
            throw new Exception("Parametar mora biti tipa FakturaPretraga.");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        FakturaPretraga p = (FakturaPretraga) param;

        StringBuilder uslov = new StringBuilder(" JOIN racunovodja ON faktura.idRacunovodja = racunovodja.idRacunovodja "
                + "JOIN knjizara ON faktura.idKnjizara = knjizara.idKnjizara "
                + " WHERE 1=1 "
        );

        // 1. Filtriranje po knjižari
        if (p.getKnjizara() != null) {
            uslov.append(" AND faktura.idKnjizara = ").append(p.getKnjizara().getIdKnjizara());
        }

        // 2. Filtriranje po računovođi
        if (p.getRacunovodja() != null) {
            uslov.append(" AND faktura.idRacunovodja = ").append(p.getRacunovodja().getIdRacunovodja());
        }

        // 3. Filtriranje po opsegu iznosa (samo ako su uneti iznosi veći od 0)
        if (p.getUkupanIznosOd() > 0 && p.getUkupanIznosDo() > 0) {
            uslov.append(" AND faktura.ukupanIznos BETWEEN ")
                 .append(p.getUkupanIznosOd()).append(" AND ").append(p.getUkupanIznosDo());
        } else if (p.getUkupanIznosOd() > 0) {
            uslov.append(" AND faktura.ukupanIznos >= ").append(p.getUkupanIznosOd());
        } else if (p.getUkupanIznosDo() > 0) {
            uslov.append(" AND faktura.ukupanIznos <= ").append(p.getUkupanIznosDo());
        }
        
            // 4. Filtriranje po opsegu datuma kreiranja
        if (p.getDatumKreiranjaOd() != null) {
            uslov.append(" AND faktura.datumKreiranja >= '").append(p.getDatumKreiranjaOd()).append("'");
        }
        if (p.getDatumKreiranjaDo() != null) {
            uslov.append(" AND faktura.datumKreiranja <= '").append(p.getDatumKreiranjaDo()).append("'");
        }

        // 5. Filtriranje po opsegu roka isplate
        if (p.getDatumIsplateOd() != null) {
            uslov.append(" AND faktura.rokIsplate >= '").append(p.getDatumIsplateOd()).append("'");
        }
        if (p.getDatumIsplateDo() != null) {
            uslov.append(" AND faktura.rokIsplate <= '").append(p.getDatumIsplateDo()).append("'");
        }

        List<Faktura> faktureF = (List<Faktura>) repository.getAll(new Faktura(), uslov.toString());
        fakture.clear();
        
        for (Faktura f : faktureF) {
            String uslovStavke = " JOIN knjiga ON stavkafakture.idKnjiga = knjiga.idKnjiga WHERE stavkafakture.idFaktura = " + f.getIdFaktura();
            List<StavkaFakture> stavke =(List<StavkaFakture>) repository.getAll(new StavkaFakture(), uslovStavke);
            f.setStavke(stavke);

            fakture.add(f);
        }
    }
    
}
