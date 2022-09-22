/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ahmed
 */
public class InvoiceHeader {
    private int num;
    private  Date date;
    private String customername;
    private ArrayList <InvoiceLine> Line; //to make the super class knowing the subclass
private DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

 public InvoiceHeader(int num, String name, Date date) {
       
    }
    public InvoiceHeader(int num, Date date, String customername) {
        this.num = num;
        this.date = date;
        this.customername = customername;
    }

   

    public double getTotal(){
    double total=0.0;
    for(int i=0 ; i<getLine().size();i++){
    InvoiceLine Line=getLine().get(i);
     total+=Line.getTotal();
    }
    /* for(InvoiceLine line:Line){
    total+=line.getTotal();
    }*/
    return total;        
    }
    public ArrayList<InvoiceLine> getLine() {
        if (Line == null) {
            Line = new ArrayList<>();
        }
        return Line;
    }

    public ArrayList<InvoiceLine> setLine() {
        this.Line= Line;
        return Line;
    }
    
    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public int getnum() {
        return num;
    }

    public void setnum(int num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void addInvLine(InvoiceLine Line){
    getLine().add(Line);
    }
    

    @Override
    public String toString() {
        return "Header{" + "invoiceno=" + num + ", date=" + date + ", customername=" + customername + '}';
    }
    
    
}
