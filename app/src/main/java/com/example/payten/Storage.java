package com.example.payten;
import java.util.ArrayList;


public class Storage {
    ArrayList<Product> product_list = new ArrayList<Product>();
    //jjytygfy
    public void add_to_list(Product product, int stock){
        //exception da ne dodas proizvod koji ne postoji
        product.stock=stock;
        product_list.add(product);
    }

    public void add_products(String product_name, int number){
        for(int i = 0; i<product_list.size(); i++){
            if(product_name==product_list.get(i).name){
                product_list.get(i).stock+=number;
                if(product_list.get(i).stock<product_list.get(i).min_value){
                    //neki alert, pop up prozor, sta god
                }
            }
        }
    }

    public void remove_products(String product_name, int number){
        for(int i = 0; i<product_list.size(); i++){
            if(product_name==product_list.get(i).name){
                if(number<product_list.get(i).stock) {
                    product_list.get(i).stock -= number;
                    if(product_list.get(i).stock<product_list.get(i).min_value){
                        //neki alert, pop up prozor, sta god
                    }
                }else{product_list.get(i).stock=0;}
            }
        }
    }

    public void order_product(String product_name, int number){
        for(int i = 0; i<product_list.size(); i++){
            if(product_name==product_list.get(i).name){
                product_list.get(i).ordered+=number;
            }
        }
    }

    public void decrease_order(String product_name, int number){
        for(int i = 0; i<product_list.size(); i++){
            if(product_name==product_list.get(i).name){
                if(number<product_list.get(i).ordered) {
                    product_list.get(i).ordered -= number;
                }else{product_list.get(i).ordered = 0;}
            }
        }
    }

    public void cancel_order(String product_name){
        for(int i = 0; i<product_list.size(); i++){
            if(product_name==product_list.get(i).name){
                product_list.get(i).ordered=0;
            }
        }
    }

    public void reserve_products(String product_name, int number){
        for(int i = 0; i<product_list.size(); i++){
            if(product_name==product_list.get(i).name){
                product_list.get(i).reserved+=number;
            }
        }
    }

    public void decrease_reservation(String product_name, int number){
        for(int i = 0; i<product_list.size(); i++){
            if(product_name==product_list.get(i).name){
                if(number<product_list.get(i).reserved) {
                    product_list.get(i).reserved -= number;
                }else{product_list.get(i).reserved = 0;}
            }
        }
    }

    public void cancel_reservation(String product_name){
        for(int i = 0; i<product_list.size(); i++){
            if(product_name==product_list.get(i).name){
                product_list.get(i).reserved=0;
            }
        }
    }

//    public static void main(String[] args) {
//        Product p1=new Product("mleko");
//        Product p2=new Product("kafa");
//        Product p3=new Product("kikiriki");
//
//        Storage s=new Storage();
//
//        s.add_to_list(p1, 5);
//        s.add_to_list(p3, 10);
//        s.add_to_list(p2, 15);
//
//        s.add_products("kafa", 12);
//        s.add_products("kikiriki", 3);
//
//        s.remove_products("kafa", 17);
//
//        s.order_product("kikiriki", 15);
//
//        s.order_product("kafa", 3);
//
//        s.cancel_order("kafa");
//
//        s.decrease_order("kikiriki", 5);
////======================================================
//        s.reserve_products("kikiriki", 15);
//
//        s.reserve_products("kafa", 3);
//
//        s.cancel_reservation("kafa");
//
//        s.decrease_reservation("kikiriki", 5);
//
//
//        for(int i = 0; i<s.product_list.size(); i++) {
//            System.out.println(s.product_list.get(i).name + ", stock: " + s.product_list.get(i).stock
//            + ", ordered: "+ s.product_list.get(i).ordered + ", reserved: " + s.product_list.get(i).reserved);
//        }
//    }


    //stokovani u rezervisane
    //rezervisani u stokovane
    //izbaci iz rezervisanih
    //




}
