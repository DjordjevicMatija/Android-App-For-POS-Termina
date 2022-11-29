package com.example.payten;

public class Product {
    int stock = 0;
    int ordered = 0;
    int reserved = 0;
    String name;

    public Product(String name){
        this.name = name;
    }
}
