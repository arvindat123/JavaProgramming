package org.General;

public class Hero {
	public void fight() {
		System.out.println("fight");
	}
	
	public static void main(String[] args) {
		Hero h = new Hero();
		//h.act();
		//h.fight();
		//Can super class reference variable hold an object of sub class? yes
		Actor actObj = new Actor();
		actObj.act();
		//actObj.fight();//Wrong compile time error, can call only super class(Actor) method
		//actObj.act(); //Correct
	}
}
	