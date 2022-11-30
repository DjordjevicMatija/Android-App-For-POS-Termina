package com.example.payten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {
    static Storage s=Storage.getInstance(); //ranije je bilo public static Storage s=Storage.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //s = Storage.getInstance(); //ovo nije radilo kada se pozove main u drugoj funkciji
        //bruuuhyysda
    }

    public void LoginCashier(View view) {
        Intent intent = new Intent(MainMenu.this, LoginActivity_Cashier.class);
        startActivity(intent);
    }

    public void LoginManager(View view) {
        Intent intent = new Intent(MainMenu.this, LoginActivity_Manager.class);
        startActivity(intent);
    }

    public void OpenRegistrations(View view) {
        Intent intent = new Intent(MainMenu.this, Rezervacije.class);
        startActivity(intent);
    }


}