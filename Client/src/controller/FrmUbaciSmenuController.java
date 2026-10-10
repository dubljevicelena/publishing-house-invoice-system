/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import domain.Smena;
import forme.FrmUbaciSmenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class FrmUbaciSmenuController {
    private FrmUbaciSmenu frm;

    public FrmUbaciSmenuController(FrmUbaciSmenu frm) {
        this.frm = frm;
        addActionListener();
    }
    
    public void otvoriFormu(){
        frm.setVisible(true);
    }

    private void addActionListener() {
        frm.getBtnDodaj().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                Smena s = new Smena(); // Ili Smena, zavisi kako ti se zove klasa
                s.setBrojRadnihSati(Long.parseLong(frm.getTxtBrojRadnihSati().getText()));

                boolean odgovor = Communication.getInstance().ubaciSmena(s); // Prilagodi naziv metode u komunikaciji ako treba
                if(odgovor){
                    JOptionPane.showMessageDialog(frm, "Sistem je zapamtio smenu");
                    frm.dispose();
                } else {
                    JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti smenu");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti smenu.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frm, "Sistem ne može da zapamti smenu");
            }
            }
        
        });
    }
    
    
    
}
