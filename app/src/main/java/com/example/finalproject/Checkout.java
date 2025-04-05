package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Checkout extends AppCompatActivity {
    RecyclerView cartRecyclerView;
    CartAdapter adapter;
    List<Product> cartItems;
    Button btnProceedToCheckout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        ImageButton btnBack = findViewById(R.id.btnBackContact);
        btnProceedToCheckout = findViewById(R.id.btnProceedToCheckout);
        btnBack.setOnClickListener(v -> finish());

        btnProceedToCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(Checkout.this, CheckoutSummary.class);
            startActivity(intent);
        });


        cartRecyclerView = findViewById(R.id.cartRecyclerView);


        // Get cart items
        cartItems = CartManager.getInstance().getCartItems();


        // Set up adapter
        adapter = new CartAdapter(this, cartItems);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(adapter);
    }
}
