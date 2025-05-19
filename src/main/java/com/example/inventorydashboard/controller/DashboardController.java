package com.example.inventorydashboard.controller;

import com.example.inventorydashboard.model.DashboardCard;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Arrays;
import java.util.List;

public class DashboardController {

     @FXML private LineChart<String, Number> salesLineChart;
    @FXML
    private GridPane cardGrid;

    @FXML
    private LineChart<Number, Number> salesChart;

    @FXML
    public void initialize() {
        setupCards();
        loadSalesChart();
    }

    private void setupCards() {
        List<DashboardCard> cards = Arrays.asList(
                new DashboardCard("Total Stock", "4,520", "📦", ""),
                new DashboardCard("Total Sales (This Month)", "₹78,000", "💰", ""),
                new DashboardCard("Total Purchase (This Month)", "₹55,000", "🛒", ""),
                new DashboardCard("Profit/Loss", "↑ ₹23,000", "📈", "up"),
                new DashboardCard("Low Stock Alerts", "6 Items", "⚠️", ""),
                new DashboardCard("Top-Selling Product", "USB Cable", "🔥", ""),
                new DashboardCard("Returns Summary", "8 (2 Purchase, 6 Sales)", "↩️", ""),
                new DashboardCard("Active Suppliers", "12", "🏭", ""),
                new DashboardCard("Pending Retailer Orders", "4", "📋", ""),
                new DashboardCard("Total Inventory Value", "₹1,50,000", "🏷️", "")
        );

        int col = 0;
        int row = 0;
        for (int i = 0; i < cards.size(); i++) {
            StackPane card = createCard(cards.get(i));
            cardGrid.add(card, col, row);
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }
    }

    private StackPane createCard(DashboardCard data) {
        VBox box = new VBox(5);
        box.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-radius: 8; -fx-padding: 15; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 4, 0, 0, 2);");
        box.setPrefSize(200, 100);
        box.setAlignment(javafx.geometry.Pos.CENTER);

        Label iconLabel = new Label(data.getIcon());
        iconLabel.setFont(new Font(24));

        Label title = new Label(data.getTitle());
        title.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");

        Label value = new Label(data.getValue());
        value.setStyle("-fx-font-size: 16px; -fx-text-fill: #333333;");

        box.getChildren().addAll(iconLabel, title, value);
        return new StackPane(box);
    }

    private void loadSalesChart() {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.getData().addAll(
                new XYChart.Data<>(1, 120),
                new XYChart.Data<>(2, 145),
                new XYChart.Data<>(3, 110),
                new XYChart.Data<>(4, 180),
                new XYChart.Data<>(5, 130),
                new XYChart.Data<>(6, 160),
                new XYChart.Data<>(7, 155)
        );
        salesChart.getData().add(series);
    }
}
