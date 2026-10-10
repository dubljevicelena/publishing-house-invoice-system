/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.grad;

import domain.Grad;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class VratiListSviGrad extends AbstractSO {
    private List<Grad> lista;

    public List<Grad> getLista() {
        return lista;
    }

    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        lista = (List<Grad>) (List<?>) repository.getAll(new Grad(), " ORDER BY nazivGrada ASC");
    }
    
}
