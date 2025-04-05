package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactUs extends AppCompatActivity {

    Button btnSubmit;
    EditText contactName, contactEmail, contactMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_us);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageButton backToMain = findViewById(R.id.linearlayout);
        btnSubmit= findViewById(R.id.btnSubmit);
        contactEmail = findViewById(R.id.contactEmail);
        contactName = findViewById(R.id.contactName);
        contactMessage = findViewById(R.id.contactMessage);

        backToMain.setOnClickListener(v -> finish());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTxt = contactEmail.getText().toString();
                String nameTxt = contactName.getText().toString();
                String msgTxt = contactMessage.getText().toString();

                String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

                if ((emailTxt.isEmpty()) && (nameTxt.isEmpty()) && (msgTxt.isEmpty())){
                    Toast.makeText(ContactUs.this, "Please enter your name, email, and message.", Toast.LENGTH_SHORT).show();
                } else if (!emailTxt.matches(emailPattern)) {
                    Toast.makeText(ContactUs.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ContactUs.this, ContactSubmission.class);
                    startActivity(intent);
                }
            }
        });
    }
}