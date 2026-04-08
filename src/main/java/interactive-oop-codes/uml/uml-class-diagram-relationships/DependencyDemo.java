/**
 * UML Dependency Relationship Demo
 *
 * Dependency is the weakest relationship in UML.
 * It represents a temporary "uses-a" connection:
 * Class A depends on Class B if A temporarily uses B
 * (as a parameter, local variable, or return type).
 *
 * UML Notation: dashed line with open arrowhead (- - - ->)
 * Direction:    Driver -------> Car  (Driver depends on Car)
 */

// ── The depended-upon class ──────────────────────────────
class Car {
    private String model;

    public Car(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void start() {
        System.out.println(model + " engine started.");
    }

    public void stop() {
        System.out.println(model + " engine stopped.");
    }
}

// ── The dependent class ──────────────────────────────────
// Driver DEPENDS ON Car — it uses Car temporarily in its methods.
// Driver does NOT store a Car as a field (that would be association).
class Driver {
    private String name;

    public Driver(String name) {
        this.name = name;
    }

    // Dependency via METHOD PARAMETER
    // Driver temporarily uses a Car object passed to this method.
    public void drive(Car car) {
        System.out.println(name + " is driving " + car.getModel());
        car.start();
    }

    // Dependency via LOCAL VARIABLE
    // Driver creates and uses a Car only within this method scope.
    public void testDrive() {
        Car rental = new Car("Toyota Camry");   // local variable
        System.out.println(name + " is test-driving " + rental.getModel());
        rental.start();
        rental.stop();
        // rental goes out of scope — no lasting relationship
    }

    // Dependency via RETURN TYPE
    // The method returns a Car object, creating a dependency.
    public Car rentCar(String model) {
        System.out.println(name + " rented a " + model);
        return new Car(model);
    }
}

// ── Main ─────────────────────────────────────────────────
public class DependencyDemo {
    public static void main(String[] args) {
        Driver alice = new Driver("Alice");
        Car honda = new Car("Honda Civic");

        System.out.println("=== Dependency via Parameter ===");
        alice.drive(honda);

        System.out.println("\n=== Dependency via Local Variable ===");
        alice.testDrive();

        System.out.println("\n=== Dependency via Return Type ===");
        Car rented = alice.rentCar("Ford Mustang");
        rented.start();
    }
}
