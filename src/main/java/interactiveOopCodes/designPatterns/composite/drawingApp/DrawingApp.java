package interactiveOopCodes.designPatterns.composite.drawingApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern — Item 1: INTENT.
 *
 * "Compose objects into tree structures to represent part-whole hierarchies.
 *  Composite lets clients treat individual objects and compositions of
 *  objects uniformly."
 *      — Gamma, Helm, Johnson, Vlissides (1994)
 *
 * In this demo:
 *   - Shape           is the Component (common interface for everyone)
 *   - Triangle/Circle are Leaf nodes (no children)
 *   - Drawing         is the Composite (holds Shapes — which may themselves
 *                     be Drawings; the recursion is the whole point)
 *
 * The Client never asks "is this a Triangle or a Drawing?". It just calls
 * draw(color). One polymorphic call replaces an unbounded forest of
 * instanceof checks.
 */
public class DrawingApp {

    public static void main(String[] args) {
        // --- Build a leaf-only Drawing ---
        Shape tri1 = new Triangle();
        Shape tri2 = new Triangle();
        Shape cir  = new Circle();

        Drawing flat = new Drawing("Flat drawing");
        flat.add(tri1);
        flat.add(tri2);
        flat.add(cir);

        // Uniform treatment: the same call works on a leaf...
        System.out.println("--- Calling draw() on a leaf ---");
        cir.draw("red");

        // ...and on a composite. The Client cannot tell the difference.
        System.out.println("\n--- Calling draw() on a composite ---");
        flat.draw("blue");

        // --- Build a NESTED tree: a Drawing inside another Drawing ---
        Drawing inner = new Drawing("Inner sub-drawing");
        inner.add(new Triangle());
        inner.add(new Circle());

        Drawing outer = new Drawing("Outer drawing");
        outer.add(new Circle());     // a leaf
        outer.add(inner);            // a composite child!
        outer.add(new Triangle());   // another leaf

        System.out.println("\n--- Calling draw() on a NESTED composite ---");
        // One call. The recursion does the rest.
        outer.draw("green");
    }
}

/* ───────────────────────── Component ───────────────────────── */

/** The Component — common interface for leaves and composites. */
interface Shape {
    void draw(String fillColor);
}

/* ───────────────────────── Leaves ───────────────────────── */

/** A Leaf — knows how to draw itself; cannot contain other shapes. */
class Triangle implements Shape {
    @Override
    public void draw(String fillColor) {
        System.out.println("  Drawing Triangle with color " + fillColor);
    }
}

/** A Leaf — knows how to draw itself; cannot contain other shapes. */
class Circle implements Shape {
    @Override
    public void draw(String fillColor) {
        System.out.println("  Drawing Circle with color " + fillColor);
    }
}

/* ───────────────────────── Composite ───────────────────────── */

/**
 * The Composite — IS A Shape (so the Client treats it uniformly)
 *                 HAS A list of Shapes (which may themselves be Composites).
 *
 * draw() is the recursive operation: forward to every child. The base case
 * (the recursion-stopper) is implicit — leaves do not recurse, they just
 * draw themselves.
 */
class Drawing implements Shape {

    private final String name;
    private final List<Shape> children = new ArrayList<>();

    public Drawing(String name) {
        this.name = name;
    }

    public void add(Shape s) {
        children.add(s);
    }

    public void remove(Shape s) {
        children.remove(s);
    }

    @Override
    public void draw(String fillColor) {
        System.out.println("[" + name + "] expanding " + children.size()
                + " child shape(s) in " + fillColor + ":");
        // The recursion. ONE polymorphic call. The Component interface is
        // doing all the heavy lifting here — every child is a Shape, so we
        // do not care whether it is a Triangle, a Circle, or another Drawing.
        for (Shape child : children) {
            child.draw(fillColor);
        }
    }
}
