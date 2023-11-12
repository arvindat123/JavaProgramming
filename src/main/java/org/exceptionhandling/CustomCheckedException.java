package org.exceptionhandling;

//A class that represent user defined  exception

class MyException extends Exception {
	MyException(String str){
		//System.out.println("Called");
		super(str); //Call constructor of parent class
	}
}

//Class that uses above exception : MyException
public class CustomCheckedException {

	public static void main(String[] args) {
		try {
			// Throw an object of user defined exception
			throw new MyException("Exception from User program");
		} catch (MyException e) {
			System.out.println("In Catch");
			System.out.println("Exception message : " + e.getMessage());
		}
		catch(Exception e){
			System.out.println("within Exception " + e.getMessage());
		}

	}

}
