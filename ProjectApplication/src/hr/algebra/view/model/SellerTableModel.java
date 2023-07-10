/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view.model;

import hr.algebra.model.Seller;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Karla
 */
public class SellerTableModel extends AbstractTableModel {
    
    private static final String[] COLUMN_NAMES = {"id", "Ime", "Prezime", "GodineStaza", "ProdavaonicaID"};
    
    private List<Seller> sellers;

    public SellerTableModel(List<Seller> sellers) {
        this.sellers = sellers;
    }

    public void setSellers(List<Seller> sellers) {
        this.sellers = sellers;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return sellers.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
           switch(columnIndex){
            case 0:
                return sellers.get(rowIndex).getId();
            case 1:
                return sellers.get(rowIndex).getIme();
            case 2:
                return sellers.get(rowIndex).getPrezime();
            case 3:
                return sellers.get(rowIndex).getGodineStaza();
            case 4:
                return sellers.get(rowIndex).getProdavaonicaID();
            
        }
        throw new RuntimeException("No such column");    
    }
    
    
    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex); //To change body of generated methods, choose Tools | Templates.
    }
    
}
