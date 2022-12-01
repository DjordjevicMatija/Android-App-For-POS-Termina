package com.example.payten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class MainMenu extends AppCompatActivity {
    public static Storage s; //ranije je bilo public static Storage s=Storage.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        s = Storage.getInstance();
    }

    public void LoginCashier(View view) {
        Intent intent = new Intent(MainMenu.this, LoginActivity_Cashier.class);
        startActivity(intent);
    }

    public void LoginManager(View view) {
        Intent intent = new Intent(MainMenu.this, Manager2_0.class);
        startActivity(intent);
    }

    public void OpenRegistrations(View view) {
        Intent intent = new Intent(MainMenu.this, Reservations.class);
        startActivity(intent);
    }

}