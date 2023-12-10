package org.Java8;
//Java program to demonstrate the case
// when two interfaces are overridden

// Creating Interface One

// Creating Interface Two


public class DefaultMethodInFunctionalInterface implements GfG2, gfg3 {

    // Interfaces are Overrided
    public void display() {

        GfG2.super.display();

        gfg3.super.display();
    }

    public static void main(String args[]) {
        DefaultMethodInFunctionalInterface obj = new DefaultMethodInFunctionalInterface();
        obj.display();
    }
}


