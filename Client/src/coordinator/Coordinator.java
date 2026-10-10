/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinator;

import communication.Communication;
import controller.FrmKreirajFakturuController;
import controller.FrmKreirajKnjizaruController;
import controller.FrmKreirajStavkuFaktureController;
import controller.FrmMainController;
import controller.FrmPretraziFaktureController;
import controller.FrmPretraziKnjizareController;
import controller.FrmPrijavaController;
import controller.FrmUbaciSmenuController;
import domain.Knjiga;
import domain.Racunovodja;
import domain.StavkaFakture;
import forme.FrmKreirajFakturu;
import forme.FrmKreirajKnjizaru;
import forme.FrmKreirajStavkuFakture;
import forme.FrmMain;
import forme.FrmPretraziFakture;
import forme.FrmPretraziKnjizare;
import forme.FrmPrijava;
import forme.FrmUbaciSmenu;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Coordinator {
    private static Coordinator instance;
    private Racunovodja ulogovaniRacunovodja;
    private FrmPrijavaController frmPrijavaController;
    private FrmMainController frmMainController;
    private FrmKreirajFakturuController frmKreirajFakturuController;
    private FrmPretraziFaktureController frmPretraziFaktureController;
    private FrmKreirajKnjizaruController frmKreirajKnjizaruController;
    private FrmPretraziKnjizareController frmPretraziKnjizareController;
    private FrmUbaciSmenuController frmUbaciSmenuController;
    
    public Coordinator() {
    }
    
    public static Coordinator getInstance() {
        if(instance == null){
            instance = new Coordinator();
        }
        return instance;
    }
    
    public Racunovodja getUlogovaniRacunovodja() {
        return ulogovaniRacunovodja;
    }

    public void setUlogovaniRacunovodja(Racunovodja r) {
        this.ulogovaniRacunovodja = r;
    }
    
    public void otvoriFrmPrijava() {
        frmPrijavaController = new FrmPrijavaController(new FrmPrijava());
        frmPrijavaController.otvoriFormu();
    }

    public void otvoriFrmMain() {
        frmMainController = new FrmMainController(new FrmMain());
        frmMainController.otvoriFormu();
    }
    
    public void otvoriFrmKreirajFakturu(){
        frmKreirajFakturuController = new FrmKreirajFakturuController(new FrmKreirajFakturu());
        frmKreirajFakturuController.otvoriFormu();
    }
    
    public void otvoriFrmPretraziFakture() {
        frmPretraziFaktureController = new FrmPretraziFaktureController(new FrmPretraziFakture());
        frmPretraziFaktureController.otvoriFormu();
    }
    
    public void otvoriFrmKreirajKnjizaru(){
        frmKreirajKnjizaruController = new FrmKreirajKnjizaruController(new FrmKreirajKnjizaru());
        frmKreirajKnjizaruController.otvoriFormu();
    }
    
    public void otvoriFrmPretraziKnjizare(){
        frmPretraziKnjizareController = new FrmPretraziKnjizareController(new FrmPretraziKnjizare());
        frmPretraziKnjizareController.otvoriFormu();
    }
    
    public void otvoriFrmUbaciSmenu(){
        frmUbaciSmenuController = new FrmUbaciSmenuController(new FrmUbaciSmenu());
        frmUbaciSmenuController.otvoriFormu();
    }
}
