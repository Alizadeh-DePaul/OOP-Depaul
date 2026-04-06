/**
 * Compile-Time Polymorphism — Method Overloading
 *
 * The compiler selects the correct method at compile time
 * based on the number, type, and order of arguments.
 * All methods share the same name but different signatures.
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Calculator {
    // Overload 1: two integers
    int add(int a, int b) {
        return a + b;
    }

    // Overload 2: three integers — different number of parameters
    int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overload 3: two doubles — different parameter types
    double add(double a, double b) {
        return a + b;
    }

    // Overload 4: String concatenation — completely different types
    String add(String a, String b) {
        return a + " " + b;
    }

    // Overload 5: different parameter order matters
    String describe(String label, int value) {
        return label + " = " + value;
    }

    String describe(int value, String label) {
        return value + " -> " + label;
    }
}

class MethodOverloading {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        System.out.println("=== Compile-Time Polymorphism ===");
        System.out.println("add(10, 20)          = " + calc.add(10, 20));
        System.out.println("add(10, 20, 30)      = " + calc.add(10, 20, 30));
        System.out.println("add(3.14, 2.86)      = " + calc.add(3.14, 2.86));
        System.out.println("add(\"Hello\", \"World\") = " + calc.add("Hello", "World"));
        System.out.println("describe(\"Score\", 95) = " + calc.describe("Score", 95));
        System.out.println("describe(95, \"Score\") = " + calc.describe(95, "Score"));
    }
}
