package com.example.finalproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        TextView text = findViewById(R.id.checkoutMessage);
        text.setText("Your order has been placed!\nThank you for shopping!!");
    }
}
