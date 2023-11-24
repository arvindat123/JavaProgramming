package org.Interface;

import java.util.HashMap;

public class C implements B,A {

	public int m() {
		System.out.println("hi");
	return 0;
		
	}
	public void print(){
		B.super.print();
		return;
	}



	public static void main(String[] args) {
		C c = new C();
		System.out.println(c.m());
		c.print();


		HashMap<Integer, Integer> map = new HashMap<>();
		int a = 1;
		int b = 2;
		map.put(a,b);
		a = 3;
		System.out.println("number return :" +map.get(a));
		map.put(null,null);
		System.out.println(map.get(null));
		map.put(null, 8);
		System.out.println(map.get(null));
	}

	
	//common method in interface should have same return type then SUCCESS
	//If any interface has different return type than other interface then method in class with throw compile time ERROR 
	//for in compatible return type
	//
}
