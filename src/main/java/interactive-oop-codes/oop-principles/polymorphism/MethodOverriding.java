/**
 * Runtime Polymorphism — Method Overriding
 *
 * When a subclass provides its own implementation of a method
 * already defined in the parent class, the JVM decides which
 * version to call at runtime based on the actual object type
 * (not the reference type). This is dynamic method dispatch.
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Animal {
    // Overridden method (will be replaced by subclass)
    void speak() {
        System.out.println("Animal makes a sound");
    }

    // Not overridden — inherited as-is
    void breathe() {
        System.out.println("Animal breathes");
    }
}

class Dog extends Animal {
    // Overriding method — replaces parent's speak()
    @Override
    void speak() {
        System.out.println("Dog barks: Woof!");
    }
}

class Cat extends Animal {
    // Overriding method — each subclass has its own version
    @Override
    void speak() {
        System.out.println("Cat meows: Meow!");
    }
}

class MethodOverriding {
    public static void main(String[] args) {
        System.out.println("=== Runtime Polymorphism ===\n");

        // Reference type: Animal, Actual object: Dog
        Animal a1 = new Dog();
        a1.speak();    // Dog barks: Woof!  (resolved at RUNTIME)
        a1.breathe();  // Animal breathes   (inherited, not overridden)

        // Reference type: Animal, Actual object: Cat
        Animal a2 = new Cat();
        a2.speak();    // Cat meows: Meow!  (resolved at RUNTIME)

        // Reference type: Animal, Actual object: Animal
        Animal a3 = new Animal();
        a3.speak();    // Animal makes a sound

        System.out.println("\n--- Polymorphic Array ---");
        Animal[] animals = { new Dog(), new Cat(), new Animal() };
        for (Animal a : animals) {
            a.speak();  // Each call resolved at runtime!
        }
    }
}
