package org.General;

public class StaticBlock {

        // Static variable
        static int x;

        // Static block
        static {
            System.out.println("This is a static block.");
            x = 10; // Initializing the static variable
        }

        // Other members of the class...

        // Constructor
        public StaticBlock() {
            System.out.println("Constructor called.");
        }

        public static void main(String[] args) {
            // Main method
            StaticBlock obj = new StaticBlock();
            System.out.println("Main method called.");

            // Accessing the static variable
            System.out.println("Value of x: " + x);
        }


}
