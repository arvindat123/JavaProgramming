package org.General;

import java.util.Hashtable;

public final class Actor  {
	
	//private static final int i = 0;
	//If class is final then class cannot be extended but object of final class can be created
	//If class has private constructor then class cannot be extended or object cannot be created
	int i = 0;
	
	public Actor() {
		//super("arvind",37);
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
		//FinalImmutableClass ab = new FinalImmutableClass("arvind",37);
	
	}
}
