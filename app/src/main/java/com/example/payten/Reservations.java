package com.example.payten;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Point;
import android.os.Bundle;

import android.util.Log;
import android.util.Pair;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Reservations extends AppCompatActivity {

    private List<Termin> termin_list;
    private List<Termin> currentTerminList;
    private List<String> eventList;

    private List<terminItemTemplate> templates;

    private AlertDialog.Builder dialog_builder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        templates = new ArrayList<terminItemTemplate>();

        termin_list = new ArrayList<Termin>();
        termin_list.add(new Termin(2022, 11, 29, "15", "16", "00", "00", "Proba 1"));
        termin_list.add(new Termin(2022, 11, 29, "18", "19", "45", "00", "Proba 2"));
        termin_list.add(new Termin(2022, 11, 26, "10", "11", "30", "00", "Proba 3"));

        termin_list.get(0).addProduct("Filter za ulje", 1);
        termin_list.get(0).addProduct("Filter za vazduh", 2);
        termin_list.get(0).addProduct("Armortizeri", 4);
        termin_list.get(0).addProduct("Plocice za kocnice", 6);
    }

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_reservation);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        ListView lv = (ListView) findViewById(R.id.eventList);

        eventList = new ArrayList<String>();
        currentTerminList = new ArrayList<Termin>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, eventList);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                eventList.clear();
                currentTerminList.clear();
                getEvents(year, (month+1), day);
                adapter.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog_builder = new AlertDialog.Builder(parent.getContext());
                final View add_new_popup = getLayoutInflater().inflate(R.layout.termin_popup, null);

                dialog_builder.setView(add_new_popup);
                dialog = dialog_builder.create();
                dialog.show();

                TextView nameTV = (TextView) add_new_popup.findViewById(R.id.reservationsNameTV);
                nameTV.setText(currentTerminList.get(position).getName());

                TextView dateTV = (TextView) add_new_popup.findViewById(R.id.reservationsDateTV);
                String date = currentTerminList.get(position).getYear() + "-" + currentTerminList.get(position).getMonth() + "-" + termin_list.get(position).getDay();
                dateTV.setText(date);

                TextView timeTV = (TextView) add_new_popup.findViewById(R.id.reservationsTimeTV);
                String time = currentTerminList.get(position).getStartHour() + ":" + currentTerminList.get(position).getStartMinute() + " - " + termin_list.get(position).getEndHour() + ":" + termin_list.get(position).getEndMinute();
                timeTV.setText(time);

                List<String> itemsList = new ArrayList<String>();
                List<Pair<String, Integer>> products = currentTerminList.get(position).getProducts();

                for (Pair<String, Integer> p : products) {
                    itemsList.add("Name: " + p.first + ", Amount: " + p.second);
                }

                ListView ilv = (ListView) add_new_popup.findViewById(R.id.itemsLV);
                ArrayAdapter<String> ilvAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, itemsList);
                ilv.setAdapter(ilvAdapter);
                ilvAdapter.notifyDataSetChanged();
            }
        });

        terminItemTemplate t1 = new terminItemTemplate("Mali servis");
        t1.addItem("Filter za ulje", 1);
        t1.addItem("Filter za vazduh", 2);
        t1.addItem("Plocice za kocnice", 8);

        terminItemTemplate t2 = new terminItemTemplate("Veliki servis");
        t2.addItem("Filter za ulje", 1);
        t2.addItem("Filter za vazduh", 2);
        t2.addItem("Plocice za kocnice", 8);
        t2.addItem("Kais", 1);
        t2.addItem("Guma", 4);

        templates.add(t1);
        templates.add(t2);

        List<String> templateNames = new ArrayList<String>();

        for (terminItemTemplate t : templates) {
            templateNames.add(t.getName());
        }

        Log.d("TEST", String.valueOf(templateNames.size()));

        Button addEventButton = (Button) findViewById(R.id.addEventButton);

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_builder = new AlertDialog.Builder(view.getContext());
                final View add_new_popup = getLayoutInflater().inflate(R.layout.addtermin_popup, null);

                dialog_builder.setView(add_new_popup);
                dialog = dialog_builder.create();
                dialog.show();

                TextView pickStartTimeTV = (TextView) add_new_popup.findViewById(R.id.addEventSTTV);

                pickStartTimeTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar c = Calendar.getInstance();

                        int hour = c.get(Calendar.HOUR_OF_DAY);
                        int minute = c.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {
                                        pickStartTimeTV.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
                                    }
                                }, hour, minute, true);
                        timePickerDialog.show();
                    }
                });

                TextView pickEndTimeTV = (TextView) add_new_popup.findViewById(R.id.addEventETTV);

                pickEndTimeTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar c = Calendar.getInstance();

                        int hour = c.get(Calendar.HOUR_OF_DAY);
                        int minute = c.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {
                                        pickEndTimeTV.setText(String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute));
                                    }
                                }, hour, minute, true);
                        timePickerDialog.show();
                    }
                });

                TextView pickDateTV = (TextView) add_new_popup.findViewById(R.id.addEventDateTV);

                pickDateTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar c = Calendar.getInstance();

                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                                new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        pickDateTV.setText(String.format("%02d", dayOfMonth) + "-" + String.format("%02d", (monthOfYear + 1)) + "-" + year);
                                    }
                                },
                                year, month, day);
                        datePickerDialog.show();
                    }
                });

                Spinner templateSpinner = (Spinner)add_new_popup.findViewById(R.id.addEventIGSpinner);

                ArrayAdapter<String> templateAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, templateNames);

                templateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                templateSpinner.setAdapter(templateAdapter);
                templateSpinner.setSelection(0);
                templateAdapter.notifyDataSetChanged();

                Button addNewEventButton = (Button) add_new_popup.findViewById(R.id.addNewEventButton);

                addNewEventButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView pickStartTimeTV = (TextView) add_new_popup.findViewById(R.id.addEventSTTV);
                        TextView pickEndTimeTV = (TextView) add_new_popup.findViewById(R.id.addEventETTV);
                        TextView pickDateTV = (TextView) add_new_popup.findViewById(R.id.addEventDateTV);
                        EditText addEventNameText = (EditText) add_new_popup.findViewById(R.id.addEventNameText);

                        if (pickDateTV.getText() != "" && pickEndTimeTV.getText() != "" && pickStartTimeTV.getText() != "" && addEventNameText.getText().toString() != "") {
                            List<String> startTime = Arrays.asList(pickStartTimeTV.getText().toString().split(":"));
                            List<String> endTime = Arrays.asList(pickEndTimeTV.getText().toString().split(":"));
                            List<String> date = Arrays.asList(pickDateTV.getText().toString().split("-"));

                            Termin ter = new Termin(Integer.parseInt(date.get(2)), Integer.parseInt(date.get(1)), Integer.parseInt(date.get(0)), startTime.get(0), endTime.get(0), startTime.get(1), endTime.get(1), addEventNameText.getText().toString());

                            int selectedTemplate = templateSpinner.getSelectedItemPosition();

                            for (Pair<String, Integer> tem : templates.get(selectedTemplate).getItems()) {
                                ter.addProduct(tem.first, tem.second);
                            }

                            termin_list.add(ter);
                            dialog.dismiss();
                        }
                    }
                });
            }
        });

        Button newTemplateButton = (Button) findViewById(R.id.newTemplateButton);

        newTemplateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ovde bi bio kod koji je povezan sa bazom
            }
        });


    }

    private void getEvents(int year, int month, int day) {
        for (int i = 0; i < termin_list.size(); i++) {
            if (termin_list.get(i).compareDate(year, month, day)) {
                eventList.add(termin_list.get(i).getInfo());
                currentTerminList.add(termin_list.get(i));
            }
        }
    }
}