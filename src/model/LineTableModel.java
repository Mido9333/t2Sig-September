/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmed
 */
public class LineTableModel extends AbstractTableModel {
   private String [] Columns={"item name","price","Count","total"};
   private ArrayList<InvoiceLine> Line;
   
   public LineTableModel(ArrayList<InvoiceLine> Line){
   
       this.Line =Line;
   }  
   
    @Override
    public int getRowCount() {
   return Line == null ? 0 : Line.size();
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
             return Line.get(rowIndex).getName();
             
             case 1:
                 
             return Line.get(rowIndex).getPrice();
             
             case 2:
             return Line.get(rowIndex).getCount();
             
             case 3:
             return Line.get(rowIndex).getTotal();
             
             default :
                 break ;
         }
       return "";
      }
}