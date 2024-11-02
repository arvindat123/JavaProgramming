package org.collection;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapExample {
	public static void main(String[] args) {
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		
		//Adding element to the map
		map.put("A",1);
		map.put("B",2);
		map.put("C",3);
		/*
		map.put(null, null); //NullPointerException
		map.put("E", null); //NullPointerException
		map.put(null, 5); // NullPointerException
		*/

		System.out.println("Map size = "+ map.size());
		
		//Getting value from map
		int value = map.get("A");
		System.out.println(value);
		
		//removing elemtns from the map
		map.remove("B");
		System.out.println("Map size = "+ map.size() + map);
	}
}
