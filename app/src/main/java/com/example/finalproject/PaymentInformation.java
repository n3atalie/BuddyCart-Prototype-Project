package com.example.finalproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class PaymentInformation extends AppCompatActivity {

    private Gson gson = new Gson();
    private PaymentMethod selectedCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_information);

        TextView cardInfoView = new TextView(this);
        setContentView(cardInfoView);

        String cardJson = getIntent().getStringExtra("selected_card");
        if (cardJson != null) {
            selectedCard = gson.fromJson(cardJson, PaymentMethod.class);
            String last4 = selectedCard.cardNumber.substring(selectedCard.cardNumber.length() - 4);
            cardInfoView.setText("You’re paying with **** " + last4 + "\nCardholder: " + selectedCard.name);
            cardInfoView.setTextSize(18f);
            cardInfoView.setPadding(32, 64, 32, 32);
        } else {
            cardInfoView.setText("No card received.");
        }
    }
}
