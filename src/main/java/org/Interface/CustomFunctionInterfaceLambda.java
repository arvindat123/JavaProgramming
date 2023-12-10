package org.Interface;

@FunctionalInterface
interface CustomFunctionalInterface {
    void display();
}
public class CustomFunctionInterfaceLambda {
    public static void main(String args[]) {
        // implementation Using Anonymous inner class
        CustomFunctionalInterface test1 = new CustomFunctionalInterface() {
            public void display() {
                System.out.println("Display using Anonymous inner class");
            }
        };
        test1.display();

        // implementation Using Lambda Expression
        CustomFunctionalInterface test2 = () -> {    // lambda expression
            System.out.println("Display using Lambda Expression");
        };
        test2.display();
    }
}
