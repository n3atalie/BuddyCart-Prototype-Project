package com.example.finalproject;

public class PaymentMethod {
    public String name;
    public String cardNumber;
    public String expiry;
    public String cvv;
    public String address;

    public PaymentMethod(String name, String cardNumber, String expiry, String cvv, String address) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.expiry = expiry;
        this.cvv = cvv;
        this.address = address;
    }
}
