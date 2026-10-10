/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjizara;

import domain.Knjizara;
import domain.KnjizaraPretraga;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class VratiListuKnjizaraSO extends AbstractSO {
    private List<Knjizara> lista;

    public List<Knjizara> getLista() {
        return lista;
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof KnjizaraPretraga)) {
            throw new Exception("Parametar mora biti tip KnjizaraPretraga.");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        KnjizaraPretraga k = (KnjizaraPretraga) param;

        StringBuilder uslov = new StringBuilder(" knjizara JOIN grad g ON knjizara.idGrad = g.idGrad WHERE 1=1");

        if (k.getNaziv() != null && !k.getNaziv().trim().isEmpty()) {
            uslov.append(" AND knjizara.nazivKnjizare LIKE '").append(k.getNaziv().trim()).append("%'");
        }
        if (k.getAdresa() != null && !k.getAdresa().trim().isEmpty()) {
            uslov.append(" AND knjizara.adresaKnjizare LIKE '").append(k.getAdresa().trim()).append("%'");
        }
        if (k.getEmail() != null && !k.getEmail().trim().isEmpty()) {
            uslov.append(" AND knjizara.emailKnjizare LIKE '").append(k.getEmail().trim()).append("%'");
        }
        if (k.getGrad() != null && k.getGrad().getIdGrad() > 0) {
            uslov.append(" AND g.idGrad = ").append(k.getGrad().getIdGrad());
        }

        lista = (List<Knjizara>) (List<?>) repository.getAll(new Knjizara(), uslov.toString());
    }
    
}
