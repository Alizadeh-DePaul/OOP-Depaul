/**
 * Method Signature Rules — What Makes a Valid Overload?
 *
 * A method signature = method name + parameter list (number, type, order).
 * Return type is NOT part of the signature.
 * Two methods with the same signature but different return types
 * cause a compilation error — the compiler cannot distinguish them.
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Printer {
    // --- VALID overloads: different parameter types ---
    void print(int value) {
        System.out.println("int: " + value);
    }

    void print(double value) {
        System.out.println("double: " + value);
    }

    void print(String value) {
        System.out.println("String: " + value);
    }

    // --- VALID overload: different number of parameters ---
    void print(String label, int value) {
        System.out.println(label + ": " + value);
    }

    // --- INVALID: same signature, different return type ---
    // Uncommenting this causes a compilation error:
    // "print(int) is already defined"
    //
    // int print(int value) {
    //     return value;
    // }

    // --- Auto-widening: int -> double happens automatically ---
    // If no exact match exists, Java widens the argument type.
    // print(42) calls print(int), not print(double).
    // But print(42.0) calls print(double).
}

class MethodSignatureRules {
    public static void main(String[] args) {
        Printer p = new Printer();

        System.out.println("=== Method Signature Rules ===");

        p.print(42);           // Exact match: print(int)
        p.print(3.14);         // Exact match: print(double)
        p.print("hello");      // Exact match: print(String)
        p.print("Score", 100); // Exact match: print(String, int)

        System.out.println("\n--- Auto-widening ---");
        // byte -> short -> int -> long -> float -> double
        byte b = 10;
        p.print(b);   // Widened: byte -> int, calls print(int)
        short s = 20;
        p.print(s);   // Widened: short -> int, calls print(int)
        float f = 1.5f;
        p.print(f);   // Widened: float -> double, calls print(double)
    }
}
