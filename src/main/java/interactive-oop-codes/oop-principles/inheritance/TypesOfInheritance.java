/**
 * Types of Inheritance in Java
 *
 * Demonstrates three types Java supports through classes:
 * 1. Single Inheritance: Dog extends Animal
 * 2. Hierarchical Inheritance: Dog and Cat both extend Animal
 * 3. Multi-level Inheritance: Puppy extends Dog extends Animal
 *
 * Java does NOT support Multiple Inheritance through classes.
 * (C++ does — Java chose safety over flexibility here.)
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */

// ── Base class ───────────────────────────────────────────
class Animal {
    String name;
    Animal(String name) { this.name = name; }
    void speak() { System.out.println(name + " makes a sound"); }
}

// ── Single Inheritance: Dog extends Animal ───────────────
class Dog extends Animal {
    Dog(String name) { super(name); }
    void speak() { System.out.println(name + " barks"); }
    void fetch() { System.out.println(name + " fetches the ball"); }
}

// ── Hierarchical: Cat also extends Animal ────────────────
class Cat extends Animal {
    Cat(String name) { super(name); }
    void speak() { System.out.println(name + " meows"); }
    void purr() { System.out.println(name + " purrs"); }
}

// ── Multi-level: Puppy extends Dog extends Animal ────────
class Puppy extends Dog {
    Puppy(String name) { super(name); }
    void speak() { System.out.println(name + " yips"); }
    void play() { System.out.println(name + " plays around"); }
}

// ── Multiple Inheritance: NOT allowed in Java ────────────
// class Hybrid extends Dog, Cat { }  // COMPILE ERROR!
// Java prevents this to avoid the Diamond Problem.

public class TypesOfInheritance {
    public static void main(String[] args) {
        System.out.println("=== Single Inheritance ===");
        Dog dog = new Dog("Rex");
        dog.speak();
        dog.fetch();

        System.out.println("\n=== Hierarchical Inheritance ===");
        Cat cat = new Cat("Whiskers");
        cat.speak();
        cat.purr();

        System.out.println("\n=== Multi-level Inheritance ===");
        Puppy pup = new Puppy("Buddy");
        pup.speak();
        pup.fetch();  // inherited from Dog
        pup.play();

        System.out.println("\n=== Transitivity ===");
        Animal a = pup;  // Puppy IS-A Dog IS-A Animal
        a.speak();        // calls Puppy's speak()
    }
}
