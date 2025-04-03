package com.example.finalproject;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    RecyclerView cartRecyclerView;
    TextView totalPriceText;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceText = findViewById(R.id.totalPriceText);
        btnBack = findViewById(R.id.btnBackCart);

        btnBack.setOnClickListener(v -> finish());

        List<CartItem> cartItems = CartManager.getInstance().getCartItems();

        CartAdapter adapter = new CartAdapter(this, cartItems, totalPriceText);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(adapter);


        double total = CartManager.getInstance().getTotalPrice();
        totalPriceText.setText("Total: $" + total);

        Button btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });

    }
}
