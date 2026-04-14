package interactiveOopCodes.oopPrinciples.abstraction.multipleInheritanceDemo;

// Multiple Inheritance using Interfaces
// Java: single class inheritance, multiple interface inheritance

interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

interface Walkable {
    void walk();
}

// Implement multiple interfaces (comma-separated)
class Duck implements Flyable, Swimmable, Walkable {
    @Override
    public void fly() {
        System.out.println("Duck is flying!");
    }

    @Override
    public void swim() {
        System.out.println("Duck is swimming!");
    }

    @Override
    public void walk() {
        System.out.println("Duck is walking!");
    }
}

// What if two interfaces have the SAME method name?
interface Sensor {
    void activate();
}

interface Alarm {
    void activate();    // Same name as Sensor.activate()
}

// One implementation satisfies BOTH interfaces
class SecuritySystem implements Sensor, Alarm {
    @Override
    public void activate() {
        System.out.println("SecuritySystem activated! (satisfies both Sensor and Alarm)");
    }
}

// Interface can extend multiple interfaces
interface Amphibious extends Flyable, Swimmable {
    void dive();   // Adds its own method
}

class FlyingFish implements Amphibious {
    @Override
    public void fly() {
        System.out.println("FlyingFish gliding above water!");
    }

    @Override
    public void swim() {
        System.out.println("FlyingFish swimming below surface!");
    }

    @Override
    public void dive() {
        System.out.println("FlyingFish diving deep!");
    }
}

public class MultipleInheritanceDemo {
    public static void main(String[] args) {
        System.out.println("=== Multiple Inheritance via Interfaces ===\n");

        Duck duck = new Duck();
        duck.fly();
        duck.swim();
        duck.walk();

        System.out.println("\n--- Same Method Name in Multiple Interfaces ---");
        SecuritySystem sec = new SecuritySystem();
        sec.activate();

        Sensor s = sec;     // Upcast to Sensor
        s.activate();       // Same implementation

        Alarm a = sec;      // Upcast to Alarm
        a.activate();       // Same implementation

        System.out.println("\n--- Interface Extending Multiple Interfaces ---");
        FlyingFish fish = new FlyingFish();
        fish.fly();
        fish.swim();
        fish.dive();

        Amphibious amp = fish;
        amp.fly();
        amp.dive();
    }
}
