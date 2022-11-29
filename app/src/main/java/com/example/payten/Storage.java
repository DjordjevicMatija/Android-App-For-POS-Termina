package com.example.payten;
import java.util.ArrayList;


public class Storage {
    private ArrayList<Product> product_list = new ArrayList<Product>();

    public void add_to_list(Product product){
        product_list.add(product);
    }
}
