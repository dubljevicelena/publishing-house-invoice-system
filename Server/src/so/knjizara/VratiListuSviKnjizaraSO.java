/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjizara;

import domain.Knjizara;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class VratiListuSviKnjizaraSO extends AbstractSO {
    private List<Knjizara> lista;

    public List<Knjizara> getLista() {
        return lista;
    }

    @Override
    protected void precondition(Object param) throws Exception {
        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        lista = (List<Knjizara>) (List<?>) repository.getAll(new Knjizara(), " knjizara JOIN grad g ON knjizara.idGrad = g.idGrad");
    }
    
}
