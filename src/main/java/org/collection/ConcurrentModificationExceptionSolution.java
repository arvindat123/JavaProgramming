package org.collection;

import java.util.ArrayList;
import java.util.Iterator;

public class ConcurrentModificationExceptionSolution {
	 public static void main(String[] args)
	    {
	 
	        // Creating an collection class object
	        // Declaring object of integer type
	        ArrayList<Integer> list = new ArrayList<>();
	 
	        // Adding element to ArrayList
	        // using add() method
	 
	        // Custom integer input entries
	        list.add(1);
	        list.add(2);
	        list.add(3);
	        list.add(4);
	        list.add(5);
	 
	        // Display the value of ArrayList
	        System.out.println("List Value Before Iteration:"
	                           + list);
	 
	        // Creating an iterator object
	        // to iterate over the ArrayList
	        Iterator<Integer> itr = list.iterator();
	 
	        // Condition check
	        // it holds true till there is single element
	        // remaining in the List
	        while (itr.hasNext()) {
	 
	            // next() method() looks out for next element in
	            // the List
	            Integer value = itr.next();
	 
	            // Here we are trying to remove the one entry of
	            // ArrayList base on the given if condition and
	            // hence
	 
	            // We will get Concurrent ModificationException
	            if (value.equals(3))
	                itr.remove();
	        }
	        // Display the value of ArrayList
	        System.out.println("List Value After iteration:"
	                           + list);
	    }
	 //Note: In multi-threaded program we can avoid ConcurrentModificaionException by using  ConcurrentHashMap and CopyOnWriteArrayList classes. These classes help us in avoiding ConcurrentModificaionException.
}
