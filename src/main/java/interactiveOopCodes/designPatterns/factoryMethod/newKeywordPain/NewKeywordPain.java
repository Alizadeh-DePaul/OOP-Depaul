package interactiveOopCodes.designPatterns.factoryMethod.newKeywordPain;

/**
 * NewKeywordPain -- the BEFORE state.
 *
 * Imagine the team started with one mode (Truck). Then SEA support was
 * added by shotgun-pasting `if (mode == SEA) new Ship() else new Truck()`
 * into every site that creates a transport. Now adding AIR means going
 * back and editing every if-chain -- and inevitably missing one.
 *
 * That ripple cost is the pain Factory Method removes:
 *   - Client code is welded to concrete classes (Truck, Ship, ...).
 *   - Adding a Product = editing every if-chain (violates OCP).
 *   - The mode parameter has to be threaded through every site.
 */
public class NewKeywordPain {

    enum Mode { ROAD, SEA }      // <- new modes (e.g., AIR) would be added here

    public static void main(String[] args) {
        System.out.println("[NewKeywordPain] direct-new client code:");
        System.out.println();

        deliver(Mode.ROAD, "Chicago",    800);
        deliver(Mode.SEA,  "Rotterdam", 4200);

        // To add AIR support: a teammate must edit every if-chain below.
        // They WILL miss one. That bug is the punchline.
    }

    /** Site #1 -- picks a Transport via if-chain. */
    static void deliver(Mode mode, String destination, int km) {
        Transport t;
        if (mode == Mode.ROAD)      t = new Truck();
        else if (mode == Mode.SEA)  t = new Ship();
        // else if (mode == Mode.AIR) t = new Plane();   // <- add for AIR
        else throw new IllegalArgumentException("unknown mode: " + mode);

        System.out.println("  1. plan route to " + destination);
        System.out.println("  2. " + describe(mode));
        System.out.println("  3. " + t.deliver(destination, km));
    }

    /** Site #2 -- ALSO branches on mode; duplicates the if-chain. */
    static String describe(Mode mode) {
        if (mode == Mode.ROAD)     return "load cargo onto truck";
        else if (mode == Mode.SEA) return "load cargo onto ship";
        // else if (mode == Mode.AIR) return "load cargo onto plane";
        else throw new IllegalArgumentException();
    }
}

interface Transport {
    String deliver(String destination, int km);
}

class Truck implements Transport {
    @Override public String deliver(String d, int km) {
        return "Truck rumbled " + km + " km by road to " + d;
    }
}

class Ship implements Transport {
    @Override public String deliver(String d, int km) {
        return "Cargo ship sailed " + km + " km by sea to " + d;
    }
}
