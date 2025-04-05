package com.example.finalproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ManagePaymentsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PaymentAdapter adapter;
    private ArrayList<PaymentMethod> paymentList;
    private SharedPreferences prefs;
    private Gson gson = new Gson();

    private Button btnAddPayment, btnUsePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_payments);

        // Views
        recyclerView = findViewById(R.id.recyclerPayments);
        btnAddPayment = findViewById(R.id.btnAddPayment);
        btnUsePayment = findViewById(R.id.btnUsePayment);

        prefs = getSharedPreferences("PaymentPrefs", MODE_PRIVATE);
        loadPayments();

        // Setup RecyclerView
        adapter = new PaymentAdapter(paymentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Add new card
        btnAddPayment.setOnClickListener(v -> {
            Intent intent = new Intent(ManagePaymentsActivity.this, PaymentMethodActivity.class);
            startActivity(intent);
        });

        // Use selected card
        btnUsePayment.setOnClickListener(v -> {
            PaymentMethod selected = adapter.getSelectedPayment();
            if (selected == null) {
                Toast.makeText(this, "Please select a card", Toast.LENGTH_SHORT).show();
                return;
            }

            // Return the selected card to the previous screen
            Intent result = new Intent();
            result.putExtra("selected_card", gson.toJson(selected));
            setResult(RESULT_OK, result);
            finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPayments();
        adapter.updateData(paymentList);
    }

    private void loadPayments() {
        String json = prefs.getString("payment_list", null);
        Type type = new TypeToken<ArrayList<PaymentMethod>>() {}.getType();
        paymentList = gson.fromJson(json, type);
        if (paymentList == null) {
            paymentList = new ArrayList<>();
        }
    }
}
