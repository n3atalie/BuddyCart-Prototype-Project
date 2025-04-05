package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PaymentMethodActivity extends AppCompatActivity {

    private TextInputEditText etCardName, etCardNumber, etExpiry, etCVC, etAddress1, etCity, etEmail;
    private Button btnSave;
    private SharedPreferences prefs;
    private Gson gson = new Gson();
    private ArrayList<PaymentMethod> paymentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);

        // Bind views
        etEmail = findViewById(R.id.etEmail);
        etCardName = findViewById(R.id.etCardName);
        etCardNumber = findViewById(R.id.etCardNumber);
        etExpiry = findViewById(R.id.etExpiry);
        etCVC = findViewById(R.id.etCVC);
        etAddress1 = findViewById(R.id.etAddress1);
        etCity = findViewById(R.id.etCity);
        btnSave = findViewById(R.id.btnSavePayment);

        prefs = getSharedPreferences("PaymentPrefs", MODE_PRIVATE);
        loadPayments();

        btnSave.setOnClickListener(v -> savePaymentMethod());
    }

    private void loadPayments() {
        String json = prefs.getString("payment_list", null);
        Type type = new TypeToken<ArrayList<PaymentMethod>>() {}.getType();
        paymentList = gson.fromJson(json, type);
        if (paymentList == null) {
            paymentList = new ArrayList<>();
        }
    }

    private void savePaymentMethod() {
        String name = etCardName.getText().toString().trim();
        String number = etCardNumber.getText().toString().trim();
        String expiry = etExpiry.getText().toString().trim();
        String cvv = etCVC.getText().toString().trim();
        String address = etAddress1.getText().toString().trim() + ", " + etCity.getText().toString().trim();

        if (name.isEmpty() || number.isEmpty() || expiry.isEmpty() || cvv.isEmpty() || address.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        PaymentMethod newMethod = new PaymentMethod(name, number, expiry, cvv, address);
        paymentList.add(newMethod);

        SharedPreferences.Editor editor = prefs.edit();
        String json = gson.toJson(paymentList);
        editor.putString("payment_list", json);
        editor.apply();

        Toast.makeText(this, "Payment method saved", Toast.LENGTH_SHORT).show();
        finish(); // Close the screen
    }
}
