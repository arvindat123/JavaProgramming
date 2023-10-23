package org.collection;

public class EqualsOverride {
	public static void main(String[] args) {
		Moof obj1 = new Moof(8);
		Moof obj2 = new Moof(8);
		
		if(obj1 == obj2) {
			System.out.println("obj1 == obj2 is true");
		}
		
		if(obj1.equals(obj2)) {
			System.out.println("obj1.equals(obj2)) is true");
		}
		
	}
}

class Moof {
	private int moofValue;
	Moof(int val){
		moofValue = val;
	}
	
	public int getMoofValue()
	{
		return moofValue;
	}
	
	public boolean equals(Object o) {
		if ((o instanceof Moof) && ((((Moof)o).getMoofValue() == this.moofValue))){
			return true;
		} else {
			return false;
		}
	}
}

