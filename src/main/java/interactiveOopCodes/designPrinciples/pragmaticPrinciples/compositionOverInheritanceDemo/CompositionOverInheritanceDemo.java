package interactiveOopCodes.designPrinciples.pragmaticPrinciples.compositionOverInheritanceDemo;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Composition over Inheritance — favor HAS-A over IS-A.
 *
 * Bad : BadStack extends ArrayList. It inherits add(int, e), remove(int),
 *       set(...), subList(...) — methods that violate stack semantics.
 * Good: GoodStack HAS-A Deque. It exposes ONLY push / pop / peek.
 *
 * Inheritance is a fragile promise: every superclass change ripples down.
 * Composition lets you choose what to expose; inheritance forces you to
 * inherit everything.
 *
 * Real history: java.util.Stack extends Vector in the JDK and has been
 * regretted ever since (Bloch, Effective Java, Item 18).
 */
public class CompositionOverInheritanceDemo {

    public static void main(String[] args) {
        System.out.println("=== Bad: stack-by-inheritance ===");
        BadStack bs = new BadStack();
        bs.push(1); bs.push(2); bs.push(3);
        bs.add(0, 999);                    // OOPS — inherited, but illegal for a stack
        System.out.println("  poisoned bottom: " + bs);
        System.out.println("  pop()           : " + bs.pop());

        System.out.println("\n=== Good: stack-by-composition ===");
        GoodStack gs = new GoodStack();
        gs.push(1); gs.push(2); gs.push(3);
        // gs.add(0, 999);  // won't compile — no such method exposed
        System.out.println("  peek()          : " + gs.peek());
        System.out.println("  pop()           : " + gs.pop());
    }
}

class BadStack extends ArrayList<Integer> {                  // IS-A ArrayList — wrong
    void push(int v) { add(v); }
    Integer pop()    { return remove(size() - 1); }
    Integer peek()   { return get(size() - 1); }
    // also inherits: add(int, e), remove(int), set(...), subList(...), clear()
}

class GoodStack {                                            // HAS-A Deque
    private final Deque<Integer> data = new ArrayDeque<>();
    void push(int v) { data.push(v); }
    Integer pop()    { return data.pop(); }
    Integer peek()   { return data.peek(); }
}
