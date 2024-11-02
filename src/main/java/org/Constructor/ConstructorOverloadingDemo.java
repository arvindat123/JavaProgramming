package org.Constructor;

class Car1 {
    String model;
    int year;

    // Constructor 1
    Car1(String model) {
        this.model = model;
        this.year = 2022;  // default year
    }

    // Constructor 2
    Car1(String model, int year) {
        this.model = model;
        this.year = year;
    }
}

public class ConstructorOverloadingDemo {
    public static void main(String[] args) {
        Car1 car1 = new Car1("Toyota");         // Calls first constructor
        Car1 car2 = new Car1("Ford", 2020);     // Calls second constructor
    }
}
