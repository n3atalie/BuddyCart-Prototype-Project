package com.example.finalproject;

public class Product {
    private String name;
    private int imageResId;
    private double price;
    private int stock;

    public Product(String name, int imageResId, double price, int stock) {
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
        this.stock = stock;
    }

    public String getName() { return name; }
    public int getImageResId() { return imageResId; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
}

