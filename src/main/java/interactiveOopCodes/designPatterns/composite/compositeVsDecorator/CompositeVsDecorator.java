package interactiveOopCodes.designPatterns.composite.compositeVsDecorator;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern — Item 6: PROS &amp; CONS / Composite vs Decorator.
 *
 * Composite and Decorator are *sister* structural patterns. Both wrap a
 * Component. Both make the wrapper IS-A Component so the Client treats
 * everything uniformly. But they answer different questions:
 *
 *   COMPOSITE  — "How do I treat a tree of objects as a single object?"
 *                Multi-child. Aggregates behavior across children.
 *                e.g. ItemBundle holds many GroceryItems → totalPrice is
 *                the sum.
 *
 *   DECORATOR  — "How do I add behavior to ONE object at runtime, without
 *                subclassing?"
 *                Single-child (or single-chain). Modifies behavior of
 *                its one wrapped Component.
 *                e.g. DiscountDecorator wraps ONE GroceryItem and
 *                returns price * 0.85.
 *
 * Same Component interface (GroceryItem). Same wrapping shape. Wildly
 * different intent. This file shows both side by side so the contrast
 * is concrete.
 */
public class CompositeVsDecorator {

    public static void main(String[] args) {

        // --- Plain leaves ---
        GroceryItem milk    = new SimpleItem("Milk",   3.50);
        GroceryItem bread   = new SimpleItem("Bread",  2.00);
        GroceryItem cheese  = new SimpleItem("Cheese", 6.00);
        GroceryItem coffee  = new SimpleItem("Coffee", 12.00);

        System.out.println("--- Just a leaf ---");
        milk.print(0);

        // --- COMPOSITE: a bundle aggregates many items ---
        ItemBundle breakfastBundle = new ItemBundle("Breakfast bundle");
        breakfastBundle.add(milk);
        breakfastBundle.add(bread);
        breakfastBundle.add(cheese);

        System.out.println("\n--- COMPOSITE: aggregates many children ---");
        breakfastBundle.print(0);

        // --- DECORATOR: a discount wraps ONE item ---
        GroceryItem discountedCoffee = new DiscountDecorator(coffee, 0.15);

        System.out.println("\n--- DECORATOR: modifies ONE wrapped child ---");
        discountedCoffee.print(0);

        // --- The patterns COMPOSE — a Decorator can wrap a Composite ---
        GroceryItem discountedBundle = new DiscountDecorator(breakfastBundle, 0.10);

        System.out.println("\n--- Decorator wrapping a Composite ---");
        discountedBundle.print(0);
        // Notice: still ONE polymorphic call. The Client never sees the
        // structural difference between a decorator, a composite, or a leaf.
    }
}

/* ───────────────────────── Component ───────────────────────── */

/** Common Component for BOTH patterns. */
interface GroceryItem {
    String name();
    double price();
    void   print(int indent);
}

/* ───────────────────────── Leaf ───────────────────────── */

class SimpleItem implements GroceryItem {
    private final String name;
    private final double price;

    SimpleItem(String name, double price) {
        this.name  = name;
        this.price = price;
    }

    @Override public String name()  { return name; }
    @Override public double price() { return price; }
    @Override public void print(int indent) {
        System.out.printf("%s● %-22s $%,.2f%n", "  ".repeat(indent), name, price);
    }
}

/* ───────────────────────── Composite (multi-child) ───────────────────────── */

/**
 * A bundle of grocery items. price() is the recursive roll-up — sum of
 * all child prices. Many children, behavior AGGREGATED.
 */
class ItemBundle implements GroceryItem {

    private final String label;
    private final List<GroceryItem> children = new ArrayList<>();

    ItemBundle(String label) { this.label = label; }

    public void add(GroceryItem g)    { children.add(g); }
    public void remove(GroceryItem g) { children.remove(g); }

    @Override public String name()  { return label; }

    @Override public double price() {
        double total = 0;
        for (GroceryItem g : children) total += g.price();   // recursion
        return total;
    }

    @Override public void print(int indent) {
        System.out.printf("%s▼ %s (bundle, $%,.2f)%n",
                "  ".repeat(indent), label, price());
        for (GroceryItem g : children) g.print(indent + 1);
    }
}

/* ───────────────────────── Decorator (single-child) ───────────────────────── */

/**
 * Wraps exactly ONE GroceryItem and modifies its behavior — applies a
 * discount fraction to whatever its delegate's price is. One child,
 * behavior MODIFIED.
 *
 * Note the structural symmetry with Composite: both implement
 * GroceryItem, both hold a GroceryItem reference. The number of children
 * (1 vs many) and the intent (modify vs aggregate) are what differ.
 */
class DiscountDecorator implements GroceryItem {

    private final GroceryItem wrapped;
    private final double discountFraction;       // 0.15 means 15% off

    DiscountDecorator(GroceryItem wrapped, double discountFraction) {
        this.wrapped          = wrapped;
        this.discountFraction = discountFraction;
    }

    @Override public String name() {
        return "Discounted " + wrapped.name() + " (-" + (int)(discountFraction * 100) + "%)";
    }

    /** Decorator's "added behavior": multiply the wrapped price. */
    @Override public double price() {
        return wrapped.price() * (1.0 - discountFraction);
    }

    @Override public void print(int indent) {
        System.out.printf("%s◇ %s  →  $%,.2f%n", "  ".repeat(indent), name(), price());
        wrapped.print(indent + 1);                // shows the chain
    }
}
