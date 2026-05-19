package interactiveOopCodes.designPatterns.adapter.voltageConverterClassAdapter;

/**
 * Adapter Pattern - Voltage Converter (CLASS ADAPTER variant).
 *
 *   Same scenario as the Object Adapter version: a US laptop needs to plug into
 *   a 220V European socket. This time the Adapter EXTENDS the Adaptee instead of
 *   wrapping it - and additionally IMPLEMENTS the Target interface.
 *
 *   CLASS ADAPTER = extends Adaptee (inheritance) + implements Target.
 *
 *   Notes / restrictions (Java-specific):
 *
 *     1) Target MUST be an interface. Java has single class inheritance, so you
 *        cannot extend Adaptee AND extend a Target class at the same time.
 *
 *     2) The Adapter IS-A Adaptee, so it can be used wherever the Adaptee is
 *        expected too. Sometimes useful, sometimes a leaky abstraction.
 *
 *     3) You cannot adapt subclasses of the Adaptee at runtime - the wrapped
 *        class is baked in at compile time.
 */
public class VoltageConverterClassAdapter {

    public static void main(String[] args) {
        USLaptop laptop = new USLaptop();

        // ── CLASS ADAPTER: a single object that IS-A USOutlet AND IS-A EuropeanSocket ──
        Voltage220To110ClassAdapter adapter = new Voltage220To110ClassAdapter();

        // Use it as a USOutlet (the Target):
        System.out.println("Through Class Adapter (as USOutlet): "
            + adapter.provide110V() + "V");
        laptop.charge(adapter);

        // Because the Adapter IS-A EuropeanSocket too, it can also be used
        // wherever a EuropeanSocket is expected - the dual-interface side
        // effect of class adapters.
        EuropeanSocket alsoEU = adapter;
        System.out.println("Same object as a EuropeanSocket: "
            + alsoEU.produce220V() + "V");
    }
}

/** TARGET - must be an interface for Class Adapter to be possible in Java. */
interface USOutlet {
    int provide110V();
}

/** ADAPTEE - a class with the legacy/foreign interface. */
class EuropeanSocket {
    public int produce220V() {
        return 220;
    }
}

/**
 * CLASS ADAPTER - extends EuropeanSocket (inheritance), implements USOutlet.
 * Inherits produce220V() for free; no composition field needed.
 */
class Voltage220To110ClassAdapter extends EuropeanSocket implements USOutlet {

    @Override
    public int provide110V() {
        return produce220V() / 2;          // call inherited Adaptee method, then convert
    }
}

/** CLIENT - sees only the Target interface. */
class USLaptop {
    public void charge(USOutlet outlet) {
        int v = outlet.provide110V();
        System.out.println("US Laptop charging at " + v + "V - " +
            (v == 110 ? "OK" : "DAMAGE - wrong voltage!"));
    }
}
