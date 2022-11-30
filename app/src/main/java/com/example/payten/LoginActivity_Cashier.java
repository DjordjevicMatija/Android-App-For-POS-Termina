package com.example.payten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity_Cashier extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private TextView textViewIncorrect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cashier);

        initialize();
        textViewIncorrect.setVisibility(View.INVISIBLE);
    }

    private void initialize() {
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textViewIncorrect = (TextView) findViewById(R.id.textViewIncorrect2);
    }

    public void LoginCashier(View view){
        if(editTextUsername.getText().toString().equals("admin") && editTextPassword.getText().toString().equals("admin")){
            Intent intent = new Intent(LoginActivity_Cashier.this, Seller.class);
            startActivity(intent);
        }
        else{
            textViewIncorrect.setVisibility(View.VISIBLE);
        }
    }

}