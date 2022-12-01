package com.example.payten;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Termin {
    private int year, month, day;
    private String name, start_hour, end_hour, start_minute, end_minute;
    private List<Pair<String, Integer>> products;

    public Termin(int year, int month, int day, String start_hour, String end_hour, String start_minute, String end_minute, String name) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.start_hour = start_hour;
        this.end_hour = end_hour;
        this.start_minute = start_minute;
        this.end_minute = end_minute;
        this.name = name;
        products = new ArrayList<Pair<String, Integer>>();
    }

    public int getYear() { return year; }
    public int getMonth() { return month; }
    public int getDay() { return day; }
    public String getStartHour() { return start_hour; }
    public String getEndHour() { return end_hour; }
    public String getStartMinute() { return start_minute; }
    public String getEndMinute() { return end_minute; }
    public String getName() { return name; }

    public Boolean compareDate(int targetYear, int targetMonth, int targetDay) {
        if (year == targetYear && month == targetMonth && day == targetDay) {
            return true;
        }else{
            return false;
        }
    }

    public String getInfo() {
        return name + " - " + year + "/" + month + "/" + day + " u: " + start_hour + ":" + start_minute + " do: " + end_hour + ":" + end_minute;
    }

    public void addProduct(String name, int count) {
        products.add(new Pair<String, Integer>(name, count));
    }

    public List<Pair<String, Integer>> getProducts() {
        return products;
    }
}
