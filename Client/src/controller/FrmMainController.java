/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import coordinator.Coordinator;
import domain.Racunovodja;
import forme.FrmMain;

/**
 *
 * @author Dell
 */
public class FrmMainController {
    private final FrmMain frm;

    public FrmMainController(FrmMain frm) {
        this.frm = frm;
        addActionListeners();
    }
    
    public void otvoriFormu() {
        Racunovodja ulogovan = Coordinator.getInstance().getUlogovaniRacunovodja();
        frm.setVisible(true);
    }

    private void addActionListeners() {
    }
    
    
}
