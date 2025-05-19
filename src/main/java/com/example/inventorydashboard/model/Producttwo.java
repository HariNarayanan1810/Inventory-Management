/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.inventorydashboard.model;

/**
 *
 * @author GEETHA
 */


import javafx.beans.property.SimpleStringProperty;

public class Producttwo {
    private final int id;
    private final SimpleStringProperty name;
    private final int stock;

    public Producttwo(int id, String name, int stock) {
        this.id = id;
        this.name = new SimpleStringProperty(name);
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getName() { return name.get(); }
    public SimpleStringProperty nameProperty() { return name; }
    public int getStock() { return stock; }

    @Override
    public String toString() { return getName(); }
}
