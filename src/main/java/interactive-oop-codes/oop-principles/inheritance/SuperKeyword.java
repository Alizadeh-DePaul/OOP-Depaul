/**
 * The super Keyword — Three Uses
 *
 * 1. super(args)   — call parent constructor (must be 1st statement)
 * 2. super.field   — access parent's field (when shadowed)
 * 3. super.method  — call parent's method (when overridden)
 *
 * Key rules:
 * - super() must be the first statement in a constructor
 * - If omitted, Java inserts super() (no-arg) automatically
 * - super refers to the immediate parent class
 * - Fields resolve by reference type; methods by actual object type
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Vehicle {
    String type = "Vehicle";
    int year;

    Vehicle(int year) {
        System.out.println("  Vehicle constructor: year=" + year);
        this.year = year;
    }

    void start() {
        System.out.println("  Vehicle starts");
    }

    String info() {
        return "Vehicle(year=" + year + ")";
    }
}

class Car extends Vehicle {
    String type = "Car";   // shadows Vehicle.type (fields are NOT overridden)
    String model;

    Car(int year, String model) {
        super(year);       // 1. super() — call parent constructor
        System.out.println("  Car constructor: model=" + model);
        this.model = model;
    }

    void start() {
        super.start();     // 3. super.method() — call parent's start
        System.out.println("  Car engine roars");
    }

    void showTypes() {
        System.out.println("  this.type  = " + this.type);    // "Car"
        System.out.println("  super.type = " + super.type);   // 2. super.field
    }

    String info() {
        return "Car(model=" + model + ", " + super.info() + ")";
    }
}

public class SuperKeyword {
    public static void main(String[] args) {
        System.out.println("=== Constructor chaining via super() ===");
        Car car = new Car(2024, "Tesla");

        System.out.println("\n=== super.method() ===");
        car.start();

        System.out.println("\n=== super.field (field shadowing) ===");
        car.showTypes();

        System.out.println("\n=== Field resolution: reference type decides ===");
        Vehicle v = car;
        System.out.println("  v.type  = " + v.type);    // "Vehicle" (reference type)
        System.out.println("  car.type = " + car.type);  // "Car"

        System.out.println("\n=== Method resolution: actual object decides ===");
        System.out.println("  v.info()   = " + v.info());    // Car's info()
        System.out.println("  car.info() = " + car.info());  // Car's info()
    }
}
