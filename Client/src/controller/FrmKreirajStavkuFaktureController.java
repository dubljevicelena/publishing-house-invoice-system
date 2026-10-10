/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import domain.Knjiga;
import domain.StavkaFakture;
import forme.FrmKreirajStavkuFakture;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;

/**
 *
 * @author Dell
 */
public class FrmKreirajStavkuFaktureController {
    private final FrmKreirajStavkuFakture frm;
    private StavkaFakture stavka;
    private boolean potvrdjeno = false;

    public FrmKreirajStavkuFaktureController(FrmKreirajStavkuFakture frm) {
        this.frm = frm;
        ucitajKnjige();
        addActionListeners();
    }

    public FrmKreirajStavkuFaktureController(FrmKreirajStavkuFakture frm, StavkaFakture postojecaStavka) {
        this.frm = frm;
        this.stavka = postojecaStavka;
        
        ucitajKnjige();
        popuniPoljaPostojecimPodacima();
        addActionListeners();
        frm.getBtnDodajStavku().setText("IZMENI");
    }
    
    

    private void addActionListeners() {
        for (ActionListener al : frm.getBtnDodajStavku().getActionListeners()) {
            frm.getBtnDodajStavku().removeActionListener(al);
        }
        
        frm.getCmbKnjiga().addActionListener(e -> izracunajCenuIIznos());
        
        frm.getTxtKolicina().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) { izracunajCenuIIznos();}

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) { izracunajCenuIIznos();}

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) { izracunajCenuIIznos();}
            
        });
        
        frm.getBtnDodajStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Knjiga k = (Knjiga) frm.getCmbKnjiga().getSelectedItem();
                if (k == null) {
                    JOptionPane.showMessageDialog(frm, "Izaberite knjigu!");
                    return;
                }

                String kolicinaTxt = frm.getTxtKolicina().getText().trim();
                if (kolicinaTxt.isEmpty()) {
                    JOptionPane.showMessageDialog(frm, "Unesite količinu!");
                    return;
                }

                try {
                    Long kolicina = Long.parseLong(kolicinaTxt);
                    if (kolicina <= 0) {
                        JOptionPane.showMessageDialog(frm, "Količina mora biti pozitivan broj!");
                        return;
                    }

                    double cena = k.getCena();
                    int rb = (stavka != null) ? stavka.getRedniBroj() : 0;

                    if (stavka == null) {
                        stavka = new StavkaFakture();
                    }

                    stavka.setRedniBroj(rb);
                    stavka.setIdKnjiga(k);
                    stavka.setKolicina(kolicina);
                    stavka.setCena(cena);
                    stavka.setIznosStavke(kolicina * cena);

                    potvrdjeno = true;
                    JOptionPane.showMessageDialog(frm, "Stavka je dodata!");
                    frm.setVisible(false);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frm, "Količina mora biti ceo broj!");
                }
                
            }
        });
    }
    

    private void ucitajKnjige() {
        try {
            frm.getCmbKnjiga().removeAllItems();
            List<Knjiga> knjige = Communication.getInstance().vratiListuSviKnjiga();
            if (knjige == null) {
            knjige = new ArrayList<>();
}
            for (Knjiga k : knjige) {
                frm.getCmbKnjiga().addItem(k);
            }
            
            frm.getCmbKnjiga().setSelectedIndex(-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frm, "Greška pri učitavanju knjiga: " + e.getMessage());
        }
    }

    private void popuniPoljaPostojecimPodacima() {
        if (stavka != null) {
            frm.getTxtKolicina().setText(String.valueOf(stavka.getKolicina()));
            frm.getTxtCena().setText(String.valueOf(stavka.getCena()));
            frm.getTxtUkupanIznos().setText(String.valueOf(stavka.getIznosStavke()));

            if (stavka.getKnjiga() != null) {
                for (int i = 0; i < frm.getCmbKnjiga().getItemCount(); i++) {
                    Knjiga k = (Knjiga) frm.getCmbKnjiga().getItemAt(i);
                    if (k != null && k.getIdKnjiga() == stavka.getKnjiga().getIdKnjiga()) {
                        frm.getCmbKnjiga().setSelectedIndex(i);
                        break;
                    }
                }
            }
        }
    }
    
    public StavkaFakture getStavka() {
        return potvrdjeno ? stavka : null;
    }

    public void otvoriFormu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void izracunajCenuIIznos() {
        try {
            Knjiga izabranaKnjiga = (Knjiga) frm.getCmbKnjiga().getSelectedItem();
            if (izabranaKnjiga == null) return;

            double cena = izabranaKnjiga.getCena();
            frm.getTxtCena().setText(String.valueOf(cena));

            String kolicinaText = frm.getTxtKolicina().getText().trim();
            if (!kolicinaText.isEmpty()) {
                long kolicina = Long.parseLong(kolicinaText);
                double ukupanIznos = cena * kolicina;
                frm.getTxtUkupanIznos().setText(String.valueOf(ukupanIznos));
            } else {
                frm.getTxtUkupanIznos().setText("");
            }
        } catch (Exception ex) {
            frm.getTxtUkupanIznos().setText("");
        }
    }
}
