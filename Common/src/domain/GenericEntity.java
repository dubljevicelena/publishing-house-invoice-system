/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface GenericEntity extends Serializable{

    public String vratiNazivTabele();
    public List<GenericEntity> vratiListu(ResultSet rs) throws Exception;
    public String vratiKoloneZaUbacivanje();
    public String vratiVrednostiZaUbacivanje();
    public void vratiGenerisaniKljuc(int generisaniId);
    public String vratiVrednostiZaIzmenu();
    public String vratiPrimarniKljuc();
    
}
