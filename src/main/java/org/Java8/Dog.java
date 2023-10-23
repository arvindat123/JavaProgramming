package org.Java8;
class Dog {
	private String name;
	private int weight;
	private int age;
	
	public Dog(String name, int weight, int age) {
		super();
		this.name = name;
		this.weight = weight;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + "]";
	}
	
	
	
	
}