/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view.model;

import hr.algebra.model.Shoe;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Karla
 */
public class ShoesTableModel extends AbstractTableModel{
    
    private static final String[] COLUMN_NAMES = {"id", "Marka", "Model", "Broj", "Boja", "OpisProizvoda"};
    
    private List<Shoe> shoes;

    public ShoesTableModel(List<Shoe> shoes) {
        this.shoes = shoes;
    }

    public void setShoes(List<Shoe> shoes) {
        this.shoes = shoes;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return shoes.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0:
                return shoes.get(rowIndex).getId();
            case 1:
                return shoes.get(rowIndex).getMarka();
            case 2:
                return shoes.get(rowIndex).getModel();
            case 3:
                return shoes.get(rowIndex).getBroj();
            case 4:
                return shoes.get(rowIndex).getBoja();
            case 5:
                return shoes.get(rowIndex).getOpisProizvoda();
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
