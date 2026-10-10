/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domain.Faktura;
import domain.Knjizara;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Dell
 */
public class ModelFaktura extends AbstractTableModel {

    private List<Faktura> lista;
    private List<Knjizara> knjizare;
    private String imeRacunovodje;
    private String[] columnNames = {"ID","datum kreiranja","rok isplate","ukupan iznos","knjizara","racunovodja"};
    private Class[] classNames = {Long.class, LocalDate.class, LocalDate.class, Double.class, String.class, String.class};
    
    public ModelFaktura(){
        lista = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Faktura f = lista.get(rowIndex);
        switch(columnIndex){
            case 0:
                return f.getIdFaktura();
            case 1:
                return f.getDatumKreiranja();
            case 2:
                return f.getRokIsplate();
            case 3:
                return f.getUkupanIznos();
            case 4:
                if (f.getKnjizara() != null) {
                    return f.getKnjizara().getNaziv();
                }
                return "Nepoznata knjizara";

            case 5:
                if (f.getRacunovodja() != null) {
                return f.getRacunovodja().getIme() + " " + f.getRacunovodja().getPrezime(); 
            }
            return "Nepoznat racunovodja";
            default:
                return "n/a";
        }
    }

    public void setImeRacunovodje(String imeRacunovodje) {
        this.imeRacunovodje = imeRacunovodje;
    }

    public void setKnjizare(List<Knjizara> knjizare) {
        this.knjizare = knjizare;
    }

    public Faktura getFaktura(int selectedRow) {
        return lista.get(selectedRow);
    }

    public void setLista(List<Faktura> rezultat) {
        this.lista = rezultat;
        fireTableDataChanged();
    }
    
    public List<Faktura> getLista() {
        return lista;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return classNames[columnIndex];
    }
    
    public void obrisiFakturu(int rowIndex) {
        if (lista != null && rowIndex >= 0 && rowIndex < lista.size()) {
            lista.remove(rowIndex);
            fireTableDataChanged();
        }
    }
    
}
