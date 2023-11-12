package org.designpattern.FactoryPattern;

//an interface Vehicle:
interface Vehicle {
	void start();

	void accelerate();

	void stop();
}

//Now, let's create two classes that implement this interface, say Car and Motorcycle:
class SimpleCar implements Vehicle {
	@Override
	public void start() {
		System.out.println("SimpleCar started");
	}

	@Override
	public void accelerate() {
		System.out.println("SimpleCar accelerating");
	}

	@Override
	public void stop() {
		System.out.println("SimpleCar stopped");
	}
}

class SimpleMotorcycle implements Vehicle {
	@Override
	public void start() {
		System.out.println("Motorcycle started");
	}

	@Override
	public void accelerate() {
		System.out.println("Motorcycle accelerating");
	}

	@Override
	public void stop() {
		System.out.println("Motorcycle stopped");
	}
}

// Now, let's create a VehicleFactory interface:
interface SimpleVehicleFactory {
	Vehicle createVehicle();
}

// And then, two concrete implementations of the factory for creating Car and
// Motorcycle:
class CarFactory implements SimpleVehicleFactory {
	@Override
	public Vehicle createVehicle() {
		return new SimpleCar();
	}
}

class MotorcycleFactory implements SimpleVehicleFactory {
	@Override
	public Vehicle createVehicle() {
		return new SimpleMotorcycle();
	}
}

//Finally, you can use these factories to create objects without specifying their concrete classes:
public class FactoryClass {
	public static void main(String[] args) {
		SimpleVehicleFactory carFactory = new CarFactory();
		Vehicle car = carFactory.createVehicle();

		car.start();
		car.accelerate();
		car.stop();

		SimpleVehicleFactory motorcycleFactory = new MotorcycleFactory();
		Vehicle motorcycle = motorcycleFactory.createVehicle();
		motorcycle.start();
		motorcycle.accelerate();
		motorcycle.stop();
	}
}

/*
 * This way, you can create different types of vehicles without exposing the
 * instantiation logic in your client code. The client code uses the factory
 * interface to create objects, and you can easily switch between different
 * implementations by changing the factory.
 */
