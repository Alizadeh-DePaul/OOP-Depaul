/**
 * Constructor Chaining in Inheritance
 *
 * Demonstrates:
 * - Constructors are NOT inherited — each class defines its own
 * - Parent constructor always runs before child constructor
 * - super() must be the first statement (or Java inserts super() implicitly)
 * - You cannot have both this() and super() in the same constructor
 * - Constructor chaining follows: top of hierarchy → bottom
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Grandparent {
    Grandparent() {
        System.out.println("1. Grandparent()");
    }
    Grandparent(String msg) {
        System.out.println("1. Grandparent(\"" + msg + "\")");
    }
}

class ParentClass extends Grandparent {
    ParentClass() {
        // implicit super() → Grandparent()
        System.out.println("2. ParentClass()");
    }
    ParentClass(String msg) {
        super(msg);          // explicit super(msg) → Grandparent(msg)
        System.out.println("2. ParentClass(\"" + msg + "\")");
    }
}

class ChildClass extends ParentClass {
    ChildClass() {
        this("default");     // this() calls sibling constructor
        // super();           // ERROR: can't have both this() and super()
        System.out.println("3. ChildClass()");
    }
    ChildClass(String msg) {
        super(msg);          // super() calls parent constructor
        System.out.println("3. ChildClass(\"" + msg + "\")");
    }
}

public class ConstructorChaining {
    public static void main(String[] args) {
        System.out.println("=== No-arg constructor ===");
        System.out.println("Calling: new ChildClass()\n");
        new ChildClass();

        System.out.println("\n=== Parameterized constructor ===");
        System.out.println("Calling: new ChildClass(\"hello\")\n");
        new ChildClass("hello");

        System.out.println("\n=== Key rules ===");
        System.out.println("• Parent constructor ALWAYS runs before child");
        System.out.println("• Cannot combine this() and super() in one constructor");
        System.out.println("• If you omit super(), Java inserts super() automatically");
        System.out.println("• Always maintain proper constructor chaining!");
    }
}
