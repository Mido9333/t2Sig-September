/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ahmed
 */
public class Header {
    private int num;
    private Date date;
    private String customername;
    private ArrayList <Line> Line; //to make the super class knowing the subclass

    public Header(int num, Date date, String customername) {
        this.num = num;
        this.date = date;
        this.customername = customername;
    }

    public Header(int num, String name, Date date) {
       
    }

    public double getTotal(){
    double total=0.0;
            for(Line line:Line){
            total+=line.getTotal();
            }
    return total;        
    }
    public ArrayList<Line> getLine() {
        return Line;
    }

    public ArrayList<Line> setLine() {
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

    @Override
    public String toString() {
        return "Header{" + "invoiceno=" + num + ", date=" + date + ", customername=" + customername + '}';
    }
    
    
}
