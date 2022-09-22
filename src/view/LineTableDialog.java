/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author ahmed
 */
public class LineTableDialog extends JDialog{
     private JTextField ItemNameField;
    private JTextField itemQuantitytField;
    private JTextField itemPriceField;
    private JLabel NameLbl;
    private JLabel QuantityLbl;
    private JLabel PriceLbl;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public LineTableDialog(invoiceframe frame) {
        ItemNameField = new JTextField(30);
        NameLbl = new JLabel("Product Name");
        
        itemQuantitytField = new JTextField(30);
        QuantityLbl = new JLabel("Product Quantity");
        
        itemPriceField = new JTextField(30);
        PriceLbl = new JLabel("Product Price");
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("newLineOK");
        cancelBtn.setActionCommand("newLineCancel");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 3));
        
        add (NameLbl);
        add (ItemNameField);
        add(QuantityLbl);
        add(itemQuantitytField);
        add(PriceLbl);
        add(itemPriceField);
        add(okBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getItemName() {
        return ItemNameField;
    }

    public JTextField getItemCount() {
        return itemQuantitytField;
    }

    public JTextField getItemPrice() {
        return itemPriceField;
    }
}