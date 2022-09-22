/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.InvoiceHeader;
import model.HeaderTableModel;
import model.InvoiceLine;
import model.LineTableModel;
import view.HeaderTableDialog;

import view.LineTableDialog;
import view.invoiceframe;

/**
 *
 * @author ahmed
 */
public class Controller implements ActionListener {

   
 private   invoiceframe frame;
 private HeaderTableDialog HeaderDialog;
 private LineTableDialog LineDialog;
 
   

    public Controller(invoiceframe frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "create new invoice":
                createNewInvoice();
                break;
                
                case "newInvoiceOK":
                 newInvoiceOk();
                break;      
                case "newInvoiceCancel":
                 newInvoiceCancel();
            case "delete invoice":
                deleteInvoice();
                break;
            
            case "create new items":
                createNewItem();
                break;
            case "delete item":
                deleteItem();
                break;
                case "newLineOK":
                 newItemOk();
                 break;
                 case "newLineCancel":
                 newItemCancel();
                 break;
            case "Load":
                LoadFile(null,null);
                break;
            case "Save":
                SaveFile();
                break;
        
        
        
        }
    }

    //dialog for header table
    private void createNewInvoice() {

       HeaderDialog = new HeaderTableDialog(frame);
        HeaderDialog.setVisible(true);
        }
   
    /*public  void AddRowToJTable(String[] dataRow) {
    DefaultTableModel model=(HeaderTableModel) frame.getheaderTable().getModel();
    model.addRow(dataRow);
    }*/
    
    
    
    
    
    
    
    private void deleteInvoice(){
      int selectedRowIndex = frame.getheaderTable().getSelectedRow();
        if (selectedRowIndex != -1) {
            frame.getHeader().remove(selectedRowIndex);
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getLineTable().setModel(new LineTableModel(null));
            frame.setLine(null);
            frame.getInvCustLabel().setText("");
            frame.getInvNumLabel().setText("");
            frame.getInvTotalLabel().setText("");
            frame.getInvDateLabel().setText("");
        }
    }

    
   

    private void createNewItem() {
   LineDialog = new LineTableDialog(frame);
        LineDialog.setVisible(true);
    }
    
    private void newItemCancel() {
        LineDialog.setVisible(false);
        LineDialog = null;
        LineDialog.dispose();
        
        
    }

    private void newItemOk() {
        LineDialog.setVisible(false);

        String name = LineDialog.getItemName().getText();
        String quantity = LineDialog.getItemCount().getText();
        String Price = LineDialog.getItemPrice().getText();
        int count = 1;
        double price = 1;
        try {
            count = Integer.parseInt(quantity);
        } catch (NumberFormatException e) {
            e.printStackTrace();

        }

        try {
            price = Double.parseDouble(Price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            

        }
        int InvoiceHeaderSelection = frame.getheaderTable().getSelectedRow();
        if (InvoiceHeaderSelection != -1) {
            InvoiceHeader invHeader = frame.getHeader().get(InvoiceHeaderSelection);
            InvoiceLine Line = new InvoiceLine(name, (int) price, count, invHeader);
            //invHeader.getLines().add(line);
            frame.getLine().add(Line);
            LineTableModel lineTableModel = (LineTableModel) frame.getLineTable().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getHeaderTableModel().fireTableDataChanged();
        }
        frame.getheaderTable().setRowSelectionInterval(InvoiceHeaderSelection, InvoiceHeaderSelection);
        LineDialog = null;
        LineDialog.dispose();
        
    }

    //ok button for invoice table
    
      private void newInvoiceOk() {
        HeaderDialog.setVisible(false);

        String customerName = HeaderDialog.getCustNameField().getText();
        String Date = HeaderDialog.getInvDateField().getText();
        Date date = new Date();
        try {
            date = frame.Df.parse(Date);
        } catch (ParseException e) {JOptionPane.showMessageDialog(frame, "date cannot be parsed", "Invalid date format", JOptionPane.ERROR);}

        int invoicenum = 0;
        for (InvoiceHeader invoice : frame.getHeader()){
         if (invoice.getnum() > invoicenum) {
                invoicenum = invoice.getnum();
            }}invoicenum++;
        InvoiceHeader NewInvoice = new InvoiceHeader(invoicenum, customerName, date);
        frame.getHeader().add(NewInvoice);
        frame.getHeaderTableModel().fireTableDataChanged();
        HeaderDialog.dispose();
        HeaderDialog = null;
    }
      
    private void newInvoiceCancel() {
        HeaderDialog.setVisible(false);
        HeaderDialog.dispose();
        HeaderDialog=null;
    }

  
    

    
//delete buttone for deleting a product item
    private void deleteItem() {
   int selectedLineIndex = frame.getLineTable().getSelectedRow();
        int selectedInvoiceIndex = frame.getheaderTable().getSelectedRow();
        if (selectedLineIndex != -1) {
            frame.getLine().remove(selectedLineIndex);
            LineTableModel lineTableModel = (LineTableModel) frame.getLineTable().getModel();
            lineTableModel.fireTableDataChanged();
            frame.getInvTotalLabel().setText("" + frame.getHeader().get(selectedInvoiceIndex).getTotal());
            frame.getHeaderTableModel().fireTableDataChanged();
            frame.getheaderTable().setRowSelectionInterval(selectedInvoiceIndex, selectedInvoiceIndex);
        }
    }
 
    public void LoadFile(String HeaderPath,String LinePath) {
        
     File Header=null;
     File Line=null;
     
     //if we don't have paths then apear the filechooser
     if (HeaderPath==null && LinePath==null){

         JFileChooser fc=new JFileChooser();
         int result = fc.showOpenDialog(frame);
         if (result==JFileChooser.APPROVE_OPTION){
         Header=fc.getSelectedFile();
         result=fc.showOpenDialog(frame);
         if (result==JFileChooser.APPROVE_OPTION){
         Line=fc.getSelectedFile();
         result=fc.showOpenDialog(frame);
         }
         }
         
     }else{
         //this else when we have two paths then create the folder
     Header= new File(HeaderPath);
     Line=new File(LinePath);
     }
     //this if to check if there's header file read and line file read
     if(Header!=null && Line!=null){
     
         try{
             /*FileReader fr = new FileReader(HeaderPath);
             FileReader fr2 = new FileReader(LinePath);
             BufferedReader br = new BufferedReader(fr);
             BufferedReader br2 = new BufferedReader(fr2);
             String firstline=br.readLine().trim();
             String firstline2=br2.readLine().trim();
             String [] columnsnameheader=firstline.split(",");
             String [] columnsnameline=firstline2.split(",");
             DefaultTableModel model=(DefaultTableModel)frame.getheaderTable().getModel();
             DefaultTableModel model2=(DefaultTableModel)frame.getLineTable().getModel();
             model.setColumnIdentifiers(columnsnameheader);
             model.setColumnIdentifiers(columnsnameline);
             //get line from txt file
             Object[] tableHeader=br.lines().toArray();
             Object[] tableline=br2.lines().toArray();
             //extract data from lines
             for(int i=0;i<tableHeader.length;i++)
             {
             String  header=tableHeader[i].toString().trim();
             String[] datarow= header.split(",");
             String numString=datarow[0];
             String dateString=datarow[1];
             String name=datarow[2];
             int num=Integer.parseInt(numString);
             Date date=frame.Df.parse(dateString);
             model.addRow(datarow);
             Header inv= new Header (num,name,date);
             frame.getHeader().add(inv);
             
             
             }
             for(int i=0;i<tableline.length;i++){
             String line=tableline[i].toString().trim();
             String[] datarow= line.split(",");
             String numString=datarow[0];
             String name=datarow[1];
             double price=Double.parseDouble(datarow[2]);
             int count=Integer.parseInt(datarow[3]);
             Header inv= getInvoiceNum(num);
             Line lines= new Line(name, (int) price,count ,inv);
             inv.getLine().add(lines);
             }
             frame.getHeaderTableModel().setModel(new HeaderTableModel(frame.getHeader()));
             br.close();
             fr.close();
            
             */
             
             //Streams
             List <String> headerLines=Files.lines (Paths.get(Header.getAbsolutePath())).collect(Collectors.toList());
             List <String> lineLines=Files.lines (Paths.get(Line.getAbsolutePath())).collect(Collectors.toList());
            
     
            frame.getHeader().clear();
            for(String headerLine:headerLines){
            String[] datarow= headerLine.split(",");
            String numString=datarow[0];
             String dateString=datarow[1];
             String name=datarow[2];
             int num=Integer.parseInt(numString);
             Date date=frame.Df.parse(dateString);
             InvoiceHeader inv= new InvoiceHeader (num,name,date);
             frame.getHeader().add(inv);
             
             
            }
            for(String lineLine:lineLines){
            String[] datarow= lineLine.split(",");
            String numString=datarow[0];
            int num=Integer.parseInt(datarow[0]);
            String name=datarow[1];
            double price=Double.parseDouble(datarow[2]);
            int count=Integer.parseInt(datarow[3]);
            InvoiceHeader inv= frame.getInvoiceNum(num);
             InvoiceLine lines= new InvoiceLine(name, (int) price,count ,inv);
             inv.getLine().add(lines);
            }
            
            frame.getheaderTable().setModel(new HeaderTableModel(frame.getHeader()));
      }catch(Exception e){
     e.printStackTrace();
     }
     
     }
    }
    
    
        
    

    private void SaveFile() {
     JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("specify a file save");
        int result = fc.showSaveDialog(frame);
       // HeaderTableModel model=frame.getHeaderTableModel().getModel();
        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fc.getSelectedFile();
            //write to file
            try {
                FileWriter Headerfw = new FileWriter(fileToSave);
                BufferedWriter Headerbw = new BufferedWriter(Headerfw);
                FileWriter LineFw = new FileWriter(fileToSave);
                BufferedWriter LineBw = new BufferedWriter(LineFw);
                
                //forloop for header
                for(int i=0;i< frame.getheaderTable().getColumnCount();i++){
                Headerfw.write(frame.getheaderTable().getColumnName(i)+",");
                }
                Headerfw.write("\n");
                for (int i = 0; i < frame.getHeaderTableModel().getRowCount(); i++) {
                    for (int j = 0; j < frame.getHeaderTableModel().getColumnCount(); j++) {
                        Headerfw.write(frame.getHeaderTableModel().getValueAt(i, j).toString()+",");
                    }
                    Headerfw.write("\n");//record per line
                    
                }
               //for loop for line
               for(int i=0;i< frame.getLineTable().getColumnCount();i++){
                Headerfw.write(frame.getLineTable().getColumnName(i)+",");
                }
                LineFw.write("\n");
                for (int i = 0; i < frame.getLineTable().getRowCount(); i++) {
                    for (int j = 0; j < frame.getLineTable().getColumnCount(); j++) {
                        LineFw.write(frame.getLineTable().getValueAt(i, j).toString()+",");
                    }
                    LineFw.write("\n");//record per line
                    
                   // JOptionPane.showMessageDialog( "Successfully saved", "Data", JOptionPane.INFORMATION_MESSAGE);
                }
                Headerbw.close();
                Headerfw.close();
                LineBw.close();
                LineFw.close();
            } catch (Exception e) {
                e.printStackTrace();;
            }


        }
    }

    
    
    
 public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(invoiceframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(invoiceframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(invoiceframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(invoiceframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new invoiceframe().setVisible(true);
                //HeaderTableDialog dialog = new HeaderTableDialog();
                //new HeaderTableDialog().setVisible(true);
               
                //new LineTableDialog().setVisible(true);
                //new Controller().setVisible(true);
                
            }
        });
    }

    


   

 
 
}

