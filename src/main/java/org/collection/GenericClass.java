package org.collection;

import java.util.ArrayList;
import java.util.List;

public class GenericClass<T> {
	private List<T> values = new ArrayList<T>();

	void add(T value) {
		values.add(value);
	}
	
	void remove(T value) {
		values.remove(value);
	}
	
	T get(int index) {
		return values.get(index);
	}
	
	public static void main(String[] args) {
		GenericClass<String> strType = new GenericClass<String>();
		strType.add("arvind");
		strType.add("Aarabh");
		System.out.println(strType);
		GenericClass<Integer> intType = new GenericClass<Integer>();
		intType.add(1);
		intType.add(3);
		System.out.println(intType);
	}

}
