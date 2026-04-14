package interactiveOopCodes.uml.umlClassDiagramRelationships.generalizationDemo;

/**
 * UML Generalization Relationship Demo
 *
 * Generalization represents an "is-a" relationship (inheritance).
 * The child class inherits all attributes and behaviors from
 * the parent class and may add or override them.
 *
 * UML Notation: solid line with open (unfilled) triangular arrowhead
 * Direction:    Laptop ———▷ Computer  (Laptop IS-A Computer)
 *
 * Generalization vs Specialization:
 *   - Reading UP the hierarchy:   Laptop → Computer  = Generalization
 *   - Reading DOWN the hierarchy: Computer → Laptop  = Specialization
 */

// ── Generalized (parent) class ───────────────────────────
class Computer {
    protected String brand;
    protected int ramGB;

    public Computer(String brand, int ramGB) {
        this.brand = brand;
        this.ramGB = ramGB;
    }

    public void powerOn() {
        System.out.println(brand + " powering on...");
    }

    public void powerOff() {
        System.out.println(brand + " shutting down...");
    }

    public String toString() {
        return brand + " (" + ramGB + " GB RAM)";
    }
}

// ── Specialized (child) classes ──────────────────────────

// Laptop IS-A Computer (adds portability features)
class Laptop extends Computer {
    private double screenSize;

    public Laptop(String brand, int ramGB, double screenSize) {
        super(brand, ramGB);
        this.screenSize = screenSize;
    }

    // Additional behavior specific to Laptop
    public void foldScreen() {
        System.out.println("Folding " + screenSize + "\" screen.");
    }

    public String toString() {
        return super.toString() + " - Laptop " + screenSize + "\"";
    }
}

// Desktop IS-A Computer (adds tower-specific features)
class Desktop extends Computer {
    private boolean hasDedicatedGPU;

    public Desktop(String brand, int ramGB, boolean hasDedicatedGPU) {
        super(brand, ramGB);
        this.hasDedicatedGPU = hasDedicatedGPU;
    }

    public void upgradeRAM(int additionalGB) {
        this.ramGB += additionalGB;
        System.out.println("Upgraded to " + ramGB + " GB RAM.");
    }

    public String toString() {
        return super.toString() + " - Desktop"
            + (hasDedicatedGPU ? " [GPU]" : "");
    }
}

// Tablet IS-A Computer (adds touch features)
class Tablet extends Computer {
    private boolean hasStylusSupport;

    public Tablet(String brand, int ramGB, boolean hasStylusSupport) {
        super(brand, ramGB);
        this.hasStylusSupport = hasStylusSupport;
    }

    public void touchInput() {
        System.out.println("Touch input on " + brand + " tablet.");
    }

    public String toString() {
        return super.toString() + " - Tablet"
            + (hasStylusSupport ? " [Stylus]" : "");
    }
}

// ── Main ─────────────────────────────────────────────────
public class GeneralizationDemo {
    public static void main(String[] args) {
        // Polymorphism through generalization
        Computer[] computers = {
            new Laptop("Dell", 16, 15.6),
            new Desktop("HP", 32, true),
            new Tablet("Apple", 8, true)
        };

        System.out.println("=== Generalization: All are Computers ===");
        for (Computer c : computers) {
            System.out.println(c);         // toString() polymorphism
            c.powerOn();                   // inherited behavior
        }

        System.out.println("\n=== Specialization: Unique behaviors ===");
        Laptop laptop = (Laptop) computers[0];
        laptop.foldScreen();               // Laptop-specific

        Desktop desktop = (Desktop) computers[1];
        desktop.upgradeRAM(16);            // Desktop-specific

        Tablet tablet = (Tablet) computers[2];
        tablet.touchInput();               // Tablet-specific
    }
}
