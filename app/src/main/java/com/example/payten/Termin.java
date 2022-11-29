package com.example.payten;

import android.util.Log;

public class Termin {
    private int year, month, day, start_hour, end_hour, start_minute, end_minute;
    private String name;

    public Termin(int year, int month, int day, int start_hour, int end_hour, int start_minute, int end_minute, String name) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.start_hour = start_hour;
        this.end_hour = end_hour;
        this.start_minute = start_minute;
        this.end_minute = end_minute;
        this.name = name;
    }

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
}
