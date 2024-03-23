package org.Java11;

public class NextBasedAccessControl {
    private String name = "I am alphabet";

    public class A {
        public void printName(){
            System.out.println(name); //access private members
        }
    }
}
