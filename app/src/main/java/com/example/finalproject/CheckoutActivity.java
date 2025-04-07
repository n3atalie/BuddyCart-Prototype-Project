package com.example.finalproject;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    private TextView checkoutMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        ImageButton btnBack = findViewById(R.id.btnBackToCart);
        btnBack.setOnClickListener(v -> finish());

        checkoutMessage = findViewById(R.id.checkoutMessage);

        StringBuilder summary = new StringBuilder();
        summary.append("🎉 Your order has been placed!\n\n📦 Order Summary:\n");

        double subtotal = 0.0;

        for (CartItem item : CartData.cartItems) {
            String name = item.getProduct().getName();
            int qty = item.getQuantity();
            double price = item.getProduct().getPrice();
            double sub = qty * price;
            subtotal += sub;

            summary.append("- ").append(name)
                    .append(" x ").append(qty)
                    .append(" = $").append(String.format("%.2f", sub))
                    .append("\n");
        }

        double tax = subtotal * 0.10;
        double shipping = 4.99;
        double total = subtotal + tax + shipping;

        summary.append("\nSubtotal: $").append(String.format("%.2f", subtotal));
        summary.append("\nTax (10%): $").append(String.format("%.2f", tax));
        summary.append("\nShipping: $").append(String.format("%.2f", shipping));
        summary.append("\n\nTotal: $").append(String.format("%.2f", total));

        checkoutMessage.setText(summary.toString());
    }
}
