package org.General;

import java.util.Hashtable;

public final class Actor {
	
	//private static final int i = 0;
	int i = 0;
	
	public Actor() {
		
	}
	
	public int act() {
		System.out.println("Act");
		return 8;
	}
	
	public int act(int y) {
		System.out.println("Overloading");
		return 7;
	}
	
	public static void main(String[] args) {
		
		Actor a = new Actor();
		Hashtable<Integer,Integer> h = new Hashtable();
		h.put(1, 2);
		h.put(null, null);
		//System.out.println(i);//compile time error because i is not static member
	
	}
}
