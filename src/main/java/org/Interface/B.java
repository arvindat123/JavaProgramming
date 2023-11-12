package org.Interface;

public interface B {
	int m();

	default void print(){
		System.out.println("inside B");
	}
}
