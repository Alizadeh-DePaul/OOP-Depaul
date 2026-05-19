package interactiveOopCodes.designPatterns.adapter.voltageConverterObjectAdapter;

/**
 * Adapter Pattern - Voltage Converter (OBJECT ADAPTER variant).
 *
 *   A US laptop expects a 110V outlet, but you only have a 220V European socket.
 *   You cannot modify the European socket (it is the wall, owned by the building),
 *   and you cannot modify the laptop (it is sealed hardware). The adapter wraps
 *   the EU socket and exposes the US-laptop-compatible interface, converting the
 *   voltage on the way through.
 *
 *   OBJECT ADAPTER = implements Target + HAS-A Adaptee (composition).
 */
public class VoltageConverterObjectAdapter {

    public static void main(String[] args) {
        // ── Adaptee: the existing 220V European wall socket - we cannot modify it ──
        EuropeanSocket european = new EuropeanSocket();
        System.out.println("Direct from European socket: " + european.produce220V() + "V");

        // ── Client: a US laptop that ONLY knows how to talk to USOutlet ──
        USLaptop laptop = new USLaptop();

        // ── Without an adapter, the laptop cannot accept the European socket. ──
        //    laptop.charge(european);   // X compile error: USOutlet expected
        System.out.println("(Without adapter: laptop.charge(european) does not compile.)");

        // ── OBJECT ADAPTER: implements USOutlet, holds a EuropeanSocket inside ──
        USOutlet adapter = new Voltage220To110ObjectAdapter(european);
        System.out.println("Through Object Adapter: " + adapter.provide110V() + "V");

        // The laptop happily plugs into the adapter - it sees a USOutlet.
        laptop.charge(adapter);

        // Swap the Adaptee at runtime - the great strength of Object Adapters.
        EuropeanSocket anotherEU = new EuropeanSocket();
        USOutlet adapter2 = new Voltage220To110ObjectAdapter(anotherEU);
        laptop.charge(adapter2);
    }
}

/** TARGET - the interface the Client (USLaptop) was written against. */
interface USOutlet {
    int provide110V();
}

/** ADAPTEE - the legacy class with the "wrong" interface (and we cannot modify it). */
class EuropeanSocket {
    public int produce220V() {
        return 220;
    }
}

/**
 * OBJECT ADAPTER - IS-A Target (USOutlet) + HAS-A Adaptee (EuropeanSocket).
 * The dual relationship is the whole pattern: same shape as a USOutlet so the
 * Client cannot tell the difference, and holds a EuropeanSocket so it can
 * forward the call (with conversion) to the legacy code.
 */
class Voltage220To110ObjectAdapter implements USOutlet {

    private final EuropeanSocket wrapped;   // HAS-A

    public Voltage220To110ObjectAdapter(EuropeanSocket wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public int provide110V() {
        int eu = wrapped.produce220V();    // forward to the Adaptee
        return eu / 2;                     // convert (the "adapt" step)
    }
}

/** CLIENT - sees only the Target interface. Knows nothing about EuropeanSocket. */
class USLaptop {
    public void charge(USOutlet outlet) {
        int v = outlet.provide110V();
        System.out.println("US Laptop charging at " + v + "V - " +
            (v == 110 ? "OK" : "DAMAGE - wrong voltage!"));
    }
}
