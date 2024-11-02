package org.General;

public class StaticBlock {

        // Static variable
        static int x;
        final int y;

        // Static block
        static {
            System.out.println("This is a static block.");
            x = 10; // Initializing the static variable
            //y = 10;
        }

        // Other members of the class...

        // Constructor
        public StaticBlock() {
            y = 10;
            System.out.println("Constructor called.");
        }

        public static void main(String[] args) {

            // SynchronizedMethodDemo method
            StaticBlock obj = new StaticBlock();
            System.out.println("SynchronizedMethodDemo method called.");

            // Accessing the static variable
            System.out.println("Value of x: " + x);
        }


}
