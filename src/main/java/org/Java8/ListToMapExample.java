package org.Java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListToMapExample {
	public static void main(String[] args) {
		// Your list of objects
		// List<MyObject> myList = List.of(new MyObject(2), new MyObject(4), new
		// MyObject(6), new MyObject(8));
		List<MyObject> myList = Arrays.asList(new MyObject(2), new MyObject(4), new MyObject(6), new MyObject(8));

		// Convert list to map using the unique identifier as the key
		// Stream AP solution
		Map<Integer, MyObject> myMapUsingStreamAPI = myList.stream()
				.collect(Collectors.toMap(MyObject::getUniqueId, obj -> obj));

		// Convert list to map using a loop
		Map<Integer, MyObject> myMapWithoutStreamAPI = new HashMap<>();
		for (MyObject obj : myList) {
			myMapWithoutStreamAPI.put(obj.getUniqueId(), obj);
		}

		// Now you have a map where the key is the unique identifier
		// and the value is the corresponding object from the list
	}
}

class MyObject {
	private int uniqueId;

	public MyObject(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	// Constructor, getters, setters, etc.

	public int getUniqueId() {
		return uniqueId;
	}
}