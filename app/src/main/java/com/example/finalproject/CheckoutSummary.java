package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CheckoutSummary extends AppCompatActivity {
    ImageButton btnBackCheckoutSummary;
    Button btnProceedToPayment;
    TextView totalTxt, gstTxt, pstTxt, finalTotalTxt;
    List<Product> cartItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_summary);

        // Find the views
        btnBackCheckoutSummary = findViewById(R.id.btnBackCheckoutSummary);
        totalTxt = findViewById(R.id.totalTextView);
        gstTxt = findViewById(R.id.gstTextView);
        pstTxt = findViewById(R.id.pstTextView);
        finalTotalTxt = findViewById(R.id.finalTotalTextView);
        btnProceedToPayment = findViewById(R.id.btnProceedToCheckout);

        // Set up the back button listener
        btnBackCheckoutSummary.setOnClickListener(v -> finish());

        // Get cart items
        cartItems = CartManager.getInstance().getCartItems();

        // Calculate total, GST, PST
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice() * product.getQuantity(); // price * quantity for each item
        }

        double gst = total * 0.05; // 5% GST
        double pst = total * 0.07; // 7% PST
        double finalTotal = total + gst + pst; // Final total including GST and PST

        // Set the text for the TextViews
        totalTxt.setText("Total: $" + String.format("%.2f", total));
        gstTxt.setText("GST (5%): $" + String.format("%.2f", gst));
        pstTxt.setText("PST (7%): $" + String.format("%.2f", pst));
        finalTotalTxt.setText("Final Total: $" + String.format("%.2f", finalTotal));

        // Button click listener for proceeding to payment information
        btnProceedToPayment.setOnClickListener(v -> {
            Intent intent = new Intent(CheckoutSummary.this, PaymentInformation.class);
            startActivity(intent);
        });


    }
}