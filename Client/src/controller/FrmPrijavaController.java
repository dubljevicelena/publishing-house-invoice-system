/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import coordinator.Coordinator;
import domain.Racunovodja;
import forme.FrmPrijava;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class FrmPrijavaController {
    private final FrmPrijava frm;

    public FrmPrijavaController(FrmPrijava frm) {
        this.frm = frm;
        addActionListeners();
    }
    
    public void otvoriFormu(){
        frm.setVisible(true);
    }

    private void addActionListeners() {
        frm.loginAddActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                try {
                    String username = frm.getTxtUsername().getText().trim();
                    String password = String.valueOf(frm.getTxtPassword().getPassword());

                    Communication.getInstance().connect();
                    Racunovodja r = Communication.getInstance().prijaviRacunovodja(username, password); // Prilagođeno tvom domenu

                    if (r == null) {
                        JOptionPane.showMessageDialog(frm, "Korisničko ime i šifra nisu ispravni", "Greška", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frm, "Korisničko ime i šifra su ispravni");
                        //JOptionPane.showMessageDialog(frm, "Ne može da se otvori glavna forma i meni.", "Greška", JOptionPane.ERROR_MESSAGE);
                        Coordinator.getInstance().setUlogovaniRacunovodja(r);
                        Coordinator.getInstance().otvoriFrmMain();
                        frm.dispose();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frm, "Ne može da se otvori glavna forma i meni.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );
    }
}
