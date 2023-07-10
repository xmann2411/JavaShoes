/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view.model;

import hr.algebra.model.Customer;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Karla
 */
public class CustomerTableModel extends AbstractTableModel{

     private static final String[] COLUMN_NAMES = {"id", "Ime", "Prezime", "OIB", "Email", "BrojTelefona", "NazivKartice"};
    
    private List<Customer> customers;
    
    public CustomerTableModel(List<Customer> customers) {
        this.customers = customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return customers.get(rowIndex).getId();
            case 1:
                return customers.get(rowIndex).getIme();
            case 2:
                return customers.get(rowIndex).getPrezime();
            case 3:
                return customers.get(rowIndex).getOIB();
            case 4:
                return customers.get(rowIndex).getEmail();
            case 5:
                return customers.get(rowIndex).getBrojTelefona();
            case 6:
                return customers.get(rowIndex).getNazivKartice();
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
