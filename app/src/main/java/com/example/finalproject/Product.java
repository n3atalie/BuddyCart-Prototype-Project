package com.example.finalproject;

public class Product {
    private String name;
    private int imageResId;
    private double price;
    private int stock;
    private int imageResource;
    private int quantity;

    public Product(String name, int imageResId, double price, int stock) {
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
        this.stock = stock;
        this.imageResource = imageResource;
        this.quantity = 1; // default quantity when added to cart
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public void setPrice(double price) {
        this.price = price;
    }

    public int getImageResource() {
        return imageResId;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStock() { return stock; }
}

