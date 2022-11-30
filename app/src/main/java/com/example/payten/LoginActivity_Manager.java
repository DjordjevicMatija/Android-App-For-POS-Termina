package com.example.payten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity_Manager extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_manager);

        initialize();
    }

    private void initialize() {
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    public void LoginManager(View view){
        if(editTextUsername.getText().toString().equals("admin") && editTextPassword.getText().toString().equals("admin")){
            Intent intent = new Intent(LoginActivity_Manager.this, Manager2_0.class);
            startActivity(intent);
        }
    }
}