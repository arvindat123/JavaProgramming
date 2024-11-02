package org.Constructor;

class Car {
    String model;
    int year;

    // Constructor
    Car(String model, int year) {
        this.model = model;
        this.year = year;
    }
}

public class CarMain {
    public static void main(String[] args) {
        Car car = new Car("Toyota", 2020);  // Constructor is called here
        System.out.println(car.model+"----"+car.year);
    }
}
