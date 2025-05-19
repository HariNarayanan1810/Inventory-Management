package com.example.inventorydashboard.repository;

import com.example.inventorydashboard.model.SalesReturn;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;


public class SalesReturnRepository {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/inventory_management";
    private static final String USER = "postgres";
    private static final String PASSWORD = "ADMIN"; // adjust if needed

    public void save(SalesReturn salesReturn) {
        String sql = "INSERT INTO sales_returns (sale_id, product_id, quantity_returned, reason, refund_amount, return_status, return_date, warehouse_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, 1); // dummy sale_id
            pstmt.setInt(2, 1); // dummy product_id
            pstmt.setInt(3, salesReturn.getQuantity());
            pstmt.setString(4, salesReturn.getReason());
            pstmt.setDouble(5, 0.0); // dummy refund_amount
            pstmt.setString(6, "pending");
            pstmt.setDate(7, Date.valueOf(salesReturn.getDate()));
            pstmt.setInt(8, 1); // dummy warehouse_id

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SalesReturn> findAll() {
        List<SalesReturn> salesReturns = new ArrayList<>();

        String sql = "SELECT return_id, quantity_returned, reason, return_date FROM sales_returns";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("return_id");
                int quantity = rs.getInt("quantity_returned");
                String reason = rs.getString("reason");
                LocalDate date = rs.getDate("return_date").toLocalDate();

                // Dummy placeholders for retailer and product (since not stored in DB)
                String retailer = "Retailer A";
                String product = "Product X";

                SalesReturn sr = new SalesReturn(id, retailer, product, quantity, reason, date);
                salesReturns.add(sr);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesReturns;
    }
}
