package org.Interface;

interface MyFunctionalInterface {
    default void defaultMethod() {
        System.out.println("Default method implementation");
    }
}

public class extendInterface implements MyFunctionalInterface {

    //Second way to access
  public void defaultMethod(){
      MyFunctionalInterface.super.defaultMethod();
  }

    public static void main(String[] args) {

        //First way
        MyFunctionalInterface myInstance = new MyFunctionalInterface() {
            @Override
            public void defaultMethod() {
                MyFunctionalInterface.super.defaultMethod();
            }
        };
        myInstance.defaultMethod(); // Accessing the default method

        //Second way
        extendInterface obj = new extendInterface();
        obj.defaultMethod();

    }
}
