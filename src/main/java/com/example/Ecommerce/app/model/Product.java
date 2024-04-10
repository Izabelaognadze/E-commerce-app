package com.example.Ecommerce.app.model;

public class Product {
    private String name;
    private int quantity;
    private double price;
    private String userType;

    public Product(String name, int quantity, double price, String userType) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
