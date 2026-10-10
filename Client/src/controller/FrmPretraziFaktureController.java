/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import coordinator.Coordinator;
import domain.Faktura;
import domain.FakturaPretraga;
import domain.Knjiga;
import domain.Knjizara;
import domain.Racunovodja;
import domain.StavkaFakture;
import forme.FrmKreirajStavkuFakture;
import forme.FrmPretraziFakture;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modeli.ModelFaktura;
import modeli.ModelStavkaFakture;

/**
 *
 * @author Dell
 */
public class FrmPretraziFaktureController {
    
    private final FrmPretraziFakture frm;
    private final ModelFaktura model;
    private final ModelStavkaFakture modelStavka;
    private List<Knjizara> knjizare;
    private List<Knjiga> knjige;
    private List<Racunovodja> racunovodje;
    private Faktura selektovanaFaktura;

    public FrmPretraziFaktureController(FrmPretraziFakture frm) {
        this.frm = frm;
        this.model = new ModelFaktura();
        this.modelStavka = new ModelStavkaFakture();
        
        frm.getTblFakture().setModel(model);
        frm.getTblStavke().setModel(modelStavka);
        
        ucitajListe();
        addActionListeners();
        
        frm.getTxtDatumKreiranjaOd().setText("");
        frm.getTxtDatumKreiranjaDo().setText("");
        frm.getTxtRokIsplateOd().setText("");
        frm.getTxtRokIsplateDo().setText("");
        frm.getTxtUkupanIznosOd().setText("");
        frm.getTxtUkupanIznosDo().setText("");
        pretrazi(false);
        frm.setVisible(true);
    }
    
    public void otvoriFormu() {
        frm.setVisible(true);
    }

    private void ucitajListe() {
        try {
            knjizare = Communication.getInstance().vratiListuSviKnjizara();
            racunovodje = Communication.getInstance().vratiListuSviRacunovodja();

            frm.getCmbKnjizara().removeAllItems();
            for (Knjizara k : knjizare) {
                frm.getCmbKnjizara().addItem(k);
            }

            frm.getCmbRacunovodja().removeAllItems();
            for (Racunovodja r : racunovodje) {
                frm.getCmbRacunovodja().addItem(r);
            }

            frm.getCmbKnjizara().setSelectedIndex(-1);
            frm.getCmbRacunovodja().setSelectedIndex(-1);

            model.setKnjizare(knjizare);
            model.setImeRacunovodje(Coordinator.getInstance().getUlogovaniRacunovodja().toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frm, "Greška: " + e.getMessage());
        }
    }

    private void addActionListeners() {
        frm.getBtnDodajStavku().addActionListener(e -> dodajStavku());
        frm.getBtnIzmeniStavku().addActionListener(e -> izmeniStavku());
        frm.getBtnObrisiStavku().addActionListener(e -> obrisiStavku());
        frm.getBtnPretrazi().addActionListener(e -> pretrazi(true));
        frm.getBtnIzmeni().addActionListener(e -> sacuvajIzmene());
        frm.getBtnReset().addActionListener(e -> resetujFiltere());
        frm.getTblFakture().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = frm.getTblFakture().getSelectedRow();
                if (selectedRow >= 0) {
                    Faktura izabrana = model.getFaktura(selectedRow);
                    
                    
                    try {
                        Faktura detaljnaFaktura = Communication.getInstance().pretraziFaktura(izabrana.getIdFaktura());
                        if (detaljnaFaktura != null) {
                            //&& detaljnaFaktura.getStavke() != null && !detaljnaFaktura.getStavke().isEmpty()
                            selektovanaFaktura = detaljnaFaktura;
                        } else {
                            selektovanaFaktura = izabrana;
                        }
                        JOptionPane.showMessageDialog(frm, "Sistem je našao fakturu.");
                        
                    } catch (Exception ex) {
                        selektovanaFaktura = izabrana;
                        JOptionPane.showMessageDialog(frm, "Sistem ne može da nađe fakturu.");
                    }
                    popuniPodatkeZaIzmenu(selektovanaFaktura);
                }
            }
        });
    }

    private void pretrazi(boolean prikaziPoruku) {
        try {
            FakturaPretraga kriterijum = new FakturaPretraga();

            Knjizara k = (Knjizara) frm.getCmbKnjizara().getSelectedItem();
            if (k != null) {
                kriterijum.setKnjizara(k);
            }

            Racunovodja r = (Racunovodja) frm.getCmbRacunovodja().getSelectedItem();
            if (r != null) {
                kriterijum.setRacunovodja(r);
            }
            
            String datumKreiranjaOd = frm.getTxtDatumKreiranjaOd().getText().trim();
            if (!datumKreiranjaOd.isEmpty()) {
                try {
                    kriterijum.setDatumKreiranjaOd(LocalDate.parse(datumKreiranjaOd));
                } catch (Exception e) {
                    kriterijum.setDatumKreiranjaOd(null);
                }
            }

            String datumKreiranjaDo = frm.getTxtDatumKreiranjaDo().getText().trim();
            if (!datumKreiranjaDo.isEmpty()) {
                try {
                    kriterijum.setDatumKreiranjaDo(LocalDate.parse(datumKreiranjaDo));
                } catch (Exception e) {
                    kriterijum.setDatumKreiranjaDo(null);
                }
            }

            String iznosOd = frm.getTxtUkupanIznosOd().getText().trim();
            String iznosDo = frm.getTxtUkupanIznosDo().getText().trim();
            
            if (!iznosOd.isEmpty()) {
                try {
                    kriterijum.setUkupanIznosOd(Double.parseDouble(iznosOd));
                } catch (NumberFormatException e) {
                    kriterijum.setUkupanIznosOd(0.0);
                }
            }

            if (!iznosDo.isEmpty()) {
                try {
                    kriterijum.setUkupanIznosDo(Double.parseDouble(iznosDo));
                } catch (NumberFormatException e) {
                    kriterijum.setUkupanIznosDo(0.0); 
                }
            }

            List<Faktura> rezultat = Communication.getInstance().vratiListuFaktura(kriterijum);

            if (rezultat == null) {
                rezultat = new ArrayList<>();
            }
            
            model.setLista(rezultat);
            
            if (prikaziPoruku) {
                if (!rezultat.isEmpty()) {
                    JOptionPane.showMessageDialog(frm, "Sistem je našao fakture po zadatim kriterijumima.");
                } else {
                    JOptionPane.showMessageDialog(frm, "Sistem ne može da nađe fakture po zadatim kriterijumima.");
                }
            }
            
        } catch (Exception e) {
            if (prikaziPoruku) {
                JOptionPane.showMessageDialog(frm, "Sistem ne može da nađe fakture po zadatim kriterijumima.");
            }
        }
    }

    private void dodajStavku() {
        
        int selektovaniRed = frm.getTblFakture().getSelectedRow();
        if (selektovaniRed == -1) {
            JOptionPane.showMessageDialog(frm, "Sistem ne može da nađe fakturu. Morate selektovati fakturu.");
            return;
        }
        int maxRb = 0;
        if (modelStavka.getStavke() != null) {
            for (StavkaFakture s : modelStavka.getStavke()) {
                if (s.getRedniBroj() > maxRb) {
                    maxRb = s.getRedniBroj();
                }
            }
        }
        
        List<Knjiga> knjige = null;
        try {
            knjige = Communication.getInstance().vratiListuSviKnjiga();
        } catch (Exception e) {
            knjige = new ArrayList<>();
        }
        if (knjige == null) {
            knjige = new ArrayList<>();
        }
        int sledeciRb = maxRb + 1;

        FrmKreirajStavkuFakture dialog = new FrmKreirajStavkuFakture(frm, true, knjige, sledeciRb);
        FrmKreirajStavkuFaktureController dialogController = new FrmKreirajStavkuFaktureController(dialog);
        dialog.setVisible(true);

        StavkaFakture nova = dialogController.getStavka();
        if (nova != null && nova.getKnjiga() != null) {
            Knjiga izabranaKnjiga = nova.getKnjiga();
            StavkaFakture postojecaStavka = null;

            if (modelStavka.getStavke() != null) {
                for (StavkaFakture s : modelStavka.getStavke()) {
                    if (s.getKnjiga() != null && s.getKnjiga().getIdKnjiga() == izabranaKnjiga.getIdKnjiga()) {
                        postojecaStavka = s;
                        break;
                    }
                }
            }

            if (postojecaStavka != null) {
                Long novaKolicina = (postojecaStavka.getKolicina() + nova.getKolicina());
                postojecaStavka.setKolicina(novaKolicina);
                postojecaStavka.setCena(novaKolicina * izabranaKnjiga.getCena());
            } else {
                nova.setRedniBroj(sledeciRb);
                modelStavka.dodajStavku(nova);
            }

            modelStavka.fireTableDataChanged();
            izracunajUkupno();
        }

        dialog.dispose();
    }

    private void izmeniStavku() {
        int red = frm.getTblStavke().getSelectedRow();
        
        if (red < 0) {
            JOptionPane.showMessageDialog(frm, "Izaberite stavku iz tabele koju želite da izmenite.");
            return;
        }
        
        List<Knjiga> knjige = null;
        try {
            knjige = Communication.getInstance().vratiListuSviKnjiga();
        } catch (Exception e) {
            knjige = new ArrayList<>();
        }
        if (knjige == null) {
            knjige = new ArrayList<>();
        }
        
        StavkaFakture izabranaStavka = modelStavka.getStavkaAt(red);
        FrmKreirajStavkuFakture dialog = new FrmKreirajStavkuFakture(frm, true, knjige, izabranaStavka.getRedniBroj());
        FrmKreirajStavkuFaktureController dialogController = new FrmKreirajStavkuFaktureController(dialog, izabranaStavka);
        dialog.setVisible(true);

        StavkaFakture izmenjena = dialogController.getStavka();
        if (izmenjena != null) {
            modelStavka.izmeniStavku(red, izmenjena);
            modelStavka.fireTableDataChanged();
            izracunajUkupno();
        }

        dialog.dispose();
    }
    
    public void osveziPretragu() {
        pretrazi(false);
    }

    private void obrisiStavku() {
        int red = frm.getTblStavke().getSelectedRow();
        
        if (red >= 0) {
            modelStavka.obrisiStavku(red);
            modelStavka.fireTableDataChanged();
            izracunajUkupno();
            JOptionPane.showMessageDialog(frm, "Stavka je obrisana!");
        } else {
            JOptionPane.showMessageDialog(frm, "Izaberite stavku za brisanje!");
        }
    }

    private void sacuvajIzmene() {
        try {
            if (selektovanaFaktura == null) {
                JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti fakturu.");
                return;
            }

            if (modelStavka.getRowCount() == 0) {
                JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti fakturu.");
                return;
            }

            Knjizara knjizara = (Knjizara) frm.getCmbKnjizara().getSelectedItem();
            Racunovodja racunovodja = (Racunovodja) frm.getCmbRacunovodja().getSelectedItem();
            
            selektovanaFaktura.setKnjizara(knjizara);
            selektovanaFaktura.setRacunovodja(racunovodja);
            selektovanaFaktura.setStavke(modelStavka.getStavke());
            izracunajUkupno();

            boolean uspeh = Communication.getInstance().promeniFaktura(selektovanaFaktura);

            if (uspeh) {
                JOptionPane.showMessageDialog(frm, "Sistem je zapamtio fakturu.");
                
                resetujFiltere();
            } else {
                JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti fakturu.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti fakturu.");
        }
    }

    private void resetujFiltere() {
        frm.getTxtDatumKreiranjaOd().setText("");
        frm.getTxtDatumKreiranjaDo().setText("");
        frm.getTxtUkupanIznosOd().setText("");
        frm.getTxtUkupanIznosDo().setText("");
        frm.getTxtRokIsplateOd().setText("");
        frm.getTxtRokIsplateDo().setText("");

        frm.getTxtDatumKreiranjaOd().setEditable(true);
        frm.getTxtDatumKreiranjaDo().setEditable(true);
        frm.getTxtUkupanIznosOd().setEditable(true);
        frm.getTxtUkupanIznosDo().setEditable(true);

        frm.getCmbKnjizara().setEnabled(true);
        frm.getCmbRacunovodja().setEnabled(true);

        frm.getCmbKnjizara().setSelectedIndex(-1);
        frm.getCmbRacunovodja().setSelectedIndex(-1);

        pretrazi(false);
        selektovanaFaktura = null;

        if (modelStavka.getStavke() != null) {
            modelStavka.getStavke().clear();
        } else {
            modelStavka.setStavke(new ArrayList<>());
        }
        modelStavka.fireTableDataChanged();
    }

    private void popuniPodatkeZaIzmenu(Faktura f) {
        if (f.getDatumKreiranja() != null) {
            frm.getTxtDatumKreiranjaOd().setText(f.getDatumKreiranja().toString());
        }
        frm.getTxtDatumKreiranjaOd().setEditable(false);
        frm.getTxtDatumKreiranjaDo().setEditable(false);
        frm.getTxtUkupanIznosOd().setEditable(false);
        frm.getTxtUkupanIznosDo().setEditable(false);

        frm.getCmbKnjizara().setEnabled(false);
        frm.getCmbKnjizara().setSelectedIndex(-1);

        if (knjizare != null && f.getKnjizara() != null) {
            for (int i = 0; i < knjizare.size(); i++) {
                if (knjizare.get(i).getIdKnjizara() == f.getKnjizara().getIdKnjizara()) {
                    frm.getCmbKnjizara().setSelectedIndex(i);
                    break;
                }
            }
        }

        if (racunovodje != null && f.getRacunovodja() != null) {
            for (int i = 0; i < racunovodje.size(); i++) {
                if (racunovodje.get(i).getIdRacunovodja() == f.getRacunovodja().getIdRacunovodja()) {
                    frm.getCmbRacunovodja().setSelectedIndex(i);
                    break;
                }
            }
        }

        if (f.getStavke() != null) {
            modelStavka.setStavke(f.getStavke());
        } else {
            modelStavka.setStavke(new ArrayList<>());
        }
        modelStavka.fireTableDataChanged();
    }

    private void izracunajUkupno() {
        if (selektovanaFaktura == null) {
            return;
        }

        if (modelStavka.getStavke() != null && !modelStavka.getStavke().isEmpty()) {
            double ukupno = 0;
            for (StavkaFakture s : modelStavka.getStavke()) {
                ukupno += s.getCena(); 
            }
            ukupno = Math.round(ukupno * 100.0) / 100.0;

            selektovanaFaktura.setUkupanIznos(ukupno);
            selektovanaFaktura.setStavke(modelStavka.getStavke());
        } else {
            selektovanaFaktura.setUkupanIznos(0.0);
            selektovanaFaktura.setStavke(new ArrayList<>());
        }
    }
    
    
    
}
