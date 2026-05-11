package interactiveOopCodes.designPatterns.factoryMethod.parameterizedFactory;

/**
 * Parameterized Factory Method.
 *
 * Variant of GoF Factory Method: a SINGLE Creator class with one factory
 * method that takes a control parameter. Subclasses do not multiply when
 * the only difference is "which concrete product type." The GoF book
 * mentions this in the Implementation section under
 *   "Parameterized factory methods."
 *
 * NOTE: This is NOT the Simple Factory anti-pattern. Simple Factory is a
 * separate utility class with a static method; Parameterized Factory
 * Method lives on the Creator itself and can be overridden by subclasses.
 */
public class ParameterizedFactory {

    enum Mode { ROAD, SEA, AIR, RAIL }

    public static void main(String[] args) {
        Logistics shop = new Logistics();

        for (Mode m : Mode.values()) {
            System.out.println("------- " + m + " -------");
            shop.planDelivery(m, destinationFor(m), 1000);
        }
    }

    private static String destinationFor(Mode m) {
        switch (m) {
            case ROAD: return "Chicago";
            case SEA:  return "Rotterdam";
            case AIR:  return "Tokyo";
            case RAIL: return "Vladivostok";
            default:   return "??";
        }
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

class Logistics {

    /** Parameterized factory method -- one method, branches by mode. */
    Transport createTransport(ParameterizedFactory.Mode mode) {
        switch (mode) {
            case ROAD: return new Truck();
            case SEA:  return new Ship();
            case AIR:  return new Plane();
            case RAIL: return new Train();
            default:   throw new IllegalArgumentException("unknown mode: " + mode);
        }
    }

    public void planDelivery(ParameterizedFactory.Mode mode, String destination, int km) {
        Transport t = createTransport(mode);
        System.out.println("  load onto " + t.getClass().getSimpleName());
        System.out.println("  " + t.deliver(destination, km));
    }
}
