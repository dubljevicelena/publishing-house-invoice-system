/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.Knjizara;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dell
 */
public class ModelKnjizara extends AbstractTableModel {
    
    private List<Knjizara> lista;
    private final String[] columnNames = {"ID", "Naziv", "Adresa", "Email", "Grad"};
    private Class[] classNames = {Integer.class,String.class,String.class,String.class,String.class};
    
    public ModelKnjizara(List<Knjizara> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Knjizara k = lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return k.getIdKnjizara();
            case 1:
                return k.getNaziv();
            case 2:
                return k.getAdresa();
            case 3:
                return k.getEmail();
            case 4:
                return k.getGrad();
            default:
                return "n/a";
        }
        
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classNames[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public List<Knjizara> getLista() {
        return lista;
    }
    
    public void setLista(List<Knjizara> lista) {
        this.lista = lista;
    }
    
    
}
