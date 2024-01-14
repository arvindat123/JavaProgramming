package org.General;

import java.util.Objects;

public class ObjectLearning {

	private int id;
	public ObjectLearning(int id) {
		this.id = id;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectLearning other = (ObjectLearning) obj;
		return id == other.id;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hi");
		ObjectLearning obj1 = new ObjectLearning(25);
		ObjectLearning obj2 = new ObjectLearning(25);
		if(obj1==obj2) {
			System.out.println("==========>Equals");
		}
		if(obj1.equals(obj2))//true if override equals method else false
		{
			System.out.println("====equals objects===");
		}
		System.out.println(obj1);
		System.out.println(obj2);
		System.out.println(obj1=obj2); 
		System.out.println(obj1);
		System.out.println(obj2);
		if(obj1==obj2 && obj1.equals(obj2)) {
			System.out.println("+++++++++++++=>Equals");
		}
	}

}
