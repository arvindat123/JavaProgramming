package org.collection;

import java.util.ArrayList;
import java.util.Iterator;

public class ConcurrentModificationException {
	public static void main(String[] args) {
		
		ArrayList<Integer> list = new ArrayList<>();
		//CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
		
		for(int i = 0; i< 10; i++) {
			list.add(i);
		}
		
		// Display all the elements of ArrayList object
		System.out.println(list);
		
		// Creating an iterator object to iterate over the ArrayList
		Iterator<Integer> itr = list.iterator();
		
		//Condition check It holds true till there is single element remaining in the List
		while(itr.hasNext()) {
			System.out.println(itr.next());
			Integer value = itr.next();
			
			//int value2 = itr.next();
			//System.out.println(value + " -- "+value2);//exception for concurrentmodification 
			
			// Here we are trying to remove the one entry of ArrayList base on the if condition and hence
            // We will get Concurrent ModificationException
			
			if(value == 5) {
				System.out.println("Hi");
				list.remove(value);
			}
		}
		// Print and display the value of ArrayList object
       // System.out.println("List Value After Iteration:"+ list);
		
		//The above program is a single-threaded program and here we can avoid  ConcurrentModificationException by using iterator’s remove( ) function,
		
		
	}
}
