package org.Java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MaxExample {
    public static void main(String[] args) {

        List<Product> productsList = new ArrayList<Product>();
        // Adding Products
        productsList.add(new Product(1, "HP Laptop", 25000f));
        productsList.add(new Product(2, "Dell Laptop", 30000f));
        productsList.add(new Product(3, "Lenevo Laptop", 28000f));
        productsList.add(new Product(4, "Sony Laptop", 90000f));
        productsList.add(new Product(5, "Apple Laptop", 90000f));

        //First way
        Optional<Float> f = productsList.stream().map(obj -> obj.getPrice()).sorted((a, b) -> b.compareTo(a)).findFirst();
        if (f.isPresent()) {
            System.out.println(f.get());
        }

        //Second way
        // Product product = productsList.stream().max((a, b) -> a.getPrice() > b.getPrice() ? 1 : -1).get();


        //Third way
        System.out.println(productsList.stream().max(Comparator.comparing(Product::getPrice)).get().getPrice());


    }
}

class Product {
    int id;
    String name;
    float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

