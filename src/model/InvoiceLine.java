/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DELL
 */
public class InvoiceLine {
    private String item;
    private double price;
    private int count;
    private InvoiceHeader Inv;

    public InvoiceLine(String item, double price, int count, InvoiceHeader Inv) {
        this.item = item;
        this.price = price;
        this.count = count;
        this.Inv = Inv;
    }

    public String toCSV() {
        return Inv.getnum() + "," + item + "," + price + "," + count;
    }
    
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public InvoiceHeader getInv() {
        return Inv;
    }

    public void setInvoice(InvoiceHeader Header) {
        this.Inv = Header;
    }
    
    public double getTotal() {
        return count * price;
    }

    @Override
    public String toString() {
        return "Line{" + "item=" + item + ", price=" + price + ", count=" + count + '}';
    }
    
}
