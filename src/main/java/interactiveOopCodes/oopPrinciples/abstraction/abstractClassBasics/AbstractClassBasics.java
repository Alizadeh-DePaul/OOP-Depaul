package interactiveOopCodes.oopPrinciples.abstraction.abstractClassBasics;

// Abstract Classes — Incomplete classes that subclasses must complete
// Abstraction = hiding complexity, exposing only what matters

// Real-life: A "Vehicle" blueprint — you know it has start() and stop(),
// but each vehicle type (Car, Bike) implements them differently.

abstract class Vehicle {
    protected String brand;

    Vehicle(String brand) {
        this.brand = brand;
    }

    // Abstract method — no body, subclass MUST implement
    abstract void start();

    // Concrete method — already complete, subclass CAN override
    void stop() {
        System.out.println(brand + " is stopping.");
    }

    void displayInfo() {
        System.out.println("Vehicle brand: " + brand);
    }
}

class Car extends Vehicle {
    Car(String brand) {
        super(brand);
    }

    @Override
    void start() {
        System.out.println(brand + " car: Turn key, engine roars!");
    }
}

class Bicycle extends Vehicle {
    Bicycle(String brand) {
        super(brand);
    }

    @Override
    void start() {
        System.out.println(brand + " bicycle: Start pedaling!");
    }

    @Override
    void stop() {
        System.out.println(brand + " bicycle: Squeeze the brakes!");
    }
}

public class AbstractClassBasics {
    public static void main(String[] args) {
        System.out.println("=== Abstract Class Basics ===\n");

        // Cannot do: Vehicle v = new Vehicle("Generic"); // Compile error!

        Car car = new Car("Toyota");
        car.displayInfo();
        car.start();
        car.stop();

        System.out.println();

        Bicycle bike = new Bicycle("Trek");
        bike.displayInfo();
        bike.start();
        bike.stop();

        System.out.println("\n--- Runtime Polymorphism via Abstract Reference ---");
        Vehicle v1 = new Car("Honda");
        Vehicle v2 = new Bicycle("Giant");
        v1.start();  // Calls Car's start()
        v2.start();  // Calls Bicycle's start()
    }
}
