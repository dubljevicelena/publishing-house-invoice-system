/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjizara;

import domain.Knjizara;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class ObrisiKnjizaraSO extends AbstractSO{

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Knjizara)){
            throw new Exception("Parametar mora biti tip Knjizara");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Knjizara knjizara = (Knjizara)param;
        repository.delete(knjizara);
    }
    
}
