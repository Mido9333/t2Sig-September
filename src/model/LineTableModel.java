/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class LineTableModel extends AbstractTableModel {

    private String[] columns = {"Item", "Price", "Count", "Total"};
    private List<InvoiceLine> Line;

    public LineTableModel(List<InvoiceLine> Line) {
        this.Line = Line;
    }

    public List<InvoiceLine> getLine() {
        return Line;
    }
    
    @Override
    public int getRowCount() {
        return Line.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine line = Line.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return line.getItem();
            case 1: return line.getPrice();
            case 2: return line.getCount();
            case 3: return line.getTotal();
        }
        return "";
    }
    
}
