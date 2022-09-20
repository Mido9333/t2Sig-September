/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmed
 */
public class HeaderTableModel extends DefaultTableModel {

   
  
    
 
    private String [] Columns={"invoice no","date","Customername","total"};
   private ArrayList<Header> Header;
   
   public HeaderTableModel(ArrayList<Header> Header){
   
       this.Header =Header;
   }

    
public ArrayList<Header> getHeader() {
        return Header;
    }
    @Override
    public int getRowCount() {
        
        return Header.size();
    
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
             Header.get(rowIndex).getnum();
             break;
             case 1:
                 
             Header.get(rowIndex).getDate();
             break;
             case 2:
             Header.get(rowIndex).getCustomername();
             break;
             case 3:
             Header.get(rowIndex).getTotal();
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

    public void setModel(HeaderTableModel headerTableModel) {
        
    }

       

  

   
}
