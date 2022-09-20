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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Header;
import model.HeaderTableModel;
import model.Line;
import model.LineTableModel;
import view.HeaderTableDialog;
import static view.HeaderTableDialog.jTextfielddt;
import view.LineTableDialog;
import view.invoiceframe;

/**
 *
 * @author ahmed
 */
public class Controller implements ActionListener {

    private static Object headerTable;
 private   invoiceframe frame;
 private HeaderTableDialog HeaderDialog;
 private LineTableDialog LineDialog;
    private int num;
   

    public Controller(invoiceframe frame) {
        this.frame = frame;
    }

    private Controller() {
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "create new invoice":
                createNewInvoice();
                break;
                
                /*case "new Invoice ok":
                Ok();
                break;*/        
            case "delete invoice":
                deleteInvoice();
                break;
            
            case "create new item":
                createNewItem();
                break;
            case "delete item":
                deleteItem();
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

        HeaderTableDialog Dialog=new HeaderTableDialog();
        //setLayout(new FlowLayout());
        Dialog.setVisible(true);
        Dialog.pack();
        Dialog.setDefaultCloseOperation(Dialog.DISPOSE_ON_CLOSE);
        }
   
    /*public  void AddRowToJTable(String[] dataRow) {
    DefaultTableModel model=(HeaderTableModel) frame.getheaderTable().getModel();
    model.addRow(dataRow);
    }*/
    
    
    
    
    
    
    
    private void deleteInvoice(){
       HeaderTableModel model=(HeaderTableModel) frame.getheaderTable().getModel();
       try{
       int SelecteRowIndex=frame.getheaderTable().getSelectedRow();
       model.removeRow(SelecteRowIndex);
       }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex);
       }
        
    }

    
   

    private void createNewItem() {
    LineTableDialog Dialog2=new LineTableDialog();
        Dialog2.setVisible(true);
        Dialog2.pack();
        Dialog2.setDefaultCloseOperation(Dialog2.DISPOSE_ON_CLOSE);
        
    }
    
    

    

    private void deleteItem() {
   LineTableModel model=(LineTableModel) frame.getLineTable().getModel();
       try{
       int SelecteRowIndex=frame.getLineTable().getSelectedRow();
       model.removeRow(SelecteRowIndex);
       }catch(Exception ex){
       JOptionPane.showMessageDialog(null, ex);
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
             Header inv= new Header (num,name,date);
             frame.getHeader().add(inv);
             
             
            }
            for(String lineLine:lineLines){
            String[] datarow= lineLine.split(",");
            String numString=datarow[0];
            int num=Integer.parseInt(datarow[0]);
            String name=datarow[1];
            double price=Double.parseDouble(datarow[2]);
            int count=Integer.parseInt(datarow[3]);
            Header inv= getInvoiceNum(num);
             Line lines= new Line(name, (int) price,count ,inv);
             inv.getLine().add(lines);
            }
            
            frame.getheaderTable().setModel(new HeaderTableModel(frame.getHeader()));
      }catch(Exception e){
     e.printStackTrace();
     }
     
     }
    }
    
    private Header getInvoiceNum(int num){
        for(Header inv: frame.getHeader()){
        if(num==inv.getnum()){
        return inv;
        }
        }
        return null;
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
               for(int i=0;i< frame.getLineTableModel().getColumnCount();i++){
                Headerfw.write(frame.getLineTableModel().getColumnName(i)+",");
                }
                LineFw.write("\n");
                for (int i = 0; i < frame.getLineTableModel().getRowCount(); i++) {
                    for (int j = 0; j < frame.getLineTableModel().getColumnCount(); j++) {
                        LineFw.write(frame.getLineTableModel().getValueAt(i, j).toString()+",");
                    }
                    LineFw.write("\n");//record per line
                    
                    //showMessageDialog( "Successfully saved", "Data", JOptionPane.INFORMATION_MESSAGE);
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
                //HeaderTableDialog dialog = new HeaderTableDialog(new javax.swing.JFrame(), true);
                new HeaderTableDialog().setVisible(true);
               
                new LineTableDialog().setVisible(true);
                //new Controller().setVisible(true);
                
            }
        });
    }

   

 
 
}

