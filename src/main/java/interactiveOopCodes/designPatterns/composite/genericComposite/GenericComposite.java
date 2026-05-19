package interactiveOopCodes.designPatterns.composite.genericComposite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern — Item 3: STRUCTURE (UML).
 *
 * Pure GoF skeleton with all four roles labelled.
 *
 *   Component   — declares operation() that both Leaf and Composite
 *                 implement. Optionally declares add/remove/getChild
 *                 (the Transparency option).
 *   Leaf        — implements operation() for primitive objects.
 *   Composite   — stores child Components and implements operation()
 *                 by forwarding to each child (the recursion).
 *   Client      — manipulates objects through the Component interface.
 *
 * This file also illustrates the SAFETY vs TRANSPARENCY trade-off (the
 * single most famous design decision in the Composite pattern). Two
 * variants of the Component live in the same file so they can be
 * compared head-to-head.
 */
public class GenericComposite {

    public static void main(String[] args) {
        System.out.println("--- Variant A: TRANSPARENT (add/remove on Component) ---");
        ComponentT root = new CompositeT("root");
        ComponentT g1   = new CompositeT("g1");
        ComponentT g2   = new CompositeT("g2");
        root.add(g1);
        root.add(g2);
        g1.add(new LeafT("a"));
        g1.add(new LeafT("b"));
        g2.add(new LeafT("c"));

        // Client treats EVERYTHING uniformly — even add().
        root.operation();
        System.out.println("\nClient tries to add a child to a Leaf:");
        try {
            new LeafT("orphan").add(new LeafT("nope"));    // compiles, but...
        } catch (UnsupportedOperationException ex) {
            System.out.println("  ⚠ runtime explosion: " + ex.getMessage());
        }

        System.out.println("\n--- Variant B: SAFE (add/remove on Composite only) ---");
        // Note: must reference the CONCRETE Composite type to call add(), not
        // the ComponentS interface. That is the trade-off — compile-time
        // safety in exchange for the Client knowing the concrete class.
        CompositeS sRoot = new CompositeS("sRoot");
        CompositeS sg1   = new CompositeS("sg1");
        sRoot.add(sg1);
        sg1.add(new LeafS("x"));
        sg1.add(new LeafS("y"));

        sRoot.operation();
        System.out.println("\nClient cannot even WRITE leaf.add(...) in Variant B —");
        System.out.println("  the method does not exist on ComponentS at all.");
        System.out.println("  Compile-time safety wins; uniform treatment loses.");
    }
}

/* ══════════════════════════════════════════════════════════════
   VARIANT A — TRANSPARENT
   add/remove/getChild declared on Component; Leaf throws.
   Client wins on uniformity, loses on safety.
   ══════════════════════════════════════════════════════════════ */

interface ComponentT {
    void operation();
    void add(ComponentT child);            // Leaf must throw
    void remove(ComponentT child);         // Leaf must throw
    ComponentT getChild(int i);            // Leaf must throw
}

class LeafT implements ComponentT {
    private final String name;
    LeafT(String name) { this.name = name; }

    @Override public void operation() { System.out.println("  Leaf  " + name); }

    // The price of Transparency: Leaf must give a NO answer at runtime.
    @Override public void add(ComponentT c)    { throw new UnsupportedOperationException("Leaf has no children"); }
    @Override public void remove(ComponentT c) { throw new UnsupportedOperationException("Leaf has no children"); }
    @Override public ComponentT getChild(int i){ throw new UnsupportedOperationException("Leaf has no children"); }
}

class CompositeT implements ComponentT {
    private final String name;
    private final List<ComponentT> children = new ArrayList<>();
    CompositeT(String name) { this.name = name; }

    @Override public void operation() {
        System.out.println("  Composite " + name + " forwarding to " + children.size() + " children:");
        for (ComponentT c : children) c.operation();   // the recursion
    }
    @Override public void add(ComponentT c)     { children.add(c); }
    @Override public void remove(ComponentT c)  { children.remove(c); }
    @Override public ComponentT getChild(int i) { return children.get(i); }
}

/* ══════════════════════════════════════════════════════════════
   VARIANT B — SAFE
   add/remove live ONLY on Composite; Client must downcast.
   Compiler wins on safety, Client loses on uniformity.
   ══════════════════════════════════════════════════════════════ */

interface ComponentS {
    void operation();
    // No add/remove here. A Leaf truly has no concept of children.
}

class LeafS implements ComponentS {
    private final String name;
    LeafS(String name) { this.name = name; }
    @Override public void operation() { System.out.println("  Leaf  " + name); }
}

class CompositeS implements ComponentS {
    private final String name;
    private final List<ComponentS> children = new ArrayList<>();
    CompositeS(String name) { this.name = name; }

    @Override public void operation() {
        System.out.println("  Composite " + name + " forwarding to " + children.size() + " children:");
        for (ComponentS c : children) c.operation();
    }

    // Lives ONLY here, not on Component.
    public void add(ComponentS c)    { children.add(c); }
    public void remove(ComponentS c) { children.remove(c); }
    public ComponentS getChild(int i){ return children.get(i); }
}
