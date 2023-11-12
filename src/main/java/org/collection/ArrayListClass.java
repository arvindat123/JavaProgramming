package org.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class ArrayListClass {

	public static void main(String[] args) {
		List<Integer> integer = new ArrayList<Integer>();
		integer.add(3);
		integer.add(3);
		integer.add(5);
		integer.add(6);
		integer.add(1);
		integer.add(null);
		System.out.println(integer);
		
		Hashtable<Integer,Integer> p = new Hashtable<>();
		//p.put(123,123);
		
		System.out.println("-----------------------");
		integer.add(2,100);
		System.out.println(integer);
		Iterator<Integer> integerIterator = integer.iterator();
		while(integerIterator.hasNext()) {
			System.out.println(integerIterator.next());
		}
		System.out.println("--->"+integer.indexOf(1000));
		Collections.sort(integer);
		System.out.println(integer);
		
		int a[] = {1,2,3};
		int aa[] = new int[] {1,2,3};
		int aaa[] = new int[5];
		
		List<Object> obj = new ArrayList<>(); //Object type so can add integer or string, it will compile 
		obj.add("arvind");
		obj.add(123);
		System.out.println(obj);
		
		List<Integer> obj1 = new ArrayList<>();
		//obj1.add("arvind"); compile time error as string is being assigned
		//obj1.add("123");compile time error as string is being assigned
		obj1.add(123);
		
		HashMap<Integer,Integer> map = new HashMap<>();
		map.put(1, 2);
		//map.put(2, "arvind"); //Compile time error
		
		
		
	}

}
