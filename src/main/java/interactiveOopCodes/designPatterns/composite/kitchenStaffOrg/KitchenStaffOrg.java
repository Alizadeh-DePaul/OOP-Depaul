package interactiveOopCodes.designPatterns.composite.kitchenStaffOrg;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern — Item 4: IMPLEMENTATION.
 *
 * A restaurant kitchen organization chart. Every staff member is a
 * KitchenStaff (the Component). A Chef can have subordinates, so Chef is
 * the Composite. A simple ranked staff member (a Cook) is a Leaf.
 *
 * The whole point of this demo is to show the *recursive* nature of
 * Composite operations. The tree is 5 levels deep:
 *
 *     Executive
 *       └─ Head
 *           ├─ Sous 1
 *           │    ├─ Line 1
 *           │    │    ├─ Commis 1   (Leaf)
 *           │    │    └─ Commis 2   (Leaf)
 *           │    ├─ Line 2          (Leaf)
 *           │    └─ Line 3          (Leaf)
 *           └─ Sous 2               (Leaf)
 *
 *   printChart()  — recursive pre-order traversal that indents by depth.
 *   headcount()   — recursive post-order roll-up: sum self + children.
 *   payroll()     — recursive post-order roll-up: sum salaries.
 *
 * Notice that the Client only ever calls these methods on the root. The
 * tree's recursive nature is encapsulated inside the Composite — exactly
 * what "uniform treatment of individual objects and compositions" means.
 */
public class KitchenStaffOrg {

    public static void main(String[] args) {
        // Leaves (cooks with no subordinates)
        Cook commis1 = new Cook("Chef 9",  "Commis Chef", 35_000);
        Cook commis2 = new Cook("Chef 10", "Commis Chef", 35_000);
        Cook line2   = new Cook("Chef 6",  "Line Chef",   55_000);
        Cook line3   = new Cook("Chef 7",  "Line Chef",   55_000);
        Cook sous2   = new Cook("Chef 4",  "Sous Chef",   75_000);

        // Composites
        Chef line1 = new Chef("Chef 5", "Line Chef", 55_000);
        line1.add(commis1);
        line1.add(commis2);

        Chef sous1 = new Chef("Chef 3", "Sous Chef", 75_000);
        sous1.add(line1);
        sous1.add(line2);
        sous1.add(line3);

        Chef head = new Chef("Chef 2", "Head Chef", 95_000);
        head.add(sous1);
        head.add(sous2);

        Chef exec = new Chef("Chef 1", "Executive Chef", 125_000);
        exec.add(head);

        // Uniform Client. ONE call to each method — the recursion is hidden.
        System.out.println("=== Kitchen org chart (recursive pre-order) ===");
        exec.printChart(0);

        System.out.println("\n=== Headcount (recursive post-order roll-up) ===");
        System.out.println("Total kitchen headcount: " + exec.headcount());

        System.out.println("\n=== Annual payroll (recursive post-order roll-up) ===");
        System.out.printf ("Total annual payroll: $%,d%n", exec.payroll());

        // The uniform-treatment trick: the SAME method calls work on any
        // node — even on a Leaf at the bottom of the tree.
        System.out.println("\n=== Calling the SAME methods on a Leaf ===");
        commis1.printChart(0);
        System.out.println("Headcount under commis1: " + commis1.headcount());
        System.out.printf ("Payroll under commis1:   $%,d%n",   commis1.payroll());
    }
}

/* ───────────────────────── Component ───────────────────────── */

/** Component — every staff member must answer these three operations. */
interface KitchenStaff {
    /** Pre-order traversal — prints self, then recurses into children. */
    void printChart(int indent);
    /** Post-order roll-up — returns 1 (this) + sum of children. */
    int  headcount();
    /** Post-order roll-up — returns this.salary + sum(children). */
    int  payroll();
}

/* ───────────────────────── Leaf ───────────────────────── */

/** Leaf — a cook with no subordinates. */
class Cook implements KitchenStaff {

    protected final String name;
    protected final String role;
    protected final int    salary;

    public Cook(String name, String role, int salary) {
        this.name   = name;
        this.role   = role;
        this.salary = salary;
    }

    /** Pre-order: just print self. Recursion stops here. */
    @Override public void printChart(int indent) {
        System.out.println("  ".repeat(indent) + "└─ " + name + " (" + role + ")");
    }

    /** Recursion base case: a leaf is exactly one head. */
    @Override public int headcount() { return 1; }

    /** Recursion base case: a leaf's payroll is its own salary. */
    @Override public int payroll()   { return salary; }
}

/* ───────────────────────── Composite ───────────────────────── */

/**
 * Composite — a chef IS A KitchenStaff (uniform treatment) AND HAS A
 * list of subordinates. Every operation forwards to children, then
 * combines results.
 *
 * Implementation note: Chef extends Cook so it inherits {name, role,
 * salary} — this is a textbook example of a Composite that itself
 * contributes to the operation (the chef's own salary counts), not just
 * a passive container.
 */
class Chef extends Cook {

    private final List<KitchenStaff> subordinates = new ArrayList<>();

    public Chef(String name, String role, int salary) {
        super(name, role, salary);
    }

    public void add(KitchenStaff s)    { subordinates.add(s);   }
    public void remove(KitchenStaff s) { subordinates.remove(s);}

    /**
     * Pre-order traversal: print THIS node first, then recurse into each
     * subordinate. Indentation shows tree depth visually.
     */
    @Override public void printChart(int indent) {
        System.out.println("  ".repeat(indent) + "├─ " + name + " (" + role + ")");
        for (KitchenStaff s : subordinates) {
            s.printChart(indent + 1);     // the recursion
        }
    }

    /**
     * Post-order roll-up: 1 (this chef counts) + sum of children. The
     * recursion is hidden inside this single line.
     */
    @Override public int headcount() {
        int total = 1;                                  // me
        for (KitchenStaff s : subordinates) total += s.headcount();
        return total;
    }

    /**
     * Post-order roll-up: my salary + sum of subordinate payrolls. Works
     * regardless of how deep the tree is — that's the point.
     */
    @Override public int payroll() {
        int total = this.salary;                        // me
        for (KitchenStaff s : subordinates) total += s.payroll();
        return total;
    }
}
