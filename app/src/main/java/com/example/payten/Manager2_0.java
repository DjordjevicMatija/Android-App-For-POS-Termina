package com.example.payten;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Manager2_0 extends AppCompatActivity {


    private AlertDialog.Builder dialog_builder;
    private AlertDialog dialog, dialog2;
    private Button newitempopup_add, newitempopup_cancel;
    private EditText newitempopup_name, newitempopup_amount;
    private String txt_edit;
    private ArrayList<TableRow> rows= new ArrayList<TableRow>();
    String name;
    TextView reservedTV;
    TextView stockTV;
    TextView orderedTV;
    int buttonType;
    int itemCount;
    TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager2_0);
        itemCount = 0;
        MakeTable();
        pop_up();
    }

    public void pop_up() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");
        builder.setMessage("Number of products to change");

        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        input.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        builder.setView(input);
        // set positive button
        builder.setPositiveButton("CHANGE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                txt_edit = input.getText().toString();
                Toast.makeText(getApplicationContext(), txt_edit, Toast.LENGTH_LONG).show();
                switch (buttonType) {
                    case 0:
                        MainMenu.s.add_products(name,Integer.parseInt(txt_edit));
                        //stockTV.setText(String.valueOf(MainMenu.s.getProduct(name).stock));
                        break;
                    case 1:
                        MainMenu.s.reserve_products(name, Integer.parseInt(txt_edit));
                        MainMenu.s.remove_products(name, Integer.parseInt(txt_edit));
                        //stockTV.setText(String.valueOf(MainMenu.s.getProduct(name).stock));
                        //reservedTV.setText(String.valueOf(MainMenu.s.getProduct(name).reserved));
                        break;
                    case 2:
                        MainMenu.s.order_product(name, Integer.parseInt(txt_edit));
                        //orderedTV.setText(txt_edit);
                        break;
                }

                clearTable();
                MakeTable();

                input.setText("");
                dialog.dismiss();
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
        dialog2 = builder.create();
        //txt3.setText(txt_edit[0]);};
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

    public void ClickReserve(View v){
        TextView txt1 = (TextView)findViewById(v.getId());
    }

    public void clearTable() {
        table.removeViews(1, itemCount);
    }

    public void MakeTable() { //create a new row to add
        table = (TableLayout) findViewById(R.id.tableLayout1);
        //table.removeAllViews();

        for(int i = 0; i < MainMenu.s.product_list.size(); i++) {
            itemCount++;
            TableRow row = new TableRow(getApplicationContext());
            //add Layouts to your new row

            TextView txt1 = new TextView(getApplicationContext());
            TextView txt2 = new TextView(getApplicationContext());
            TextView txt3 = new TextView(getApplicationContext());
            TextView txt4 = new TextView(getApplicationContext());
            txt1.setText(MainMenu.s.product_list.get(i).name);
            txt2.setText(MainMenu.s.product_list.get(i).stock + "");
            txt3.setText(MainMenu.s.product_list.get(i).reserved + "");
            txt4.setText(MainMenu.s.product_list.get(i).ordered + "");
            txt2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            txt3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            txt4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

            if (MainMenu.s.product_list.get(i).stock < 5) {
                txt1.setBackgroundColor(0xFFFF0000);
                txt2.setBackgroundColor(0xFFFF0000);
                txt3.setBackgroundColor(0xFFFF0000);
                txt4.setBackgroundColor(0xFFFF0000);
            }

            int finalI = i;
            txt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog2.setTitle(name);
                    dialog2.show();
                    name=MainMenu.s.product_list.get(finalI).name;
                    stockTV = txt2;
                    buttonType=0;
                    //Toast.makeText(getApplicationContext(), txt_edit, Toast.LENGTH_LONG).show();

                    //txt3.setText(txt_edit);
                }
            });
            txt3.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       dialog2.setTitle(name);
                       dialog2.show();
                       name=MainMenu.s.product_list.get(finalI).name;
                       stockTV = txt2;
                       reservedTV = txt3;
                       buttonType=1;
                       //Toast.makeText(getApplicationContext(), txt_edit, Toast.LENGTH_LONG).show();

                       //txt3.setText(txt_edit);
                   }
               });
            txt4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog2.setTitle(name);
                    dialog2.show();
                    name=MainMenu.s.product_list.get(finalI).name;
                    orderedTV = txt4;
                    buttonType=2;
                    //Toast.makeText(getApplicationContext(), txt_edit, Toast.LENGTH_LONG).show();

                    //txt3.setText(txt_edit);
                }
            });
            row.addView(txt1);
            row.addView(txt2);
            row.addView(txt3);
            row.addView(txt4);
            table.addView(row);
            //add your new row to the TableLayout:


        }

        //TableLayout table = new TableLayout()}
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

                String name = newitempopup_name.getText().toString();
                int amount = Integer.parseInt(newitempopup_amount.getText().toString());

                Product p = new Product(name);

                MainMenu.s.add_to_list(p, amount);

                MakeTable();



                dialog.dismiss();
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
        Toast.makeText(getApplicationContext(), "add f-ja", Toast.LENGTH_LONG).show();

        EditText text = (EditText)findViewById(R.id.newitempopup_name);
        String name = text.getText().toString();
        text = (EditText) findViewById(R.id.newitempopup_amount);
        int amount = Integer.parseInt(text.getText().toString());

        Product p = new Product(name);

        MainMenu.s.add_to_list(p, amount);
        Toast.makeText(getApplicationContext(), p.name, Toast.LENGTH_SHORT).show();
    }

//    public static void main(String[] args) {
//        Product p1=new Product("mleko");
//        Product p2=new Product("kafa");
//        Product p3=new Product("kikiriki");
//
//
//
//        MainMenu.s.add_to_list(p1, 5);
//        MainMenu.s.add_to_list(p3, 10);
//
//        MainMenu.s.add_to_list(p2, 15);
//
//        MainMenu.s.add_products("kafa", 12);
//        MainMenu.s.add_products("kikiriki", 3);
//
//        MainMenu.s.remove_products("kafa", 17);
//
//        MainMenu.s.order_product("kikiriki", 15);
//
//        MainMenu.s.order_product("kafa", 3);
//
//        MainMenu.s.cancel_order("kafa");
//
//        MainMenu.s.decrease_order("kikiriki", 5);
////======================================================
//        MainMenu.s.reserve_products("kikiriki", 15);
//
//        MainMenu.s.reserve_products("kafa", 3);
//
//        MainMenu.s.cancel_reservation("kafa");
//
//        MainMenu.s.decrease_reservation("kikiriki", 5);
//
//
//        for(int i = 0; i<MainMenu.s.product_list.size(); i++) {
//            System.out.println(MainMenu.s.product_list.get(i).name + ", stock: " + MainMenu.s.product_list.get(i).stock
//                    + ", ordered: "+ MainMenu.s.product_list.get(i).ordered + ", reserved: " + MainMenu.s.product_list.get(i).reserved);
//        }
//    }

}