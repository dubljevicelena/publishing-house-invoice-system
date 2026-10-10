/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import domain.Racunovodja;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class PrijaviRacunovodjaSO extends AbstractSO {
    Racunovodja racunovodja;

    public Racunovodja getRacunovodja() {
        return racunovodja;
    }

    public void setRacunovodja(Racunovodja racunovodja) {
        this.racunovodja = racunovodja;
    }

    @Override
    protected void precondition(Object param) {
        
    }

    @Override
    protected void executeOperation(Object param) {
        try {
            List<Racunovodja> racunovodje = repository.getAll((Racunovodja)param, null);
            
            for (Racunovodja r : racunovodje) {
                if(r.equals((Racunovodja)param)){
                    racunovodja = r;
                    return;
                }
            }
            racunovodja = null;
        } catch (Exception ex) {
            Logger.getLogger(PrijaviRacunovodjaSO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
