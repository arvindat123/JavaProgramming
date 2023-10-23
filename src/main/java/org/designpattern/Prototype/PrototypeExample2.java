package org.designpattern.Prototype;
//Shape Interface
interface Shape extends Cloneable {
	void draw();
	Shape clone();
}
//Concrete implementation of Shape
class Circle implements Shape {
	@Override
	public void draw() { System.out.println("Drawing a circle");}	
	public void hi() {System.out.println("Hi");	}	
	@Override 
	public Shape clone() {return new Circle();}
}
//Concrete implementation of Shape
class Square implements Shape {
	@Override
	public void draw() {System.out.println("Drawing a Square");}	
	@Override
	public Shape clone() {return new Square();}
}
//shaperegistry class to manage prototypes
class ShapeRegistry {
	private static final Shape circlePrototype = new Circle();
	private static final Shape squarePrototype = new Square();
	
	public static Shape getShape(String type) {
		if(type.equalsIgnoreCase("Circle")) {
			return circlePrototype.clone();
		} else if (type.equalsIgnoreCase("Square")) {
			return squarePrototype.clone();
		}		
		return null;
	}
}
//Client code
public class PrototypeExample2 {
	public static void main(String[] args) {
		Shape circle = ShapeRegistry.getShape("Circle");
		Shape square = ShapeRegistry.getShape("Square");
		circle.draw();
		//circle.hi(); not visible because of Shape Interface
		square.draw();
	}
}
