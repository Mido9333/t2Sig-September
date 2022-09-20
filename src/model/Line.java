/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ahmed
 */
public class Line {
    
    private String name;
    private int count;
    private double price;
    private Header inv;
    private ArrayList <Header> Header;

    public Line(String name, int count, double price, Header inv) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.inv = inv;
    }
    
    public double getTotal(){
    return count*price;
    }

    public Header getInv() {
        return inv;
    }

    public ArrayList<Header> getHeader() {
        return Header;
    }
    public void setInv(Header inv) {
        this.inv = inv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Line{" + "name=" + name + ", count=" + count + ", price=" + price + '}';
    }
    
    
}
