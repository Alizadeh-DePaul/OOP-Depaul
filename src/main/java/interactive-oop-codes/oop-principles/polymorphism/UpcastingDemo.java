/**
 * Upcasting — Safe Implicit Casting to Supertype
 *
 * Upcasting is always safe because a subclass IS-A superclass.
 * The reference narrows the "view" of the object — you can only
 * call methods defined in the reference type. But overridden
 * methods still execute the child's version at runtime.
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Vehicle {
    void start() {
        System.out.println("Vehicle starts");
    }

    void showType() {
        System.out.println("Type: Vehicle");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car starts with key ignition");
    }

    @Override
    void showType() {
        System.out.println("Type: Car");
    }

    // Car-specific method — not visible through Vehicle reference
    void openTrunk() {
        System.out.println("Car trunk opened");
    }
}

class ElectricCar extends Car {
    @Override
    void start() {
        System.out.println("Electric car starts silently");
    }

    @Override
    void showType() {
        System.out.println("Type: ElectricCar");
    }

    void chargeBattery() {
        System.out.println("Battery charging...");
    }
}

class UpcastingDemo {
    public static void main(String[] args) {
        System.out.println("=== Upcasting Demo ===\n");

        // Implicit upcast: ElectricCar -> Car
        Car carRef = new ElectricCar();
        carRef.start();      // ElectricCar.start() — runtime polymorphism
        carRef.showType();   // ElectricCar.showType()
        carRef.openTrunk();  // OK — openTrunk() is defined in Car
        // carRef.chargeBattery();  // ERROR: Car doesn't define this

        System.out.println("\n--- Upcast to Vehicle ---");
        // Implicit upcast: ElectricCar -> Vehicle (two levels up)
        Vehicle vRef = new ElectricCar();
        vRef.start();        // ElectricCar.start() — polymorphism preserved!
        vRef.showType();     // ElectricCar.showType()
        // vRef.openTrunk();     // ERROR: Vehicle doesn't define this
        // vRef.chargeBattery(); // ERROR: Vehicle doesn't define this

        System.out.println("\n--- Polymorphic method parameter ---");
        printVehicle(new Car());
        printVehicle(new ElectricCar());
    }

    // Upcasting enables polymorphic parameters
    static void printVehicle(Vehicle v) {
        System.out.print("  ");
        v.showType();  // Calls the actual object's version
    }
}
