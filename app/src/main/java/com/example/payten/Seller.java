package com.example.payten;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class Seller extends AppCompatActivity {

//    EditText input;
//    ImageButton first_item;
//    ImageButton second_item;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_seller);
//
//        make_popup();
//    }
//
//    private void make_popup(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Title");
//        builder.setMessage("Number of sold products");
//
//        input = new EditText(this);
//        input.setInputType(InputType.TYPE_CLASS_NUMBER);
//        input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//        builder.setView(input);
//
//        // set positive button
//        builder.setPositiveButton("CHARGE", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String txt = input.getText().toString();
//                Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG).show();
//                input.setText("");
//            }
//        });
//
//        // set negative button
//        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int i) {
//                input.setText("");
//                dialog.dismiss();
//            }
//        });
//
//        // create dialog
//        AlertDialog dialog = builder.create();
//
//        // buttons
//        set_buttons(dialog);
//
//    }
//
//    private void set_buttons(AlertDialog dialog){
//        first_item = (ImageButton) findViewById(R.id.imageButton19);
//        first_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.setTitle("Akumulator");
//                dialog.show();
//            }
//        });
//
//        second_item = (ImageButton) findViewById(R.id.imageButton21);
//        second_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.setTitle("Felna");
//                dialog.show();
//            }
//        });
//    }

    EditText input;
    AlertDialog dialog;
    int selectedItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);

        Product p1=new Product("Akumulator");
        Product p4=new Product("Ulje");
        Product p2=new Product("Felna");
        Product p3=new Product("kikiriki");
        MainMenu.s.add_to_list(p1, 5);
        MainMenu.s.add_to_list(p3, 10);
        MainMenu.s.add_to_list(p2, 15);
        MainMenu.s.add_to_list(p4, 15);

        make_popup();
        fill_grid(dialog);
    }

    private void fill_grid(AlertDialog dialog) {
        //the layout on which you are working
        GridLayout layout = (GridLayout) findViewById(R.id.grid_layout);


        //set the properties for button
        for (int i = 0; i < MainMenu.s.product_list.size(); i++) {
            ImageButton btnTag = new ImageButton(this);
            String txt = MainMenu.s.product_list.get(i).name;
            switch(txt){
                case "Akumulator":
                    btnTag.setImageResource(R.drawable.akumulator);
                    break;
                case "Ulje":
                    btnTag.setImageResource(R.drawable.ulje_za_motor1);
                    break;
                case "Felna":
                    btnTag.setImageResource(R.drawable.felna);
                    break;
                default:
                    btnTag.setImageResource(R.drawable.akumulator2);
                    break;
            }
            btnTag.setId(i);



            int finalI = i;
            btnTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.setTitle(txt);
                    dialog.show();
                    selectedItem = finalI;
                }
            });

            //add button to the layout
            layout.addView(btnTag);
            }

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
                int amount = Integer.parseInt(input.getText().toString());
                if (amount <= MainMenu.s.product_list.get(selectedItem).stock) {
                    Toast.makeText(getApplicationContext(), amount + "", Toast.LENGTH_LONG).show();
                    input.setText("");
                    MainMenu.s.remove_products(MainMenu.s.product_list.get(selectedItem).name, amount);
                }

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
        dialog = builder.create();

    }
}