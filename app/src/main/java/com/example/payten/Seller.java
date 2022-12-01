package com.example.payten;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Seller extends AppCompatActivity {

    EditText input;
    ImageButton first_item;
    ImageButton second_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        make_popup();
    }

    private void make_popup(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("Number of sold products");

        input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        builder.setView(input);

        // set positive button
        builder.setPositiveButton("CHARGE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String txt = input.getText().toString();
                Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
                input.setText("");
            }
        });

        // set negative button
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                input.setText("");
                dialog.dismiss();
            }
        });

        // create dialog
        AlertDialog dialog = builder.create();

        // buttons
        set_buttons(dialog);

    }

    private void set_buttons(AlertDialog dialog){
        first_item = (ImageButton) findViewById(R.id.imageButton19);
        first_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setTitle("Akumulator");
                dialog.show();
            }
        });

        second_item = (ImageButton) findViewById(R.id.imageButton21);
        second_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setTitle("Felna");
                dialog.show();
            }
        });
    }
}