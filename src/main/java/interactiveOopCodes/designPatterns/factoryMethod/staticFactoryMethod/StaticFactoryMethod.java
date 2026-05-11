package interactiveOopCodes.designPatterns.factoryMethod.staticFactoryMethod;

/**
 * Static Factory Method -- Joshua Bloch idiom (Effective Java Item 1).
 *
 * NOTE: This is NOT the GoF Factory Method pattern. Both are called
 * "factory methods", but they are different ideas:
 *
 *   GoF Factory Method     -- INSTANCE method on a Creator subclass,
 *                             overridden polymorphically to choose the
 *                             concrete product.
 *
 *   Static Factory Method  -- STATIC method, named for intent, that
 *                             returns an instance of the enclosing type.
 *                             No subclassing required.
 *
 * Reasons to prefer Static Factory Methods over public constructors:
 *   1. Names. `Transport.byRoad()` reads better than `new Transport(true)`.
 *   2. Cache and reuse. `Boolean.valueOf` returns one of two cached values.
 *   3. Return a subtype. `Collections.unmodifiableList` does this.
 *   4. Return any subtype known at call time. `Calendar.getInstance` does this.
 *
 * Real-world JDK examples:
 *   - Integer.valueOf(int)        cached for small int range
 *   - List.of(...), Map.of(...)   immutable factories (Java 9+)
 *   - Calendar.getInstance()      locale-specific Calendar subtype
 *   - Path.of(String...)          filesystem Path (Java 11+)
 */
public class StaticFactoryMethod {

    public static void main(String[] args) {
        // Named static factories -- no `new` keyword, no Creator subclass.
        Transport a = Transport.byRoad();
        Transport b = Transport.bySea();
        Transport c = Transport.byAir();
        Transport d = Transport.byRail();

        System.out.println(a.deliver("Chicago",      800));
        System.out.println(b.deliver("Rotterdam",   4200));
        System.out.println(c.deliver("Tokyo",       9800));
        System.out.println(d.deliver("Vladivostok", 9300));

        // Static factory CAN cache: byRoad() always returns the same Truck.
        System.out.println();
        System.out.println("byRoad() == byRoad() ? "
            + (Transport.byRoad() == Transport.byRoad()));
        System.out.println("bySea()  == bySea()  ? "
            + (Transport.bySea() == Transport.bySea()));
    }
}

interface Transport {
    String deliver(String destination, int km);

    /* ---- static factory methods (Bloch idiom) ---- */
    static Transport byRoad() { return TruckHolder.INSTANCE; }   // cached singleton
    static Transport bySea()  { return new Ship();  }            // fresh each call
    static Transport byAir()  { return new Plane(); }
    static Transport byRail() { return new Train(); }
}

/** Holder for the cached Truck instance (lazy, thread-safe by JVM). */
final class TruckHolder {
    static final Transport INSTANCE = new Truck();
    private TruckHolder() {}
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
class Plane implements Transport {
    @Override public String deliver(String d, int km) {
        return "Cargo plane flew " + km + " km by air to " + d;
    }
}
class Train implements Transport {
    @Override public String deliver(String d, int km) {
        return "Cargo train chugged " + km + " km by rail to " + d;
    }
}
