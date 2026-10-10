/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.StavkaFakture;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dell
 */
public class ModelStavkaFakture extends AbstractTableModel{
    List<StavkaFakture> stavke;
    private String[] columnNames = {"redni broj","kolicina","iznos stavke","cena","knjiga"};
    private Class[] classNames = {Long.class,int.class,Double.class,Double.class,String.class};

    public ModelStavkaFakture() {
        this.stavke = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaFakture s = stavke.get(rowIndex);
        switch(columnIndex){
            case 0:
                return s.getRedniBroj();
            case 1:
                return s.getKolicina();
            case 2:
                return s.getIznosStavke();
            case 3:
                return s.getCena();
            case 4:
                return s.getKnjiga().getNaziv();
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
    
    

    public List<StavkaFakture> getStavke() {
        return stavke;
    }
    
    public void setStavke(List<StavkaFakture> stavke) {
        this.stavke = stavke;
    }

    public void dodajStavku(StavkaFakture nova) {
        stavke.add(nova);
        fireTableRowsInserted(stavke.size() - 1, stavke.size() - 1);
    }

    public void obrisiStavku(int red) {
        if (red >= 0 && red < stavke.size()) {
            stavke.remove(red);
            fireTableDataChanged();
        }
    }
    
    public StavkaFakture getStavkaAt(int row) {
        if (row >= 0 && row < stavke.size()) {
            return stavke.get(row);
        }
        return null;
    }

    public void izmeniStavku(int row, StavkaFakture izmenjenaStavka) {
        if (row >= 0 && row < stavke.size()) {
            StavkaFakture stara = stavke.get(row);
            izmenjenaStavka.setRedniBroj(stara.getRedniBroj());
            stavke.set(row, izmenjenaStavka);
            fireTableRowsUpdated(row, row);
        }
    }
    
}
