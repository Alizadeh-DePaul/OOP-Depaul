/**
 * Static Method Hiding — No Polymorphism for Statics
 *
 * Static methods belong to the CLASS, not to objects.
 * When a subclass defines a static method with the same signature,
 * it HIDES (not overrides) the parent's version.
 * The reference type determines which static method runs —
 * NOT the actual object type. No dynamic dispatch!
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Parent {
    // Static method — resolved by REFERENCE TYPE at compile time
    static void greet() {
        System.out.println("Hello from Parent (static)");
    }

    // Instance method — resolved by ACTUAL OBJECT at runtime
    void introduce() {
        System.out.println("I am Parent (instance)");
    }
}

class Child extends Parent {
    // HIDES Parent.greet() — same signature, but NOT overriding
    static void greet() {
        System.out.println("Hello from Child (static)");
    }

    // OVERRIDES Parent.introduce() — true polymorphism
    @Override
    void introduce() {
        System.out.println("I am Child (instance)");
    }
}

class StaticMethodHiding {
    public static void main(String[] args) {
        System.out.println("=== Static vs Instance Methods ===\n");

        // Reference: Parent, Object: Child
        Parent ref = new Child();

        ref.greet();      // Parent's greet() — static, uses REFERENCE type
        ref.introduce();  // Child's introduce() — instance, uses ACTUAL type

        System.out.println("\n--- Direct calls ---");
        Parent.greet();   // Parent's greet()
        Child.greet();    // Child's greet()

        System.out.println("\n--- Child reference ---");
        Child childRef = new Child();
        childRef.greet();      // Child's greet() — reference IS Child
        childRef.introduce();  // Child's introduce()
    }
}
