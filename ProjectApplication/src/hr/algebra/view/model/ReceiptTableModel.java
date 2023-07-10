/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view.model;

import hr.algebra.model.Receipt;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Karla
 */
public class ReceiptTableModel extends AbstractTableModel{
    
    private static final String[] COLUMN_NAMES = {"IDRacun","KupacID", "ProdavacID", "ParCipelaID", "Cijena", "Popust", "datumIzdavanja"};
    
    private List<Receipt> receipts;
    
    
    public ReceiptTableModel(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return receipts.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return receipts.get(rowIndex).getId();
            case 1:
                return receipts.get(rowIndex).getKupacID();
            case 2:
                return receipts.get(rowIndex).getProdavacID();
            case 3:
                return receipts.get(rowIndex).getParCipelaID();
            case 4:
                return receipts.get(rowIndex).getCijena();
            case 5:
                return receipts.get(rowIndex).getPopust();
            case 6:
                return receipts.get(rowIndex).getDatumIzdavanja();
                
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
