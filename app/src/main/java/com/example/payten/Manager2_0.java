package com.example.payten;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Manager2_0 extends AppCompatActivity {


    private AlertDialog.Builder dialog_builder;
    private AlertDialog dialog;
    private Button newitempopup_add, newitempopup_cancel;
    private EditText newitempopup_name, newitempopup_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager2_0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.menuitem_add_new){
            create_new_dialog();
        }
        return super.onOptionsItemSelected(item);
    }

    //call popup
    public void create_new_dialog(){
        dialog_builder = new AlertDialog.Builder(this);
        final View add_new_popup = getLayoutInflater().inflate(R.layout.popup_add_new, null);

        newitempopup_name = (EditText) add_new_popup.findViewById(R.id.newitempopup_name);
        newitempopup_amount = (EditText) add_new_popup.findViewById(R.id.newitempopup_amount);
        newitempopup_add = (Button) add_new_popup.findViewById(R.id.newitempopup_add);
        newitempopup_cancel = (Button) add_new_popup.findViewById(R.id.newitempopup_cancel);

        dialog_builder.setView(add_new_popup);
        dialog = dialog_builder.create();
        dialog.show();

        newitempopup_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //define add button here
                //add_item();
            }
        });

        newitempopup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //define cancel button here
                dialog.dismiss();
            }
        });
    }

    public void add_item(){
        EditText text = (EditText)findViewById(R.id.editname);
        String name = text.getText().toString();
        text = (EditText) findViewById(R.id.editname1);
        int amount = Integer.parseInt(text.getText().toString());

        Product p = new Product(name);

        //MainMenu.s.add_to_list(p, amount);
        //sadasd
    }

    public static void main(String[] args) {
        Product p1=new Product("mleko");
        Product p2=new Product("kafa");
        Product p3=new Product("kikiriki");



        MainMenu.s.add_to_list(p1, 5);
        MainMenu.s.add_to_list(p3, 10);

        MainMenu.s.add_to_list(p2, 15);

        MainMenu.s.add_products("kafa", 12);
        MainMenu.s.add_products("kikiriki", 3);

        MainMenu.s.remove_products("kafa", 17);

        MainMenu.s.order_product("kikiriki", 15);

        MainMenu.s.order_product("kafa", 3);

        MainMenu.s.cancel_order("kafa");

        MainMenu.s.decrease_order("kikiriki", 5);
//======================================================
        MainMenu.s.reserve_products("kikiriki", 15);

        MainMenu.s.reserve_products("kafa", 3);

        MainMenu.s.cancel_reservation("kafa");

        MainMenu.s.decrease_reservation("kikiriki", 5);


        for(int i = 0; i<MainMenu.s.product_list.size(); i++) {
            System.out.println(MainMenu.s.product_list.get(i).name + ", stock: " + MainMenu.s.product_list.get(i).stock
                    + ", ordered: "+ MainMenu.s.product_list.get(i).ordered + ", reserved: " + MainMenu.s.product_list.get(i).reserved);
        }
    }

}