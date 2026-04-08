/**
 * UML Composition Relationship Demo
 *
 * Composition is the strongest form of association: a whole-part
 * relationship where parts CANNOT exist without the whole.
 * Their lifetimes are bound together.
 *
 * UML Notation: solid line with filled (solid) diamond
 * Diamond points to the "whole" class.
 *
 *   House ◆———— Room
 *   (whole)     (part — cannot exist without House)
 *
 * Key property: when the whole is destroyed, parts are destroyed too.
 * The whole creates and owns its parts exclusively.
 *
 * Example from lecture:
 *   A House has exactly 1 kitchen, exactly 1 bath,
 *   at least 1 bedroom (1..*), exactly 1 mailbox,
 *   and at most 1 mortgage (0..1).
 */

import java.util.ArrayList;
import java.util.List;

// ── Part classes (no independent existence) ──────────────

class Room {
    private final String type;
    private final double sqft;

    Room(String type, double sqft) {  // package-private: only House creates Rooms
        this.type = type;
        this.sqft = sqft;
    }

    public String toString() {
        return type + " (" + sqft + " sq ft)";
    }
}

class Mailbox {
    private final String number;

    Mailbox(String number) {
        this.number = number;
    }

    public String toString() {
        return "Mailbox #" + number;
    }
}

class Mortgage {
    private final double amount;
    private final double rate;

    Mortgage(double amount, double rate) {
        this.amount = amount;
        this.rate = rate;
    }

    public String toString() {
        return "$" + String.format("%,.0f", amount)
            + " at " + (rate * 100) + "%";
    }
}

// ── Whole class (creates and owns all parts) ─────────────
class House {
    private final String address;
    private final Room kitchen;            // exactly 1
    private final Room bath;               // exactly 1
    private final List<Room> bedrooms;     // 1..*
    private final Mailbox mailbox;         // exactly 1
    private final Mortgage mortgage;       // 0..1 (nullable)

    // House CREATES its parts — composition ownership
    public House(String address, int numBedrooms, Double mortgageAmount) {
        this.address = address;

        // Parts are created BY the whole
        this.kitchen  = new Room("Kitchen", 200);
        this.bath     = new Room("Bathroom", 80);
        this.mailbox  = new Mailbox(address.substring(0, 3));

        this.bedrooms = new ArrayList<>();
        for (int i = 1; i <= numBedrooms; i++) {
            bedrooms.add(new Room("Bedroom " + i, 150));
        }

        // 0..1 multiplicity — mortgage is optional
        this.mortgage = (mortgageAmount != null)
            ? new Mortgage(mortgageAmount, 0.065)
            : null;
    }

    public void describe() {
        System.out.println("House at " + address + ":");
        System.out.println("  " + kitchen);
        System.out.println("  " + bath);
        for (Room br : bedrooms) {
            System.out.println("  " + br);
        }
        System.out.println("  " + mailbox);
        System.out.println("  Mortgage: "
            + (mortgage != null ? mortgage : "None (paid off)"));
    }

    // When House is destroyed, ALL parts are destroyed with it
    public void demolish() {
        System.out.println("\nDemolishing house at " + address + "...");
        System.out.println("  Kitchen, bath, " + bedrooms.size()
            + " bedroom(s), mailbox — all destroyed.");
        if (mortgage != null) {
            System.out.println("  Mortgage is void.");
        }
        // In Java, GC handles destruction.
        // In C++, you'd explicitly delete each part here.
    }
}

// ── Main ─────────────────────────────────────────────────
public class CompositionDemo {
    public static void main(String[] args) {
        System.out.println("=== Composition: House owns its Rooms ===");
        House family = new House("123 Elm St", 3, 350000.0);
        family.describe();

        System.out.println();
        House condo = new House("456 Oak Ave", 1, null);
        condo.describe();

        // Destroying the whole destroys the parts
        family.demolish();

        // You CANNOT create a Room outside of a House —
        // Room's constructor is package-private, enforcing composition.
        // Room stray = new Room("Floating Room", 100); // won't compile outside package
    }
}
