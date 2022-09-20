/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ahmed
 */
public class LineTableModel extends AbstractTableModel {
   private String [] Columns={"item name","price","quantity","total"};
   private ArrayList<Line> Line;
   
   public LineTableModel(ArrayList<Line> Line){
   
       this.Line =Line;
   }

   public ArrayList<Line> getLine() {

    return Line;
}
   
    @Override
    public int getRowCount() {
        return Line.size();
    }

    @Override
    public int getColumnCount() {
         return Columns.length;
    }
   @Override
    public String getColumnName(int column){
    
        return Columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
          
              switch (columnIndex){
             case 0:
             Line.get(rowIndex).getName();
             break;
             case 1:
                 
             Line.get(rowIndex).getPrice();
             break;
             case 2:
             Line.get(rowIndex).getCount();
             break;
             case 3:
             Line.get(rowIndex).getTotal();
             break;
             default :
                 break ;
         }
       return null;
      }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
    
    if (getValueAt(0,columnIndex)!=null){
    return getValueAt(0,columnIndex).getClass();
    }else{
    return Object.class;
    }
    }

    public void removeRow(int SelecteRowIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

