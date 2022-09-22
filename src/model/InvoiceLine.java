/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.InvoiceHeader;

/**
 *
 * @author ahmed
 */
public class InvoiceLine {
    
    private String Itemname;
    private int count;
    private double price;
    private InvoiceHeader inv;
    
     public InvoiceLine() {
    }

    public InvoiceLine(String Itemname, int count, double price, InvoiceHeader inv) {
        this.Itemname = Itemname;
        this.count = count;
        this.price = price;
        this.inv = inv;
    }
    
    public double getTotal(){
    return count*price;
    }

    public InvoiceHeader getInv() {
        return inv;
    }

  
    public void setInv(InvoiceHeader inv) {
        this.inv = inv;
    }

    public String getName() {
        return Itemname;
    }

    public void setName(String Itemname) {
        this.Itemname = Itemname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Line{" + "name=" + Itemname + ", count=" + count + ", price=" + price + '}';
    }
    
    
}
