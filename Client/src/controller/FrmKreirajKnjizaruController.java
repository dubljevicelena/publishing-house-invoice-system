/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import domain.Grad;
import domain.Knjizara;
import forme.FrmKreirajKnjizaru;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class FrmKreirajKnjizaruController {
    private final FrmKreirajKnjizaru frm;

    public FrmKreirajKnjizaruController(FrmKreirajKnjizaru frm) {
        this.frm = frm;
        addActionListener();
        popuniCmbGrad();
    }
    
    public void otvoriFormu() {
        frm.setVisible(true);
    }

    private void addActionListener() {
        
        frm.getBtnDodaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validateForm();
                    Knjizara k = new Knjizara();
                    k.setNaziv(frm.getTxtNaziv().getText());
                    k.setAdresa(frm.getTxtAdresa().getText());
                    k.setEmail(frm.getTxtEmail().getText());
                    k.setGrad((Grad) frm.getCmbGrad().getSelectedItem());

                    boolean odgovor = Communication.getInstance().ubaciKnjizara(k);
                    if (odgovor) {
                        JOptionPane.showMessageDialog(frm, "Sistem je zapamtio knjižaru.");
                        reset();
                    } else {
                        JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti knjižaru");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti knjižaru");
                }
            }

            private void reset() {
                frm.getTxtNaziv().setText("");
                frm.getTxtAdresa().setText("");
                frm.getTxtEmail().setText("");
                frm.getCmbGrad().setSelectedIndex(-1);
            }

            private void validateForm() throws Exception {
                String naziv = frm.getTxtNaziv().getText().trim();
                String adresa = frm.getTxtAdresa().getText().trim();
                String email = frm.getTxtEmail().getText().trim();

                if (frm.getCmbGrad().getSelectedIndex() == -1) {
                    throw new Exception("Morate izabrati grad iz liste.");
                }

                if (naziv.isEmpty()) {
                    throw new Exception("Naziv knjižare ne sme biti prazan.");
                }
                if (adresa.isEmpty()) {
                    throw new Exception("Adresa ne sme biti prazna.");
                }
                if (email.isEmpty() || !email.contains("@")) {
                    throw new Exception("Email mora sadržati znak '@'.");
                }
            }
        
        });
    }

    private void popuniCmbGrad() {
        try {
            frm.getCmbGrad().removeAllItems();
            List<Grad> gradovi = Communication.getInstance().vratiListuSviGrad();
            if (gradovi == null) {
                gradovi = new ArrayList<>();
            }
            for (Grad g : gradovi) {
                frm.getCmbGrad().addItem(g);
            }
            frm.getCmbGrad().setSelectedIndex(-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frm, "Greška pri učitavanju gradova: " + e.getMessage());
        }
    }
    
    
    
}
