package interactiveOopCodes.designPrinciples.solid.ocpDemo;

/**
 * OCP — Open-Closed Principle.
 *
 * "Software entities should be OPEN for extension, CLOSED for modification."
 *
 * VIOLATION: BadCalculator.calculate() uses an if/else (or switch) ladder
 * keyed on the operation name. Adding "power" or "modulo" forces an edit
 * to BadCalculator itself — risk of breaking the existing branches, and
 * every change re-tests every operation.
 *
 * SOLUTION: Define an Operation interface. Each concrete operation is its
 * own class. The Calculator depends on the abstraction, never on the
 * specific operations. Adding a new operation = adding a new class. The
 * Calculator class is never re-opened. Pure extension, zero modification.
 */
public class OcpDemo {

    public static void main(String[] args) {
        System.out.println("=== OCP — Calculator Strategy ===\n");

        // --- VIOLATION ---
        System.out.println("[VIOLATION] BadCalculator must be edited for every new op:");
        BadCalculator bad = new BadCalculator();
        System.out.println("  3 + 4 = " + bad.calculate("add",      3, 4));
        System.out.println("  9 - 5 = " + bad.calculate("subtract", 9, 5));
        // Adding "power" requires re-opening BadCalculator.calculate().

        System.out.println();

        // --- SOLUTION ---
        System.out.println("[SOLUTION] Calculator never changes; new ops are new classes:");
        Calculator good = new Calculator();
        System.out.println("  3 + 4 = " + good.execute(new Add(),      3, 4));
        System.out.println("  9 - 5 = " + good.execute(new Subtract(), 9, 5));
        System.out.println("  6 * 7 = " + good.execute(new Multiply(), 6, 7));
        System.out.println("  8 / 2 = " + good.execute(new Divide(),   8, 2));
        // To add Power: define class Power implements Operation { ... }
        //               then: calc.execute(new Power(), 2, 10);
        //               NO change to Calculator.
        System.out.println("  2 ^ 10 = " + good.execute(new Power(), 2, 10));
    }
}

/* ─────────────────────────── VIOLATION ─────────────────────────── */

class BadCalculator {
    double calculate(String op, double a, double b) {
        if (op.equals("add"))      return a + b;
        if (op.equals("subtract")) return a - b;
        if (op.equals("multiply")) return a * b;
        if (op.equals("divide"))   return a / b;
        // To add "power" or "modulo", you must MODIFY this method.
        throw new IllegalArgumentException("Unknown op: " + op);
    }
}

/* ─────────────────────────── SOLUTION ─────────────────────────── */

interface Operation {
    double apply(double a, double b);
}

class Add implements Operation {
    public double apply(double a, double b) { return a + b; }
}

class Subtract implements Operation {
    public double apply(double a, double b) { return a - b; }
}

class Multiply implements Operation {
    public double apply(double a, double b) { return a * b; }
}

class Divide implements Operation {
    public double apply(double a, double b) { return a / b; }
}

class Power implements Operation {       // <-- added later, no edit to Calculator
    public double apply(double a, double b) { return Math.pow(a, b); }
}

class Calculator {
    double execute(Operation op, double a, double b) {
        return op.apply(a, b);   // CLOSED — never changes for a new op
    }
}
