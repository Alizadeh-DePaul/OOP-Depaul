/**
 * The Diamond Problem
 *
 * Why Java does not support multiple inheritance through classes:
 * - Parent has show()
 * - Child1 and Child2 both extend Parent and override show()
 * - If GrandChild could extend BOTH Child1 and Child2,
 *   which show() would it inherit? → Ambiguity!
 *
 * Java's solution: only single class inheritance.
 * Use interfaces for multiple inheritance of type/behavior.
 *
 * Note: C++ supports multiple class inheritance but leaves
 * the resolution to the programmer (virtual inheritance).
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Parent {
    public void show() {
        System.out.println("I am in Parent");
    }
}

class Child1 extends Parent {
    public void show() {
        System.out.println("I am in Child1");
    }
}

class Child2 extends Parent {
    public void show() {
        System.out.println("I am in Child2");
    }
}

// ILLEGAL in Java — would create ambiguity:
// class GrandChild extends Child1, Child2 { }
//
// If this were allowed:
//   GrandChild gc = new GrandChild();
//   gc.show();  // Child1's show()? Child2's show()? ← AMBIGUOUS!

// What Java DOES allow: single inheritance + interfaces
interface Printable {
    default void print() {
        System.out.println("Printable default");
    }
}

interface Loggable {
    default void print() {
        System.out.println("Loggable default");
    }
}

// When interfaces conflict, the class MUST override to resolve:
class Document implements Printable, Loggable {
    public void print() {
        Printable.super.print();  // explicitly choose
        System.out.println("Document resolved the conflict");
    }
}

public class DiamondProblem {
    public static void main(String[] args) {
        System.out.println("=== The Diamond Problem ===");
        Child1 c1 = new Child1();
        Child2 c2 = new Child2();
        c1.show();
        c2.show();

        System.out.println("\n=== Interface conflict resolution ===");
        Document doc = new Document();
        doc.print();
    }
}
