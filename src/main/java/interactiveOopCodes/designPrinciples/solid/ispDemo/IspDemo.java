package interactiveOopCodes.designPrinciples.solid.ispDemo;

/**
 * ISP — Interface Segregation Principle.
 *
 * "Clients should not be forced to depend on methods they do not use."
 *
 * VIOLATION: BadVehicle is a "fat" interface — every implementer must
 * provide park(), drive(), charge(), and refuel(). A GasCar has no
 * battery (so charge() is a lie); an electric car has no fuel tank
 * (so refuel() is a lie). The most common smell is to throw
 * UnsupportedOperationException in the methods you cannot honor —
 * the same LSP-style trap.
 *
 * SOLUTION: Split into ROLE INTERFACES. Each vehicle implements ONLY
 * the roles it actually fulfills. Java's own JDK uses this pattern:
 * MouseListener is a fat interface (5 methods); MouseAdapter offers
 * empty defaults so a client overrides just the ONE method it cares
 * about. ISP is also visible in java.io: Readable, Closeable,
 * Flushable, and AutoCloseable are role interfaces — a class
 * implements only the ones it can actually honor.
 */
public class IspDemo {

    public static void main(String[] args) {
        System.out.println("=== ISP — Vehicle Roles ===\n");

        // --- VIOLATION ---
        System.out.println("[VIOLATION] GasCar is forced to fake charge():");
        BadVehicle gas = new BadGasCar();
        gas.park();
        gas.drive();
        gas.refuel();
        try { gas.charge(); }
        catch (UnsupportedOperationException ex) {
            System.out.println("  threw: " + ex.getMessage());
        }

        System.out.println();

        // --- SOLUTION ---
        System.out.println("[SOLUTION] Each vehicle implements ONLY its real roles:");
        GasCar honda = new GasCar();
        honda.park();
        honda.drive();
        honda.refuel();
        // honda.charge();   // <-- COMPILER ERROR — GasCar isn't Chargeable.

        Tesla tesla = new Tesla();
        tesla.park();
        tesla.drive();
        tesla.charge();
        // tesla.refuel();   // <-- COMPILER ERROR — Tesla isn't Refuelable.
    }
}

/* ─────────────────────────── VIOLATION ─────────────────────────── */

interface BadVehicle {
    void park();
    void drive();
    void charge();   // every vehicle is forced to "implement" this
    void refuel();   // every vehicle is forced to "implement" this
}

class BadGasCar implements BadVehicle {
    public void park()   { System.out.println("  BadGasCar parked"); }
    public void drive()  { System.out.println("  BadGasCar driving"); }
    public void refuel() { System.out.println("  BadGasCar refueled"); }
    public void charge() {
        // I do not have a battery — this method should not exist on me.
        throw new UnsupportedOperationException("GasCar has no battery");
    }
}

/* ─────────────────────────── SOLUTION ─────────────────────────── */

interface Parkable    { void park(); }
interface Drivable    { void drive(); }
interface Chargeable  { void charge(); }
interface Refuelable  { void refuel(); }

class GasCar implements Parkable, Drivable, Refuelable {
    public void park()   { System.out.println("  GasCar parked"); }
    public void drive()  { System.out.println("  GasCar driving"); }
    public void refuel() { System.out.println("  GasCar refueled"); }
}

class Tesla implements Parkable, Drivable, Chargeable {
    public void park()   { System.out.println("  Tesla parked"); }
    public void drive()  { System.out.println("  Tesla driving"); }
    public void charge() { System.out.println("  Tesla charging"); }
}
