package org.Interface;

import java.util.HashMap;

public class C implements B,A {

	public int m() {
		System.out.println("hi");
	return 0;
		
	}
	
	public static void main(String[] args) {
		C c = new C();
		System.out.println(c.m());
		HashMap<String, String> map = new HashMap<>();
		map.put(null,null);
		System.out.println(map.get(null));
		map.put(null, "Arvind");
		System.out.println(map.get(null));
	}
	
	//common method in interface should have same return type then SUCCESS
	//If any interface has different return type than other interface then method in class with throw compile time ERROR 
	//for in compatible return type
	//
}
