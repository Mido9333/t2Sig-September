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
public class HeaderTableDialog extends JDialog {
    private JTextField custNameField;
    private JTextField DateField;
    private JLabel custNameLbl;
    private JLabel DateLbl;
    private JButton OKBtn;
    private JButton cancelBtn;

    public HeaderTableDialog(invoiceframe frame) {
        custNameLbl = new JLabel("Customer Name ");
        custNameField = new JTextField(30);
        DateLbl = new JLabel(" Date ");
        DateField = new JTextField(30);
        OKBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        OKBtn.setActionCommand("newInvoiceOK");
        cancelBtn.setActionCommand("newInvoiceCancel");
        
        OKBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 3));
        
        add(DateLbl);
        add(DateField);
        add(custNameLbl);
        add(custNameField);
        add(OKBtn);
        add(cancelBtn);
        
        pack();
        
    }


    public JTextField getCustNameField() {
        return custNameField;
    }

    public JTextField getInvDateField() {
        return DateField;
    }
    
    
}