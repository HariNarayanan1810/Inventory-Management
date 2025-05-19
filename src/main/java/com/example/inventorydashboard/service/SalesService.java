/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.inventorydashboard.service;

/**
 *
 * @author GEETHA
 */



import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;


import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class SalesService {

    private final String url = "jdbc:postgresql://localhost:5432/sales_db";
    private final String user = "postgres";
    private final String password = "ADMIN";

    public boolean checkStock(int productId, int quantity) {
        // Query the product_stock table instead of products table
        String sql = "SELECT quantity FROM product_stock WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt("quantity");  // Using 'quantity' from the product_stock table
                return stock >= quantity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   public void saveOrder(String customerName, int productId, int warehouseId, int quantity) {
    String sql = "INSERT INTO sales (customer_name, product_id, warehouse_id, quantity, payment_status) " +
                 "VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, customerName);
        stmt.setInt(2, productId);
        stmt.setInt(3, warehouseId);
        stmt.setInt(4, quantity);
        stmt.setString(5, "Pending"); // default payment status

        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void generateInvoicePDF(String customerName, String filePath) {
    Document document = new Document();
    String sql = 
    "SELECT s.sale_id, s.customer_name, p.name AS product_name, p.supplier_id, s.quantity, s.payment_status, s.created_at " +
    "FROM sales s " +
    "JOIN products p ON s.product_id = p.product_id " +
    "WHERE s.customer_name = ?";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, customerName);
        ResultSet rs = stmt.executeQuery();

        PdfWriter.getInstance(document, new FileOutputStream("‪C:\\Fresh\\Invoice.docx"));

        document.open();

        document.add(new Paragraph("Invoice"));

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.addCell("Customer Name");
        table.addCell("Product Name");
        table.addCell("Supplier ID");
        table.addCell("Quantity");
        table.addCell("Payment Status");
        table.addCell("Date");

        if (rs.next()) {
            table.addCell(customerName);
            table.addCell(rs.getString("product_name"));
            table.addCell(rs.getString("supplier_id"));
            table.addCell(rs.getString("quantity"));
            table.addCell(rs.getString("payment_status"));
            table.addCell(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate().toString());
        } else {
            // No order found
            table.addCell(customerName);
            table.addCell("N/A");
            table.addCell("N/A");
            table.addCell("N/A");
            table.addCell("N/A");
            table.addCell(java.time.LocalDate.now().toString());
        }

        document.add(table);

    } catch (DocumentException | IOException | SQLException e) {
        e.printStackTrace();
    } finally {
        document.close();
    }
}

}
