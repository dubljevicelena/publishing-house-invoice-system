/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.smena;

import domain.Smena;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class UbaciSmenaSO extends AbstractSO{

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Smena)) {
            throw new Exception("Parametar mora biti tip Smena");
        }

        Smena smena = (Smena) param;

        if (smena.getBrojRadnihSati() == null || smena.getBrojRadnihSati() <= 0) {
            throw new Exception("Sistem ne moze da zapamti smenu");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Smena smena = (Smena) param;
        repository.add(smena);
    }
    
}
