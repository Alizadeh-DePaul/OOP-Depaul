package interactiveOopCodes.oopPrinciples.abstraction.defaultMethodsDemo;

// Default Methods (Java 8+) and the Diamond Problem
// Interfaces can now have method bodies using 'default' keyword

interface Logger {
    void log(String msg);    // Abstract — must implement

    // Default method — has a body, can be overridden
    default void logInfo(String msg) {
        System.out.println("[INFO] " + msg);
    }

    default void logError(String msg) {
        System.out.println("[ERROR] " + msg);
    }
}

class ConsoleLogger implements Logger {
    @Override
    public void log(String msg) {
        System.out.println("LOG: " + msg);
    }

    // Override the default method
    @Override
    public void logError(String msg) {
        System.out.println("*** CRITICAL ERROR: " + msg + " ***");
    }
    // logInfo() is inherited as-is from Logger
}

// --- Diamond Problem ---
interface InterfaceA {
    void show();
    default void greet() {
        System.out.println("Hello from InterfaceA!");
    }
}

interface InterfaceB {
    void show();
    default void greet() {
        System.out.println("Hello from InterfaceB!");
    }
    default void farewell() {
        System.out.println("Goodbye from InterfaceB!");
    }
}

// MUST override greet() — diamond conflict!
class MyClass implements InterfaceA, InterfaceB {
    @Override
    public void show() {
        System.out.println("MyClass show()");
    }

    // Resolving the diamond: must override conflicting default method
    @Override
    public void greet() {
        System.out.println("MyClass resolves the conflict:");
        // Call specific interface defaults using InterfaceName.super
        InterfaceA.super.greet();
        InterfaceB.super.greet();
    }
    // farewell() — no conflict, inherited from InterfaceB
}

public class DefaultMethodsDemo {
    public static void main(String[] args) {
        System.out.println("=== Default Methods (Java 8+) ===\n");

        ConsoleLogger logger = new ConsoleLogger();
        logger.log("Application started");
        logger.logInfo("Using default method");
        logger.logError("Something went wrong");

        System.out.println("\n--- Diamond Problem Resolution ---");
        MyClass obj = new MyClass();
        obj.show();
        obj.greet();       // Resolves diamond
        obj.farewell();    // No conflict — from InterfaceB

        System.out.println("\n--- Through Interface References ---");
        InterfaceA refA = new MyClass();
        refA.greet();

        InterfaceB refB = new MyClass();
        refB.greet();
        refB.farewell();
    }
}
