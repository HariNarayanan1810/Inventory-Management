module com.example.inventorydashboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;       // Add this for JDBC access
    requires itextpdf;       // Add this for iText PDF library (if iText is modular)
    
    opens com.example.inventorydashboard.model to javafx.base;
    opens com.example.inventorydashboard to javafx.graphics;
    opens com.example.inventorydashboard.controller to javafx.fxml;

    exports com.example.inventorydashboard;
    exports com.example.inventorydashboard.controller;
}
