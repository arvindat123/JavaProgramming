package org.Java8;
interface GfG2{
	   default void display()
	   {
	      System.out.println("GEEKSFORGEEKS");
	   }

	   static void staticMethod(){
		   System.out.println("Inside interface static method");
	   }
	   
	}

	public  class TestFunctionalInterface implements GfG2{
	public void display(){
		GfG2.super.display();
		System.out.println("inside class default method");
	}
	void staticMethod(){ //static method cannot be overridden
		System.out.println("inside class static method");
	}
		public static void main(String[] args) {
			TestFunctionalInterface obj = new TestFunctionalInterface();
			obj.display();//inside class default method -- if this method is not in class then interface method will be called
			obj.staticMethod();//inside class static method
			GfG2.staticMethod();//Inside interface static method
			//obj.staticMethod();//Static method may be invoked on containing interface class only
			//GfG2 f = new GfG2();//'GfG2' is abstract; cannot be instantiated
			//f.display();
		}
	}
	 