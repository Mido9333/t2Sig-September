/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import Model.HeaderTableModel;
import Model.InvoiceHeader;
import Model.InvoiceLine;
import Model.LineTableModel;
import View.HeaderTableDialog;
import View.LineTableDialog;
import View.invoiceframe;

/**
 *
 * @author DELL
 */
public class Controller implements ActionListener, ListSelectionListener {

    private invoiceframe frame;
    private HeaderTableDialog HeaderDialog;
    private LineTableDialog LineDialog;

    public Controller(invoiceframe frame) {
        this.frame = frame;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        System.out.println("Action Listener");

        int row = frame.getHeaderTable().getSelectedRow();
        System.out.println("Selected Row: " + row);
        if (row > -1 && row < frame.getInvoices().size()) {
            InvoiceHeader inv = frame.getInvoices().get(row);
            frame.getCustNameLabel().setText(inv.getCustomername());
            frame.getInvDateLabel().setText(frame.sdf.format(inv.getDate()));
            frame.getInvNumLabel().setText("" + inv.getnum());
            frame.getInvTotalLabel().setText("" + inv.getTotal());

            List<InvoiceLine> lines = inv.getLine();
            frame.getLinesTable().setModel(new LineTableModel(lines));
        } else {
            frame.getCustNameLabel().setText("");
            frame.getInvDateLabel().setText("");
            frame.getInvNumLabel().setText("");
            frame.getInvTotalLabel().setText("");

            frame.getLinesTable().setModel(new LineTableModel(new ArrayList<InvoiceLine>()));
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("ActionListener");

        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "create new invoice":
                createNewInvoice();
                break;
            case "delete invoice":
                deleteInvoice();
                break;
            case "create new items":
                createNewItem();
                break;
            case "delete item":
                deleteItem();
                break;
            case "Load":
                Load (null, null);
                break;
            case "Save":
                SaveFile();
                break;
            case "newInvoiceOK":
                newInvoiceOK();
                break;
            case "newInvoiceCancel":
                newInvoiceCancel();
                break;
            case "newLineOK":
                newItemOk();
                break;
            case "newLineCancel":
                newItemCancel();
                break;
        }
    }


    private void createNewInvoice() {
        HeaderDialog = new HeaderTableDialog(frame);
        HeaderDialog.setVisible(true);
    }

    private void deleteInvoice() {
        int row = frame.getHeaderTable().getSelectedRow();
        if (row != -1) {
            frame.getInvoices().remove(row);
            ((HeaderTableModel) frame.getHeaderTable().getModel()).fireTableDataChanged();
        }
    }

    private void createNewItem() {
        int selectedInv = frame.getHeaderTable().getSelectedRow();
        if (selectedInv != -1) {
            LineDialog = new LineTableDialog(frame);
            LineDialog.setVisible(true);
        }
    }

    private void deleteItem() {
        int row = frame.getLinesTable().getSelectedRow();
        if (row != -1) {
            int headerRow = frame.getHeaderTable().getSelectedRow();
            LineTableModel lineTableModel = (LineTableModel) frame.getLinesTable().getModel();
            lineTableModel.getLine().remove(row);
            lineTableModel.fireTableDataChanged();
            ((HeaderTableModel) frame.getHeaderTable().getModel()).fireTableDataChanged();
            frame.getHeaderTable().setRowSelectionInterval(headerRow, headerRow);
        }
    }

    public void Load(String headrPath, String linePath) {
        File headerFile = null;
        File lineFile = null;
        if (headrPath == null && linePath == null) {
            JFileChooser fc = new JFileChooser();
            int result = fc.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                headerFile = fc.getSelectedFile();
                result = fc.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    lineFile = fc.getSelectedFile();
                }
            }
        } else {
            headerFile = new File(headrPath);
            lineFile = new File(linePath);
        }

        if (headerFile != null && lineFile != null) {
            try {
              //streaming  
                List<String> headerLines = Files.lines(Paths.get(headerFile.getAbsolutePath())).collect(Collectors.toList());
               
                List<String> lineLines = Files.lines(Paths.get(lineFile.getAbsolutePath())).collect(Collectors.toList());
              
                frame.getInvoices().clear();
                for (String headerLine : headerLines) {
                    String[] parts = headerLine.split(",");  // "1,22-11-2020,Ali"  ==>  ["1", "22-11-2020", "Ali"]
                    String numString = parts[0];
                    String dateString = parts[1];
                    String name = parts[2];
                    int num = Integer.parseInt(numString);
                    Date date = invoiceframe.sdf.parse(dateString);
                    InvoiceHeader inv = new InvoiceHeader(num, name, date);
                    //invoices.add(inv);
                    frame.getInvoices().add(inv);
                }
                System.out.println("Check point");
                for (String lineLine : lineLines) {
                    // lineLine = "1,Mobile,3200,1"
                    String[] parts = lineLine.split(",");
                    /*
                    parts = ["1", "Mobile", "3200", "1"]
                     */
                    int num = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int count = Integer.parseInt(parts[3]);
                    InvoiceHeader inv = getInvoiceByNum(num);
                    InvoiceLine line = new InvoiceLine(name, price, count, inv);
                    inv.getLine().add(line);
                }
                System.out.println("Check point");
                frame.getHeaderTable().setModel(new HeaderTableModel(frame.getInvoices()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private InvoiceHeader getInvoiceByNum(int num) {
        for (InvoiceHeader inv : frame.getInvoices()) {
            if (num == inv.getnum()) {
                return inv;
            }
        }
        return null;
    }

    private void SaveFile() {
        String invoicesData = "";
        String linesData = "";
        for (InvoiceHeader invoice : frame.getInvoices()) {
            invoicesData += invoice.toCSV();
            invoicesData += "\n";
            for (InvoiceLine line : invoice.getLine()) {
                linesData += line.toCSV();
                linesData += "\n";
            }
        }

        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File headerFile = fc.getSelectedFile();
            result = fc.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File lineFile = fc.getSelectedFile();
                try {
                    FileWriter headerFW = new FileWriter(headerFile);
                    headerFW.write(invoicesData);
                    headerFW.flush();
                    headerFW.close();

                    FileWriter lineFW = new FileWriter(lineFile);
                    lineFW.write(linesData);
                    lineFW.flush();
                    lineFW.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error while saving data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void newInvoiceOK() {
        String customer = HeaderDialog.getCustNameField().getText();
        String date = HeaderDialog.getInvDateField().getText();
        HeaderDialog.setVisible(false);
        HeaderDialog.dispose();
        int num = getNextInvoiceNum();
        try {
            Date invDate = frame.sdf.parse(date);
            InvoiceHeader inv = new InvoiceHeader(num, customer, invDate);
            frame.getInvoices().add(inv);
            ((HeaderTableModel) frame.getHeaderTable().getModel()).fireTableDataChanged();
        } catch (ParseException pex) {
            JOptionPane.showMessageDialog(frame, "Error in date format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int getNextInvoiceNum() {
        int num = 1;
        for (InvoiceHeader inv : frame.getInvoices()) {
            if (inv.getnum() > num) {
                num = inv.getnum();
            }
        }
        return num + 1;
    }

    private void newInvoiceCancel() {
        HeaderDialog.setVisible(false);
        HeaderDialog.dispose();
    }

    private void newItemOk() {
        int selectedInv = frame.getHeaderTable().getSelectedRow();
        if (selectedInv != -1) {
            InvoiceHeader inv = frame.getInvoices().get(selectedInv);
            String name = LineDialog.getItemNameField().getText();
            String priceStr = LineDialog.getItemPriceField().getText();
            String countStr = LineDialog.getItemCountField().getText();
            LineDialog.setVisible(false);
            LineDialog.dispose();
            double price = Double.parseDouble(priceStr);
            int count = Integer.parseInt(countStr);
            InvoiceLine line = new InvoiceLine(name, price, count, inv);
            inv.getLine().add(line);
            ((LineTableModel) frame.getLinesTable().getModel()).fireTableDataChanged();
            ((HeaderTableModel) frame.getHeaderTable().getModel()).fireTableDataChanged();
            frame.getHeaderTable().setRowSelectionInterval(selectedInv, selectedInv);
        }
    }

    private void newItemCancel() {
        LineDialog.setVisible(false);
        LineDialog.dispose();
    }

}
