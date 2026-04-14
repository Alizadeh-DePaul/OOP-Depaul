package interactiveOopCodes.oopPrinciples.polymorphism.finalKeywordDemo;

/**
 * The final Keyword — Locking Down Inheritance and Mutation
 *
 * final has three uses in Java:
 * 1. final class    — cannot be extended (no subclasses)
 * 2. final method   — cannot be overridden by subclasses
 * 3. final variable — cannot be reassigned after initialization
 *
 * Constructors CANNOT be final because they are not inherited,
 * so there is nothing to "lock."
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */

// --- final variable ---
class Constants {
    final int MAX_SIZE = 100;          // compile-time constant
    final String name;                  // blank final — set in constructor

    Constants(String name) {
        this.name = name;              // must be assigned exactly once
    }

    void tryChange() {
        // MAX_SIZE = 200;             // ERROR: cannot assign to final variable
        // name = "other";             // ERROR: cannot assign to final variable
        System.out.println("MAX_SIZE=" + MAX_SIZE + ", name=" + name);
    }
}

// --- final method ---
class Formatter {
    // This method cannot be overridden
    final String formatHeader(String title) {
        return "=== " + title + " ===";
    }

    // This method CAN be overridden
    String formatBody(String content) {
        return content;
    }
}

class FancyFormatter extends Formatter {
    // @Override
    // String formatHeader(String title) { ... }  // ERROR: cannot override final method

    @Override
    String formatBody(String content) {
        return ">> " + content + " <<";  // OK — not final
    }
}

// --- final class ---
final class ImmutablePoint {
    final int x;
    final int y;

    ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point(" + x + ", " + y + ")";
    }
}

// class ExtendedPoint extends ImmutablePoint { }  // ERROR: cannot extend final class

class FinalKeywordDemo {
    public static void main(String[] args) {
        System.out.println("=== The final Keyword ===\n");

        // final variable
        System.out.println("--- final variable ---");
        Constants c = new Constants("DePaul");
        c.tryChange();

        // final method
        System.out.println("\n--- final method ---");
        FancyFormatter ff = new FancyFormatter();
        System.out.println(ff.formatHeader("Title"));  // Inherited final method
        System.out.println(ff.formatBody("Content"));   // Overridden method

        // final class
        System.out.println("\n--- final class ---");
        ImmutablePoint p = new ImmutablePoint(3, 7);
        System.out.println(p);
        System.out.println("ImmutablePoint cannot be subclassed.");

        // final parameter
        System.out.println("\n--- final parameter ---");
        printFinal("immutable argument");
    }

    // final parameter — cannot be reassigned inside the method
    static void printFinal(final String msg) {
        // msg = "changed";  // ERROR: final parameter
        System.out.println("Final param: " + msg);
    }
}
