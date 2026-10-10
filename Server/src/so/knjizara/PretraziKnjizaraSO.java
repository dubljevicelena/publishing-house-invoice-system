/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.knjizara;

import domain.GenericEntity;
import domain.Knjizara;
import domain.KnjizaraPretraga;
import java.util.ArrayList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author Dell
 */
public class PretraziKnjizaraSO extends AbstractSO {
    private Knjizara knjizara;
    private List<Knjizara> listaKnjizara;

    public List<Knjizara> getListaKnjizara() {
        return listaKnjizara;
    }

    public Knjizara getKnjizara() {
        return knjizara;
    }

    @Override
    protected void precondition(Object param) throws Exception {
        if(param == null || !(param instanceof KnjizaraPretraga)){
            throw new Exception("Parametar mora biti id knjizare.");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        
        
        
        /*int id = (int)param;
        String uslov = " JOIN grad g ON knjizara.idGrad=g.idGrad WHERE knjizara.idKnjizara="+id;
        
        List<GenericEntity> lista = repository.getAll(new Knjizara(), uslov);
        if(!lista.isEmpty()){
            knjizara = (Knjizara)lista.get(0);
            return;
        }
        throw new Exception("Knjizara sa ID " + id + " ne postoji");*/
        
        KnjizaraPretraga kriterijum = (KnjizaraPretraga) param;
        
        // Formiranje SQL uslova u zavisnosti od unetih kriterijuma sa forme
        String uslov = " JOIN grad g ON knjizara.idGrad=g.idGrad WHERE 1=1";
        
        if (kriterijum.getNaziv() != null) {
            uslov += " AND knjizara.nazivKnjizare LIKE '" + kriterijum.getNaziv() + "'";
        }
        if (kriterijum.getAdresa() != null) {
            uslov += " AND knjizara.adresaKnjizare LIKE '" + kriterijum.getAdresa() + "'";
        }
        if (kriterijum.getEmail() != null) {
            uslov += " AND knjizara.emailKnjizare LIKE '" + kriterijum.getEmail() + "'";
        }
        if (kriterijum.getGrad() != null) {
            uslov += " AND knjizara.idGrad = " + kriterijum.getGrad().getIdGrad();
        }
        
        String sql = "SELECT * FROM knjizara" + uslov;
        System.out.println("IZVRŠAVAM SQL UPIT: " + sql);
        
        List<GenericEntity> listaGeneric = repository.getAll(new Knjizara(), uslov);
        listaKnjizara = new ArrayList<>();
        for (GenericEntity ge : listaGeneric) {
            listaKnjizara.add((Knjizara) ge);
        }
    }
    
}
