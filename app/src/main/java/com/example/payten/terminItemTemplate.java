package com.example.payten;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class terminItemTemplate {
    private String name;
    private List<Pair<String, Integer>> items;

    public terminItemTemplate(String name) {
        this.name = name;
        items = new ArrayList<Pair<String, Integer>>();
    }

    public void addItem(String name, int count) {
        items.add(new Pair<String, Integer>(name, count));
    }

    public List<Pair<String, Integer>> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }
}
