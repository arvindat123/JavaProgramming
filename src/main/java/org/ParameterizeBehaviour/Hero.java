package org.ParameterizeBehaviour;

public class Hero extends Actor{
	public void fight() {
		System.out.println("fight");
	}
	
	public static void main(String[] args) {
		Hero h = new Hero();
		h.act();
		h.fight();
		//Can super class reference variable hold an object of sub class? yes
		Actor actObj = new Hero();
		//actObj.fight();//Wrong compile time error, can call only super class(Actor) method
		actObj.act(); //Correct
	}
}
