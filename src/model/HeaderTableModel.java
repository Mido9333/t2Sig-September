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
import view.invoiceframe;

/**
 *
 * @author ahmed
 */
public class HeaderTableModel extends AbstractTableModel {

   
  
    
 
    private String [] Columns={"invoice no","date","Customername","total"};
   private ArrayList<InvoiceHeader> Header;
   
   public HeaderTableModel(ArrayList<InvoiceHeader> Header){
   
       this.Header =Header;
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
        InvoiceHeader Inv = Header.get(rowIndex);
        switch (columnIndex){
             case 0:
             return Header.get(rowIndex).getnum();
             
             case 1:
                 
             
          return   invoiceframe.Df.format(Header.get(rowIndex).getDate());
             
             case 2:
             return Header.get(rowIndex).getCustomername();
             
             case 3:
             return Header.get(rowIndex).getTotal();
             
             default :
                 break ;
         }
       return "";
    }



       

  

   
}
