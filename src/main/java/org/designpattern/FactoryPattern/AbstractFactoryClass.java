package org.designpattern.FactoryPattern;

/*The Abstract Factory Design Pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes. Let's create an example in Java.

Suppose you have a scenario where you're creating both cars and motorcycles, and for each type, there are different variants: economy and luxury.
 */

//First, let's define the abstract product interfaces:

//Abstract Product A
interface Car {
	void drive();
}

//Abstract Product B
interface Motorcycle {
	void ride();
}

//Now, let's create concrete implementations for these products:


// Concrete Product A1
class EconomyCar implements Car {
	@Override
	public void drive() {
		System.out.println("Driving an economy car");
	}
}

// Concrete Product A2
class LuxuryCar implements Car {
	@Override
	public void drive() {
		System.out.println("Driving a luxury car");
	}
}

// Concrete Product B1
class EconomyMotorcycle implements Motorcycle {
	@Override
	public void ride() {
		System.out.println("Riding an economy motorcycle");
	}
}

// Concrete Product B2
class LuxuryMotorcycle implements Motorcycle {
	@Override
	public void ride() {
		System.out.println("Riding a luxury motorcycle");
	}
}


//Next, let's define the abstract factory interface:


// Abstract Factory
interface VehicleFactory {
	Car createCar();
	Motorcycle createMotorcycle();
}


//Now, we'll implement two concrete factories, one for each variant (economy and luxury):


// Concrete Factory 1
class EconomyVehicleFactory implements VehicleFactory {
	@Override
	public Car createCar() {
		return new EconomyCar();
	}

	@Override
	public Motorcycle createMotorcycle() {
		return new EconomyMotorcycle();
	}
}

// Concrete Factory 2
class LuxuryVehicleFactory implements VehicleFactory {
	@Override
	public Car createCar() {
		return new LuxuryCar();
	}

	@Override
	public Motorcycle createMotorcycle() {
		return new LuxuryMotorcycle();
	}
}


//Finally, you can use these factories to create families of related objects:


public class AbstractFactoryClass {
	public static void main(String[] args) {
		VehicleFactory economyFactory = new EconomyVehicleFactory();
		Car economyCar = economyFactory.createCar();
		Motorcycle economyMotorcycle = economyFactory.createMotorcycle();

		economyCar.drive();
		economyMotorcycle.ride();

		VehicleFactory luxuryFactory = new LuxuryVehicleFactory();
		Car luxuryCar = luxuryFactory.createCar();
		Motorcycle luxuryMotorcycle = luxuryFactory.createMotorcycle();

		luxuryCar.drive();
		luxuryMotorcycle.ride();
	}
}


/*This way, you can ensure that the created objects are compatible and belong to the same family. The client code does not need to know the concrete classes; it works with the abstract interfaces and factories.*/


