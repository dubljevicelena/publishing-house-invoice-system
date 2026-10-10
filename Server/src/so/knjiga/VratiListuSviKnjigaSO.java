/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjiga;

import domain.Knjiga;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class VratiListuSviKnjigaSO extends AbstractSO {
    private List<Knjiga> lista;

    public List<Knjiga> getLista() {
        return lista;
    }

    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        lista = (List<Knjiga>) (List<?>) repository.getAll(new Knjiga(), " ORDER BY nazivKnjige ASC");
    }
    
}
