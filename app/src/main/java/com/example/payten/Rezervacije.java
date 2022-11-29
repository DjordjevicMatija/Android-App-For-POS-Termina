package com.example.payten;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class Rezervacije extends AppCompatActivity {

    List<Termin> termin_list;
    List<String> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezervacije);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        termin_list = new ArrayList<Termin>();
        termin_list.add(new Termin(2022, 11, 29, 15, 16, 00, 00, "Proba 1"));
        termin_list.add(new Termin(2022, 11, 29, 18, 19, 45, 00, "Proba 2"));
        termin_list.add(new Termin(2022, 11, 26, 10, 11, 30, 00, "Proba 3"));

        ListView lv = (ListView) findViewById(R.id.eventList);

        eventList = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, eventList);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int day) {
                eventList.clear();
                getEvents(year, (month+1), day);
                adapter.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.termin_popup, null);

                // create the popup window
                int height = ConstraintLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width-40, height, focusable);


                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });

            }
        });

        /*
        * WindowManager.LayoutParams wp = getWindow().getAttributes();
wp.dimAmount = 0.75f;
        * */
    }

    private void getEvents(int year, int month, int day) {
        for (int i = 0; i < termin_list.size(); i++) {
            if (termin_list.get(i).compareDate(year, month, day)) {
                eventList.add(termin_list.get(i).getInfo());
            }
        }
    }
}