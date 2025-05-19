/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.inventorydashboard.controller;

/**
 *
 * @author GEETHA
 */

import com.example.inventorydashboard.service.SalesService;

//import com.mycompany.enterpriseproject.service.SalesService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;

public class SalesController {

    @FXML private TextField customerNameField;
    @FXML private TextField warehouseIdField;
    @FXML private TextField productIdField;
    @FXML private TextField quantityField;
    @FXML private Label stockStatusLabel;

    private final SalesService salesService = new SalesService();

    @FXML
    private void onCheckStock() {
        try {
            int productId = Integer.parseInt(productIdField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            boolean inStock = salesService.checkStock(productId, quantity);
            stockStatusLabel.setText(inStock ? "In Stock" : "Not Enough Stock");
        } catch (NumberFormatException e) {
            stockStatusLabel.setText("Invalid input");
        }
    }

    @FXML
private void onSaveOrder() {
    String customerName = customerNameField.getText();
    try {
        int productId = Integer.parseInt(productIdField.getText());
        int warehouseId = Integer.parseInt(warehouseIdField.getText());
        int quantity = Integer.parseInt(quantityField.getText());

        salesService.saveOrder(customerName, productId, warehouseId, quantity);
        stockStatusLabel.setText("Order Saved");
    } catch (NumberFormatException e) {
        stockStatusLabel.setText("Invalid input");
    }
}

    @FXML
    private void onGenerateInvoice() {
        String customerName = customerNameField.getText();
        String filePath = "invoice_" + customerName + ".pdf";

        salesService.generateInvoicePDF(customerName, filePath);
        stockStatusLabel.setText("Invoice PDF Saved");
    }
}