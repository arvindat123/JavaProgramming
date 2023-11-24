package org.Interface;

public interface A {
	int m();

	default void print(){
		System.out.println("inside A");
	}
	//void print(int i);
	//void print(String s);
	//static int my();// This needs implementation
}
