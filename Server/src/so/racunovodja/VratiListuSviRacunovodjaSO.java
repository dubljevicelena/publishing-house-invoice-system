/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racunovodja;

import domain.Racunovodja;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class VratiListuSviRacunovodjaSO extends AbstractSO {
    private List<Racunovodja> lista;

    public List<Racunovodja> getLista() {
        return lista;
    }

    @Override
    protected void precondition(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        lista = (List<Racunovodja>) (List<?>) repository.getAll(new Racunovodja(), " ORDER BY prezimeRacunovodje ASC, imeRacunovodje ASC");
    }
    
}
