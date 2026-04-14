package interactiveOopCodes.oopPrinciples.oopPrinciplesReview.polymorphismReview;

// Polymorphism Review — Overloading vs Overriding, compile-time vs runtime
// The KEY distinction: overloading = compile-time, overriding = runtime

class Animal {
    public String speak() { return "..."; }

    // OVERLOADING — same name, different parameters (compile-time)
    public void eat(String food) {
        System.out.println(getClass().getSimpleName() + " eats " + food);
    }

    public void eat(String food, int amount) {
        System.out.println(getClass().getSimpleName() + " eats " + amount + " " + food);
    }
}

class Dog extends Animal {
    // OVERRIDING — same signature, different implementation (runtime)
    @Override
    public String speak() { return "Woof!"; }
}

class Cat extends Animal {
    @Override
    public String speak() { return "Meow!"; }
}

public class PolymorphismReview {
    // Polymorphic method — works with ANY Animal
    static void makeSpeak(Animal a) {
        System.out.println(a.getClass().getSimpleName() + " says: " + a.speak());
    }

    public static void main(String[] args) {
        System.out.println("=== Runtime Polymorphism (Overriding) ===");
        Animal[] animals = { new Dog(), new Cat(), new Animal() };
        for (Animal a : animals) {
            makeSpeak(a);  // Dynamic dispatch — correct speak() called at runtime
        }

        System.out.println("\n=== Compile-time Polymorphism (Overloading) ===");
        Dog d = new Dog();
        d.eat("bones");          // Calls eat(String)
        d.eat("treats", 3);     // Calls eat(String, int)

        System.out.println("\n=== Compile-time vs Runtime Type ===");
        Animal a = new Dog();   // Compile-time: Animal, Runtime: Dog
        System.out.println("Declared type: Animal");
        System.out.println("Actual type:   " + a.getClass().getSimpleName());
        System.out.println("speak() returns: " + a.speak()); // Runtime type wins

        System.out.println("\n=== Overloading Resolved at Compile Time ===");
        Object obj = "hello";
        printType(obj);  // Prints "Object" — NOT "String"!
    }

    // Overloaded methods — resolved by DECLARED type, not runtime type
    static void printType(Object o)  { System.out.println("Object"); }
    static void printType(String s)  { System.out.println("String"); }
}
