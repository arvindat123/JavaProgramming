package org.General;

public class TowerOfHanoi {

	public void move(int numberOfDiscs, char from, char to, char inter) {
		
		if(numberOfDiscs == 1) {
			System.out.println("Moving disc 1 from " + from + "to "+to);
		} else {
			move(numberOfDiscs-1,from,inter,to);
			System.out.println("moving disc "+numberOfDiscs +" from "+ from + "to "+ inter);
			move(numberOfDiscs-1,from,to,inter);
		}
		
	}
	
	public static void main(String[] args) {
		TowerOfHanoi toh = new TowerOfHanoi();
		toh.move(3, 'A', 'C', 'B');
	}
	
	
}
