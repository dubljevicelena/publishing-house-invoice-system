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
public class UbaciKnjizaraSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Knjizara)) {
            throw new Exception("Parametar mora biti tip Knjizara");
        }
        Knjizara k = (Knjizara) param;

        if (k.getNaziv() == null || k.getNaziv().trim().isEmpty()) {
            throw new Exception("Naziv ne sme biti null niti prazan.");
        }
        if (k.getAdresa() == null || k.getAdresa().trim().isEmpty()) {
            throw new Exception("Adresa ne sme biti null niti prazna.");
        }

        if (k.getEmail() == null || !k.getEmail().contains("@")) {
            throw new Exception("Email ne sme biti null i mora sadržati karakterski znak '@'.");
        }

        if (k.getGrad() == null) {
            throw new Exception("Grad ne sme biti null.");
        }

        List<Knjizara> sveKnjizare = (List<Knjizara>) (List<?>) repository.getAll(new Knjizara(), " JOIN grad ON knjizara.idGrad = grad.idGrad");
        for (Knjizara postojeca : sveKnjizare) {
            if (postojeca.getEmail() != null && postojeca.getEmail().equalsIgnoreCase(k.getEmail().trim())) {
                throw new Exception("Knjizara sa unetim email-om vec postoji u sistemu");
            }
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Knjizara knjizara = (Knjizara) param;
        repository.add(knjizara);
    }
    
}
