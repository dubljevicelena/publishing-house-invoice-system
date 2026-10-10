/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import coordinator.Coordinator;
import domain.Faktura;
import domain.Knjiga;
import domain.Knjizara;
import domain.StavkaFakture;
import forme.FrmKreirajFakturu;
import forme.FrmKreirajStavkuFakture;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeli.ModelStavkaFakture;

/**
 *
 * @author Dell
 */
public class FrmKreirajFakturuController {
    
    private final FrmKreirajFakturu frm;
    private final ModelStavkaFakture modelStavke;
    private List<Knjizara> knjizare;
    private List<Knjiga> knjige;

    public FrmKreirajFakturuController(FrmKreirajFakturu frm) {
        this.frm = frm;
        this.modelStavke = new ModelStavkaFakture();
        
        frm.getTblStavke().setModel(modelStavke);
        frm.getTxtDatumKreiranja().setText(LocalDate.now().toString());
        frm.getTxtDatumKreiranja().setEditable(false);
        frm.getTxtUkupanIznos().setEditable(false);

        addActionListeners();
        ucitajListe();
        postaviRacunovodju();

        frm.setVisible(true);
    }
    
    public void otvoriFormu() {
        frm.setVisible(true);
    }

    private void addActionListeners() {
        frm.getBtnDodajStavku().addActionListener(e -> {
            try {
                dodajStavku();
            } catch (Exception ex) {
                Logger.getLogger(FrmKreirajFakturuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        frm.getBtnDodaj().addActionListener(e -> sacuvajFakturu());
        frm.getBtnObrisiStavku().addActionListener(e -> obrisiStavku());
    }

    private void ucitajListe() {
        try {
            List<Knjizara> knjizare = Communication.getInstance().vratiListuSviKnjizara();

            for (Knjizara k : knjizare) {
                frm.getCmbKnjizara().addItem(k);
            }
            frm.getCmbKnjizara().setSelectedIndex(-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frm, "Greška pri učitavanju knjižara: " + e.getMessage());
        }
    }

    private void postaviRacunovodju() {
        String ime = Coordinator.getInstance().getUlogovaniRacunovodja().getIme() + " "
            + Coordinator.getInstance().getUlogovaniRacunovodja().getPrezime();
    frm.getLblRacunovodja().setText(ime);
    }
    
    
    

    private void dodajStavku() throws Exception {
        int sledeciRb = modelStavke.getRowCount() + 1;
        knjige = Communication.getInstance().vratiListuSviKnjiga();
        FrmKreirajStavkuFakture dialog = new FrmKreirajStavkuFakture(frm, true, knjige, sledeciRb);

        FrmKreirajStavkuFaktureController dialogController = new FrmKreirajStavkuFaktureController(dialog);
        dialog.setVisible(true);

        StavkaFakture nova = dialogController.getStavka();
        if (nova != null && nova.getKnjiga() != null) {
            StavkaFakture postojecaStavka = null;

            for (StavkaFakture s : modelStavke.getStavke()) {
                if (s.getKnjiga().getIdKnjiga() == nova.getKnjiga().getIdKnjiga()) {
                    postojecaStavka = s;
                    break;
                }
            }

            if (postojecaStavka != null) {
                long novaKolicina = postojecaStavka.getKolicina() + nova.getKolicina();
                postojecaStavka.setKolicina(novaKolicina);
                postojecaStavka.setIznosStavke(novaKolicina * postojecaStavka.getCena());
                modelStavke.fireTableDataChanged();
            } else {
                nova.setRedniBroj(modelStavke.getRowCount() + 1);
                modelStavke.dodajStavku(nova);
            }

            izracunajUkupno();
        }

    dialog.dispose();
    }
    
    private void obrisiStavku() {
        int red = frm.getTblStavke().getSelectedRow();
        if (red >= 0) {
            modelStavke.obrisiStavku(red);
            izracunajUkupno();
            JOptionPane.showMessageDialog(frm, "Stavka je obrisana!");
        } else {
            JOptionPane.showMessageDialog(frm, "Izaberite stavku za brisanje");
        }
    }

    private void sacuvajFakturu() {
        try {
            Knjizara knjizara = (Knjizara) frm.getCmbKnjizara().getSelectedItem();
            if (knjizara == null) {
                JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti fakturu.");
                return;
            }

            LocalDate datumKreiranja = LocalDate.now();

            String rokTekst = frm.getTxtDatumIsplate().getText().trim();
            LocalDate rokIsplate = datumKreiranja.parse(rokTekst); 

            String txtUkupnoStr = frm.getTxtUkupanIznos().getText().trim();
            double ukupanIznos = txtUkupnoStr.isEmpty() ? 0.0 : Double.parseDouble(txtUkupnoStr);

            Faktura faktura = new Faktura();
            faktura.setDatumKreiranja(datumKreiranja);
            faktura.setRokIsplate(rokIsplate);
            faktura.setUkupanIznos(ukupanIznos);
            faktura.setKnjizara(knjizara);
            faktura.setRacunovodja(Coordinator.getInstance().getUlogovaniRacunovodja());

            faktura.setStavke(modelStavke.getStavke());

            boolean uspeh = Communication.getInstance().kreirajFaktura(faktura);

            if (uspeh) {
                JOptionPane.showMessageDialog(frm, "Sistem je zapamtio fakturu.");
                frm.dispose();
            } else {
                JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti fakturu.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti fakturu.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti fakturu.");
        }
    }

    private void izracunajUkupno() {
        double ukupanIznos = 0.0;
        for (StavkaFakture s : modelStavke.getStavke()) {
            ukupanIznos += s.getIznosStavke();
        }
        frm.getTxtUkupanIznos().setText(String.valueOf(ukupanIznos));
    }

    
    
    
    
    
    
}
