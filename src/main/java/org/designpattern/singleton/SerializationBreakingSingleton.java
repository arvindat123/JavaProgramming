package org.designpattern.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class SingletonSerialization implements Serializable {
	
	public static SingletonSerialization instance = new SingletonSerialization();
	private SingletonSerialization() {
		//private constructor
	}
	
	// implement readResolve method
  protected Object readResolve() { return instance; }
}


public class SerializationBreakingSingleton {
	public static void main(String[] args) {
		try {
			SingletonSerialization instance1 = SingletonSerialization.instance;
			
			//serialize from object to file
			ObjectOutput out = new ObjectOutputStream(new FileOutputStream("D:\\EclipseWorkspace\\JavaLearning\\JavaExamples\\src\\com\\JavaTest\\designPattern\\singleton\\file.txt"));
			out.writeObject(instance1);
			out.close();
			
			//de serialize from file to object
			ObjectInput in = new ObjectInputStream(new FileInputStream("D:\\EclipseWorkspace\\JavaLearning\\JavaExamples\\src\\com\\JavaTest\\designPattern\\singleton\\file.txt"));
			SingletonSerialization instance2 = (SingletonSerialization)in.readObject();
			in.close();
			
			System.out.println("Instance1 hashcode = "+ instance1.hashCode());
			System.out.println("Instance2 hashcode = "+ instance2.hashCode());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
