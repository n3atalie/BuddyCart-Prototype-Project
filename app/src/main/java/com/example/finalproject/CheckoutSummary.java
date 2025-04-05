package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.List;

public class CheckoutSummary extends AppCompatActivity {
    ImageButton btnBackCheckoutSummary;
    Button btnProceedToPayment;
    TextView totalTxt, gstTxt, pstTxt, finalTotalTxt, selectedCardText;
    List<Product> cartItems;
    private Gson gson = new Gson();
    private PaymentMethod selectedCard = null;

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
        selectedCardText = findViewById(R.id.selectedCardText);

        btnBackCheckoutSummary.setOnClickListener(v -> finish());

        // Get cart items
        cartItems = CartManager.getInstance().getCartItems();
        double total = 0;
        for (Product product : cartItems) {
            total += product.getPrice() * product.getQuantity();
        }

        double gst = total * 0.05;
        double pst = total * 0.07;
        double finalTotal = total + gst + pst;

        totalTxt.setText("Total: $" + String.format("%.2f", total));
        gstTxt.setText("GST (5%): $" + String.format("%.2f", gst));
        pstTxt.setText("PST (7%): $" + String.format("%.2f", pst));
        finalTotalTxt.setText("Final Total: $" + String.format("%.2f", finalTotal));

        // Get selected card
        String cardJson = getIntent().getStringExtra("selected_card");
        if (cardJson != null) {
            selectedCard = gson.fromJson(cardJson, PaymentMethod.class);
            String last4 = selectedCard.cardNumber.substring(selectedCard.cardNumber.length() - 4);
            selectedCardText.setText("Paying with **** " + last4 + " – " + selectedCard.name);
        }

        // Pass card to PaymentInformation
        btnProceedToPayment.setOnClickListener(v -> {
            Intent intent = new Intent(CheckoutSummary.this, PaymentInformation.class);
            if (selectedCard != null) {
                intent.putExtra("selected_card", gson.toJson(selectedCard));
            }
            startActivity(intent);
        });
    }
}
