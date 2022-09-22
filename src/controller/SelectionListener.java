/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static java.nio.file.Files.lines;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.InvoiceHeader;
import model.InvoiceLine;
import model.LineTableModel;
import view.invoiceframe;

/**
 *
 * @author ahmed
 */
public class SelectionListener implements ListSelectionListener {
    private invoiceframe frame;

    public SelectionListener(invoiceframe frame) {
        this.frame = frame;
    }

    public SelectionListener() {
        
    }

    

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedInvoiceIndex = frame.getheaderTable().getSelectedRow();
        System.out.println("Invoice selected: " + selectedInvoiceIndex);
        if (selectedInvoiceIndex != -1) {
            InvoiceHeader selectedInv = frame.getHeader().get(selectedInvoiceIndex);
            ArrayList <InvoiceLine> Line  = selectedInv.getLine();
            LineTableModel LineTableModel = new LineTableModel(Line);
            frame.setLine(Line);
            frame.getLineTable().setModel(LineTableModel);
            frame.getInvCustLabel().setText(selectedInv.getCustomername());
            frame.getInvNumLabel().setText("" + selectedInv.getnum());
            frame.getInvTotalLabel().setText("" + selectedInv.getTotal());
            frame.getInvDateLabel().setText(frame.Df.format(selectedInv.getDate()));
        }
    }
}
