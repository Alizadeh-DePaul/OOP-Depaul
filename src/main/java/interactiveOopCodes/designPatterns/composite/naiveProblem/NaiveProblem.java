package interactiveOopCodes.designPatterns.composite.naiveProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Composite Pattern — Item 2: PROBLEM.
 *
 * What happens if you DON'T use Composite? The client code becomes a
 * forest of `instanceof` checks and explicit downcasts that:
 *   (1) grows linearly every time you add a new shape type,
 *   (2) breaks the Open/Closed Principle (modify the client to extend),
 *   (3) has to manage recursion by hand for nested containers,
 *   (4) couples the client to every concrete leaf and container type.
 *
 * The fix in NaiveProblemFixed is to give all nodes the same Component
 * interface and let polymorphism dispatch the right behavior — that is
 * the entire Composite pattern in one line of difference.
 */
public class NaiveProblem {

    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("  WITHOUT Composite — instanceof spaghetti");
        System.out.println("================================================");

        // A "drawing" is just a heterogeneous List<Object> here. No shared
        // type. The client has to figure out what each element is.
        List<Object> flat = Arrays.asList(
                new NaiveTriangle(),
                new NaiveCircle(),
                new NaiveSquare()         // imagine we just added Square
        );
        NaiveClient.drawAll(flat, "red");

        // Add a nested "group". The client now has to handle recursion
        // manually, complete with another instanceof branch.
        List<Object> nested = new ArrayList<>();
        nested.add(new NaiveTriangle());
        nested.add(flat);                // a sub-list (group)!
        nested.add(new NaiveCircle());
        NaiveClient.drawAll(nested, "green");

        System.out.println();
        System.out.println("================================================");
        System.out.println("  WITH Composite — uniform polymorphic call");
        System.out.println("================================================");
        // See: drawingApp/DrawingApp.java — same scenario, ONE polymorphic
        // call replaces every instanceof branch above.
        System.out.println("    drawing.draw(\"red\");   // that is the entire client");
    }
}

/* ──────────── Naive shapes (no shared interface) ──────────── */
class NaiveTriangle { void renderTri(String c) { System.out.println("  Triangle " + c); } }
class NaiveCircle   { void renderCir(String c) { System.out.println("  Circle "   + c); } }
class NaiveSquare   { void renderSq (String c) { System.out.println("  Square "   + c); } }

/* ──────────── The instanceof-driven Client ──────────── */
class NaiveClient {

    /**
     * Without Composite, the client must dispatch by type itself.
     * Note the explosion of `instanceof`:
     *   - one branch per leaf type
     *   - one branch for the recursive sub-list
     *   - a fallback for the "I don't know what this is" case
     *
     * Add a Hexagon next semester? You will be editing this method.
     */
    @SuppressWarnings("unchecked")
    public static void drawAll(List<Object> things, String color) {
        for (Object o : things) {
            if (o instanceof NaiveTriangle t) {
                t.renderTri(color);
            } else if (o instanceof NaiveCircle c) {
                c.renderCir(color);
            } else if (o instanceof NaiveSquare s) {
                s.renderSq(color);
            } else if (o instanceof List) {
                // Manual recursion. The client now KNOWS the tree structure.
                drawAll((List<Object>) o, color);
            } else {
                // The fallback nobody likes.
                throw new IllegalArgumentException("Unknown shape: " + o);
            }
        }
    }
}
