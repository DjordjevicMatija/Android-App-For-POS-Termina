package com.example.payten;

public class Product {
    int stock = 0;
    int ordered = 0;
    int reserved = 0;
    int min_value =0;
    String name;

    //komentar

    public Product(String name, int min_value){
        this.name = name;
        this.min_value=min_value;
    }
}
