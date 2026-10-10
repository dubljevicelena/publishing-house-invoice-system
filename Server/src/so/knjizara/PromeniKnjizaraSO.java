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
public class PromeniKnjizaraSO extends AbstractSO {

    @Override
    protected void precondition(Object param) throws Exception {
        if (param == null || !(param instanceof Knjizara)) {
        throw new Exception("Parametar mora biti tip Knjizara.");
        }

        Knjizara knjizara = (Knjizara) param;

        if (knjizara.getNaziv().isEmpty() || knjizara.getAdresa().isEmpty() || 
            knjizara.getEmail().isEmpty() || knjizara.getGrad() == null) {
            throw new Exception("Sistem ne moze da zapamti knjizaru");
        }

        if (!knjizara.getEmail().contains("@")) {
            throw new Exception("Email adresa mora sadrzati karakter '@'");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Knjizara k = (Knjizara) param;
        Knjizara nova = new Knjizara();
        nova.setIdKnjizara(k.getIdKnjizara());
        nova.setNaziv(k.getNaziv());
        nova.setAdresa(k.getAdresa());
        nova.setEmail(k.getEmail());
        nova.setGrad(k.getGrad());

        repository.edit(nova);
    }
    
}
