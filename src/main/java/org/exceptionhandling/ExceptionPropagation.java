package org.exceptionhandling;

public class ExceptionPropagation {

    public static void main(String[] args) {
        try {
            // Call method1, which may throw an exception
            method1();
        } catch (Exception e) {
            // Handle the exception at the top level
            System.out.println("Exception caught in main method: " + e.getMessage());
        }
    }

    public static void method1() throws NullPointerException {
        // Call method2, which may throw an exception
        method2();
    }

    public static void method2() throws NullPointerException {
        // Simulate an exception being thrown
       throw new NullPointerException("Exception in method2");
    }
}
