/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import java.util.ArrayList;
import View.invoiceframe;

/**
 *
 * @author DELL
 */
public class InvoiceHeader {
    private int num;
    private String customer;
    private Date date;
    private ArrayList<InvoiceLine> Line;

    public InvoiceHeader(int num, String customer, Date date) {
        this.num = num;
        this.customer = customer;
        this.date = date;
    }

    
    public String toCSV() {
        return num + "," + invoiceframe.sdf.format(date) + "," + customer;
    }
    
    public int getnum() {
        return num;
    }

    public void setnum(int num) {
        this.num = num;
    }

    public String getCustomername() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<InvoiceLine> getLine() {
        if (Line == null) {
            Line = new ArrayList<>();
        }
        return Line;
    }

    public void setLine(ArrayList<InvoiceLine> Line) {
        this.Line = Line;
    }
    
    public double getTotal() {
        double total = 0.0;
        for (InvoiceLine line : getLine()) {
            total += line.getTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Invoice{" + "num=" + num + ", customer=" + customer + ", date=" + date + '}';
    }

    
    
    
}
