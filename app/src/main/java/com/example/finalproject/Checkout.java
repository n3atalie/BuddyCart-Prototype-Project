package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

public class Checkout extends AppCompatActivity {
    RecyclerView cartRecyclerView;
    CartAdapter adapter;
    List<Product> cartItems;
    Button btnProceedToCheckout;
    Button btnSelectPayment;
    TextView tvSelectedCard;

    private static final int REQUEST_PAYMENT = 101;
    private Gson gson = new Gson();
    private PaymentMethod selectedPayment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Cart views
        ImageButton btnBack = findViewById(R.id.btnBackContact);
        btnProceedToCheckout = findViewById(R.id.btnProceedToCheckout);
        cartRecyclerView = findViewById(R.id.cartRecyclerView);

        // Payment method views
        btnSelectPayment = findViewById(R.id.btnSelectPayment);
        tvSelectedCard = findViewById(R.id.tvSelectedCard);

        btnBack.setOnClickListener(v -> finish());

        btnProceedToCheckout.setOnClickListener(v -> {
            if (selectedPayment == null) {
                Toast.makeText(this, "Please select a payment method first", Toast.LENGTH_SHORT).show();
                return;
            }

            // Pass selected card to CheckoutSummary
            Intent intent = new Intent(Checkout.this, CheckoutSummary.class);
            String selectedCardJson = gson.toJson(selectedPayment);
            intent.putExtra("selected_card", selectedCardJson);
            startActivity(intent);
        });

        btnSelectPayment.setOnClickListener(v -> {
            Intent intent = new Intent(Checkout.this, ManagePaymentsActivity.class);
            startActivityForResult(intent, REQUEST_PAYMENT);
        });

        // Load cart items
        cartItems = CartManager.getInstance().getCartItems();
        adapter = new CartAdapter(this, cartItems);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PAYMENT && resultCode == RESULT_OK && data != null) {
            String selectedJson = data.getStringExtra("selected_card");
            selectedPayment = gson.fromJson(selectedJson, PaymentMethod.class);
            tvSelectedCard.setText("Paying with **** " +
                    selectedPayment.cardNumber.substring(selectedPayment.cardNumber.length() - 4));
        }
    }
}
