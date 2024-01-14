package org.designpattern.singleton;

import java.lang.reflect.Constructor;

/*class SingletonClass{
	
	//  instance initialized when loading the class
	public static SingletonClass instance = new SingletonClass();
	
	private SingletonClass() {
		
	}
	
}*/

 enum SingletonClass { instance; }

public class ReflectionBreakingSingleton {
	public static void main(String[] args) {
		SingletonClass instance1 = SingletonClass.instance;
		SingletonClass instance2 = null;
		
		try {
			Constructor[] constructors = SingletonClass.class.getDeclaredConstructors();
			for(Constructor cons : constructors) {
				 // Below code will destroy the singleton pattern
				cons.setAccessible(true);
				instance2 = (SingletonClass)cons.newInstance();
				break;
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("instance1 hashcode = "+instance1.hashCode());
		System.out.println("instance2 hashcode = "+instance2.hashCode());
		
	}
}
