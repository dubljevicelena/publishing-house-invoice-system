/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import domain.Grad;
import domain.Knjizara;
import domain.KnjizaraPretraga;
import forme.FrmPretraziKnjizare;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modeli.ModelKnjizara;

/**
 *
 * @author Dell
 */
public class FrmPretraziKnjizareController {
    private final FrmPretraziKnjizare frm;

    public FrmPretraziKnjizareController(FrmPretraziKnjizare frm) {
        this.frm = frm;
        addActionListener();
        ucitajKnjizare();
        popuniCmbGrad();
    }
    
    public void otvoriFormu() {
        frm.setVisible(true);
    }

    private void addActionListener() {
        frm.getBtnPretrazi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretrazi();
            }
        });
        
        frm.getBtnIzmeni().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Knjizara k = frm.getK(); 
                if (k == null) {
                    JOptionPane.showMessageDialog(frm, "Sistem ne može da nađe knjižaru", "Greška", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                try {
                    validateForm();
                    String naziv = frm.getTxtNaziv().getText().trim();
                    String adresa = frm.getTxtAdresa().getText().trim();
                    String email = frm.getTxtEmail().getText().trim();
                    Grad grad = (Grad) frm.getCmbGrad().getSelectedItem();

                    Knjizara izmenjenaKnjizara = new Knjizara();
                    izmenjenaKnjizara.setIdKnjizara(k.getIdKnjizara()); // Prilagodi naziv ID metode ako se razlikuje (npr. getId())
                    izmenjenaKnjizara.setNaziv(naziv);
                    izmenjenaKnjizara.setAdresa(adresa);
                    izmenjenaKnjizara.setEmail(email);
                    izmenjenaKnjizara.setGrad(grad);

                    boolean uspesno = Communication.getInstance().promeniKnjizara(izmenjenaKnjizara);

                    if (uspesno) {
                        k.setNaziv(naziv);
                        k.setAdresa(adresa);
                        k.setEmail(email);
                        k.setGrad(grad);

                        JOptionPane.showMessageDialog(frm, "Sistem je zapamtio knjižaru");
                        ucitajKnjizare();
                    } else {
                        JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti knjižaru", "Greška", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti knjižaru", "Greška", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        frm.getTblKnjizare().addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int selektovaniRed = frm.getTblKnjizare().getSelectedRow();
        
                if (selektovaniRed != -1) {
                    try {
                        ModelKnjizara mt = (ModelKnjizara) frm.getTblKnjizare().getModel();
                        Knjizara k = mt.getLista().get(selektovaniRed);
                        frm.getTxtNaziv().setText(k.getNaziv());
                        frm.getTxtAdresa().setText(k.getAdresa());
                        frm.getTxtEmail().setText(k.getEmail());

                        if(k.getNaziv().isEmpty() || k.getAdresa().isEmpty() || k.getEmail().isEmpty() || k.getGrad() == null){
                            reset();
                            JOptionPane.showMessageDialog(frm, "Sistem ne može da nađe knjižaru.");
                            return;
                        }
                        
                        if (k.getGrad() != null) {
                            for (int i = 0; i < frm.getCmbGrad().getItemCount(); i++) {
                                Grad g = (Grad) frm.getCmbGrad().getItemAt(i);
                                if (g.getIdGrad() == k.getGrad().getIdGrad()) {
                                    frm.getCmbGrad().setSelectedIndex(i);
                                    break;
                                }
                            }
                           
                        }
                        frm.setK(k);
                        JOptionPane.showMessageDialog(frm, "Sistem je našao knjižaru");
                        //Sistem je našao knjižaru
                    } catch (Exception ex) {
                        //ex.printStackTrace();
                    }
                }
            }
            
        });
        
        frm.getBtnObrisi().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selektovaniRed = frm.getTblKnjizare().getSelectedRow(); // Prilagodi naziv tabele
                if (selektovaniRed == -1) {
                    JOptionPane.showMessageDialog(frm, "Sistem ne može da obriše knjižaru", "Greška", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int opcija = JOptionPane.showConfirmDialog(
                        frm, "Da li ste sigurni da želite da obrišete izabranu knjižaru?", "Potvrda brisanja", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (opcija == JOptionPane.YES_OPTION) {
                    try {
                        ModelKnjizara model = (ModelKnjizara) frm.getTblKnjizare().getModel();
                        Knjizara k = model.getLista().get(selektovaniRed);
                        boolean uspesno = Communication.getInstance().obrisiKnjizara(k);

                        if (uspesno) {
                            JOptionPane.showMessageDialog(frm, "Sistem je obrisao knjižaru");
                            ucitajKnjizare();
                            reset();
                        } else {
                            JOptionPane.showMessageDialog(frm, "Sistem ne može da obriše knjižaru", "Greška", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frm, "Sistem ne može da obriše knjižaru", "Greška", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        
        frm.getBtnReset().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frm.getTxtNaziv().setText("");
                frm.getTxtAdresa().setText("");
                frm.getTxtEmail().setText("");
                if (frm.getCmbGrad().getItemCount() > 0) {
                    frm.getCmbGrad().setSelectedIndex(-1);
                }
                frm.setK(null);
                ucitajKnjizare();
            }
        });
        
    }

    private void ucitajKnjizare() {
        try {
            List<Knjizara> knjizare = Communication.getInstance().vratiListuSviKnjizara();
            if (knjizare == null) {
                knjizare = new ArrayList<>();
            }
            ModelKnjizara mtk = new ModelKnjizara(knjizare);
            frm.getTblKnjizare().setModel(mtk);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frm, "Greška pri učitavanju knjižara");
        }
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
        }
    }
    
    private void pretrazi() {
        try {
            KnjizaraPretraga kriterijum = new KnjizaraPretraga();
            String naziv = frm.getTxtNaziv().getText().trim();
            String adresa = frm.getTxtAdresa().getText().trim();
            String email = frm.getTxtEmail().getText().trim();
            
            //kriterijum.setNaziv(naziv.isEmpty() ? null : "%" + naziv + "%");
            
            /*kriterijum.setNaziv(naziv.isEmpty() ? null : naziv);
            kriterijum.setAdresa(adresa.isEmpty() ? null : adresa);
            kriterijum.setEmail(email.isEmpty() ? null : email);*/
            
            kriterijum.setNaziv(naziv.isEmpty() ? null : "%" + naziv + "%");
            kriterijum.setAdresa(adresa.isEmpty() ? null : "%" + adresa + "%");
            kriterijum.setEmail(email.isEmpty() ? null : "%" + email + "%");

            Grad grad = (Grad) frm.getCmbGrad().getSelectedItem();
            kriterijum.setGrad(grad);

            List<Knjizara> rezultat = Communication.getInstance().vratiListuKnjizara(kriterijum);
            if (rezultat == null) {
                rezultat = new ArrayList<>();
            }

            ModelKnjizara mtk = new ModelKnjizara(rezultat);
            frm.getTblKnjizare().setModel(mtk);

            if (rezultat.isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Sistem ne može da nađe knjižare po zadatim kriterijumima");
            } else {
                JOptionPane.showMessageDialog(frm, "Sistem je našao knjižare po zadatim kriterijumima");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frm, "Sistem ne može da nađe knjižare po zadatim kriterijumima.");
        }
    }
    
    public void osveziTabelu() {
        ucitajKnjizare();
    }
    
    private void validateForm() throws Exception {
        String naziv = frm.getTxtNaziv().getText().trim();
        String adresa = frm.getTxtAdresa().getText().trim();
        String email = frm.getTxtEmail().getText().trim();

        if (frm.getCmbGrad().getSelectedIndex() == -1) {
            throw new Exception("Morate izabrati grad iz liste.");
        }

        if (naziv.isEmpty()) {
            throw new Exception("Naziv ne sme biti prazan.");
        }
        if (adresa.isEmpty()) {
            throw new Exception("Adresa ne sme biti prazna.");
        }
        if (email.isEmpty() || !email.contains("@")) {
            throw new Exception("Email mora sadržati znak '@'.");
        }
    }

    private void reset() {
        frm.getTxtNaziv().setText("");
        frm.getTxtAdresa().setText("");
        frm.getTxtEmail().setText("");
        frm.getCmbGrad().setSelectedIndex(-1);
        frm.setK(null);
    }
    
}
