/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Faktura;
import domain.FakturaPretraga;
import domain.Grad;
import domain.Knjiga;
import domain.Knjizara;
import domain.KnjizaraPretraga;
import domain.Racunovodja;
import domain.Smena;
import java.util.List;
import so.faktura.PretraziFakturaSO;
import so.faktura.PromeniFakturaSO;
import so.faktura.UbaciFakturaSO;
import so.faktura.VratiListuFakturaSO;
import so.grad.VratiListSviGrad;
import so.knjiga.VratiListuSviKnjigaSO;
import so.knjizara.ObrisiKnjizaraSO;
import so.knjizara.PretraziKnjizaraSO;
import so.knjizara.PromeniKnjizaraSO;
import so.knjizara.UbaciKnjizaraSO;
import so.knjizara.VratiListuKnjizaraSO;
import so.knjizara.VratiListuSviKnjizaraSO;
import so.login.PrijaviRacunovodjaSO;
import so.racunovodja.UbaciRacunovodjuSO;
import so.racunovodja.VratiListuSviRacunovodjaSO;
import so.smena.UbaciSmenaSO;

/**
 *
 * @author Dell
 */
public class Controller {
    private static Controller instance;

    public Controller() {
    }
    
    public static Controller getInstance(){
        if(instance==null){
            instance = new Controller();
        }
        return instance;
    }
    
    public Racunovodja prijaviRacunovodju(Racunovodja r) throws Exception {
        PrijaviRacunovodjaSO pv = new PrijaviRacunovodjaSO();
        pv.execute(r);
        return pv.getRacunovodja();
    }

    public List<Racunovodja> vratiListuSviRacunovodja() throws Exception {
        VratiListuSviRacunovodjaSO so = new VratiListuSviRacunovodjaSO();
        so.execute(null);
        return so.getLista();
    }

    public void ubaciRacunovodju(Racunovodja racunovodja) throws Exception {
        UbaciRacunovodjuSO so = new UbaciRacunovodjuSO();
        so.execute(racunovodja);
    }

    public List<Grad> vratiListuSviGrad() throws Exception {
        VratiListSviGrad so = new VratiListSviGrad();
        so.execute(null);
        return so.getLista();
    }

    public List<Knjiga> vratiListuSviKnjiga() throws Exception {
        VratiListuSviKnjigaSO so = new VratiListuSviKnjigaSO();
        so.execute(null);
        return so.getLista();
    }

    public List<Knjizara> vratiListuSviKnjizara() throws Exception {
        VratiListuSviKnjizaraSO so = new VratiListuSviKnjizaraSO();
        so.execute(null);
        return so.getLista();
    }

    public void ubaciKnjizaru(Knjizara knjizara) throws Exception {
        UbaciKnjizaraSO so = new UbaciKnjizaraSO();
        so.execute(knjizara);
    }

    public void obrisiKnjizaru(Knjizara k) throws Exception {
        ObrisiKnjizaraSO so = new ObrisiKnjizaraSO();
        so.execute(k);
    }

    public void promeniKnjizaru(Knjizara k) throws Exception {
        PromeniKnjizaraSO so = new PromeniKnjizaraSO();
        so.execute(k);
    }

    public Knjizara pretraziKnjizaru(Long idKnjizara) throws Exception {
        PretraziKnjizaraSO so = new PretraziKnjizaraSO();
        so.execute(idKnjizara);
        return so.getKnjizara();
    }

    public List<Knjizara> vratiListuKnjizara(KnjizaraPretraga kriterijum) throws Exception {
        VratiListuKnjizaraSO so = new VratiListuKnjizaraSO();
        so.execute(kriterijum);
        return so.getLista();
    }

    public void zapamtiFakturu(Faktura f) throws Exception {
        UbaciFakturaSO so = new UbaciFakturaSO();
        so.execute(f);
    }

    public void promeniFakturu(Faktura f) throws Exception {
        PromeniFakturaSO so = new PromeniFakturaSO();
        so.execute(f);
    }

    public Faktura pretraziFakturu(int idFaktura) throws Exception {
        PretraziFakturaSO so = new PretraziFakturaSO();
        so.execute(idFaktura);
        return so.getFaktura();
    }

    public List<Faktura> vratiListuFaktura(FakturaPretraga kriterijum) throws Exception {
        VratiListuFakturaSO so = new VratiListuFakturaSO();
        so.execute(kriterijum);
        return so.getFakture();
    }

    public void ubaciSmenu(Smena smena) throws Exception {
        UbaciSmenaSO so = new UbaciSmenaSO();
        so.execute(smena);
    }
    
    
}
