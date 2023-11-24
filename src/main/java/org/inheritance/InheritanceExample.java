package org.inheritance;

import java.util.Objects;

public class InheritanceExample {
    public static void main(String[] args) {
        InheritanceExample obj1 = new InheritanceExample();
        InheritanceExample obj2 = new InheritanceExample();
        if(!obj1.equals(obj2)) {
            System.out.println("Objects are not equals");
        }
        if(!(obj1 == obj2)) {
            System.out.println("Memory address also not same");
        }
        if(obj1 instanceof Object){
            System.out.println("obj1 is an Object");
        }
    }
}
