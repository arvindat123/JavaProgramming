package org.collection;

public class DuckComparable implements Comparable<DuckComparable> {
	String name;
	String color;
	int age;
	public DuckComparable(String name, String color, int age) {
		super();
		this.name = name;
		this.color = color;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "DuckComparable [name=" + name + ", color=" + color + ", age=" + age + "]";
	}
	
	@Override
	public int compareTo(DuckComparable duck) {
		//return duck.getName().compareTo(this.getName()); //decreasing order
		return this.getName().compareTo(duck.getName()); //increasing order
	}
	
}
