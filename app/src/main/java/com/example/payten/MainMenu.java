package com.example.payten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void LoginCashier(View view) {
        Intent intent = new Intent(MainMenu.this, LoginActivity_Cashier.class);
        startActivity(intent);
    }

    public void LoginManager(View view) {
        Intent intent = new Intent(MainMenu.this, Manager.class);
        startActivity(intent);
    }

    public void OpenRegistrations(View view) {
        Intent intent = new Intent(MainMenu.this, Rezervacije.class);
        startActivity(intent);
    }
}