package com.hcdisat.weekone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        if (intent != null) {
            TextView emailText = findViewById(R.id.email_text);
            emailText.setText(intent.getStringExtra(LoginActivity.EXTRA_EMAIL));
        }
    }
}