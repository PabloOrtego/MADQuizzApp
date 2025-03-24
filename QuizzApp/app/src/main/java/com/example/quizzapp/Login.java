package com.example.quizzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (UserData.validateLogin(Login.this, email, password)) {
                    Intent intent = new Intent(Login.this, MainPage.class);
                    intent.putExtra("EMAIL", email);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
