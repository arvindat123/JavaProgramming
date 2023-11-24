package org.General;

public class SubClass extends SuperClass{

	public SubClass() {

		System.out.println("Inside SubClass");
		//super.print("arvind");
	}

	void display(){
		super.print("arvind - display");
	}
	
	//void print(){};//'print()' cannot override 'print()' in 'org.General.SuperClass'; overridden method is final
	
	
	public static void main(String[] args) {
		SubClass sub = new SubClass();
		sub.display();
		SuperClass su = new SuperClass();
	}

}
