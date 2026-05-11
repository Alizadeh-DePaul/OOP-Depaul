package interactiveOopCodes.designPatterns.factoryMethod.logisticsApp;

/**
 * LogisticsApp -- canonical Factory Method demo.
 *
 * Parallel hierarchies:
 *   Creators:  Logistics (abstract) <- RoadLogistics, SeaLogistics,
 *                                       AirLogistics, RailLogistics
 *   Products:  Transport (interface) <- Truck, Ship, Plane, Train
 *
 * Same client method (planDelivery) for every creator;
 * only the FACTORY METHOD createTransport() differs per subclass.
 * That is the entire pattern in one file.
 */
public class LogisticsApp {

    public static void main(String[] args) {
        System.out.println("[App] LogisticsApp boot");
        System.out.println();

        deliverWith(new RoadLogistics(), "Chicago",       800);
        deliverWith(new SeaLogistics(),  "Rotterdam",    4200);
        deliverWith(new AirLogistics(),  "Tokyo",        9800);
        deliverWith(new RailLogistics(), "Vladivostok",  9300);

        System.out.println();
        System.out.println("[App] note: planDelivery() is identical for all creators.");
        System.out.println("[App]       only createTransport() (the FACTORY METHOD) differs.");
    }

    /** The "client" code -- depends ONLY on the abstract Logistics. */
    private static void deliverWith(Logistics shop, String destination, int km) {
        System.out.println("------- " + shop.getClass().getSimpleName() + " -------");
        shop.planDelivery(destination, km);
    }
}

/* ============ PRODUCT (interface) ============ */
interface Transport {
    String deliver(String destination, int km);
}

/* ============ CONCRETE PRODUCTS ============ */
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

/* ============ CREATOR (abstract) -- defines the factory method ============ */
abstract class Logistics {

    /** FACTORY METHOD -- concrete subclasses choose the product. */
    abstract Transport createTransport();

    /**
     * Template method using the factory method.
     * No `new Truck()` here -- only the abstract Product type.
     */
    public void planDelivery(String destination, int km) {
        Transport t = createTransport();              // polymorphic creation
        System.out.println("  1. plan route to " + destination);
        System.out.println("  2. load cargo onto " + t.getClass().getSimpleName());
        System.out.println("  3. " + t.deliver(destination, km));
    }
}

/* ============ CONCRETE CREATORS ============ */
class RoadLogistics extends Logistics {
    @Override Transport createTransport() { return new Truck(); }
}
class SeaLogistics extends Logistics {
    @Override Transport createTransport() { return new Ship(); }
}
class AirLogistics extends Logistics {
    @Override Transport createTransport() { return new Plane(); }
}
class RailLogistics extends Logistics {
    @Override Transport createTransport() { return new Train(); }
}
