package org.collection;

import java.util.Vector;

public class VectorClass {

    // SynchronizedMethodDemo driver method
    public static void main(String[] args)
    {
        // Size of the Vector
        int n = 5;

        // Declaring the Vector with // initial size n
        Vector<Integer> v = new Vector<Integer>(n);

        // Appending new elements at // the end of the vector
        for (int i = 1; i <= n; i++)
            v.add(i);

        // Printing elements
        System.out.println(v);

        // Remove element at index 3
        v.remove(3);

        // Displaying the vector// after deletion
        System.out.println(v);

        // iterating over vector elements  // using for loop
        for (int i = 0; i < v.size(); i++)

            // Printing elements one by one
            System.out.print(v.get(i) + " ");

        // Case 1
        // Creating a default vector
        Vector v1 = new Vector();

        // Adding custom elements
        // using add() method
        v1.add(1);
        v1.add(2);
        v1.add("geeks");
        v1.add("forGeeks");
        v1.add(3);

        // Printing the vector elements to the console
        System.out.println("Vector v1 is " + v1);

        // Case 2
        // Creating generic vector
        Vector<Integer> v2 = new Vector<Integer>();

        // Adding custom elements
        // using add() method
        v2.add(1);
        v2.add(2);
        v2.add(3);

        // Printing the vector elements to the console
        System.out.println("Vector v2 is " + v2);

        // Creating an empty Vector
        Vector<Integer> vec_tor = new Vector<Integer>();

        // Use add() method to add elements in the vector
        vec_tor.add(12);
        vec_tor.add(23);
        vec_tor.add(22);
        vec_tor.add(10);
        vec_tor.add(20);

        // Displaying the Vector
        System.out.println("Vector: " + vec_tor);

        // Using set() method to replace 12 with 21
        System.out.println("The Object that is replaced is: "
                + vec_tor.set(0, 21));

        // Using set() method to replace 20 with 50
        System.out.println("The Object that is replaced is: "
                + vec_tor.set(4, 50));

        // Displaying the modified vector
        System.out.println("The new Vector is:" + vec_tor);

        // create default vector of capacity 10
        Vector v11 = new Vector();

        // Add elements using add() method
        v11.add(1);
        v11.add(2);
        v11.add("Geeks");
        v11.add("forGeeks");
        v11.add(4);

        // removing first occurrence element at 1
        v11.remove(1);

        // checking vector
        System.out.println("after removal: " + v11);

        // create an instance of vector
        Vector<String> vectorVar = new Vector<>();

        // Add elements using add() method
        vectorVar.add("Geeks");
        vectorVar.add("Geeks");
        vectorVar.add(1, "For");

        // Using the Get method and the
        // for loop
        for (int i = 0; i < vectorVar.size(); i++) {

            System.out.print(vectorVar.get(i) + " ");
        }

        System.out.println();

        // Using the for each loop
        for (String str : vectorVar)
            System.out.print(str + " ");
    }
}
