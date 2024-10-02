package org.algorithms;

import java.util.ArrayList;
import java.util.List;

public class GenericsExample1 {
    public static void main(String[] args) {

        // Example without Generics
        List names = new ArrayList();
        names.add("Kelly");
        String name = (String) names.get(0); // type casting is required for each element
        System.out.println("First name: " + name);
        names.add(7);
        names.add(true);
        System.out.println(names.get(2));



        // Example with Generics
        List<String> names2 = new ArrayList();
        names2.add("Kelly");
        String name2 = names2.get(0); // type casting is not required. Compiler checks while adding element to list.
        System.out.println("First name: " + name2);
        //names2.add(7);

    }
}
