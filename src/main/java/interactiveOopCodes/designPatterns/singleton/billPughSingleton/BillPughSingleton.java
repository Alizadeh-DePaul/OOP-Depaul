package interactiveOopCodes.designPatterns.singleton.billPughSingleton;

/**
 * Bill Pugh Singleton — the "initialization-on-demand holder" idiom.
 *
 *   - Lazy: the inner Holder class is NOT loaded until getInstance() is
 *     called for the first time. The JVM defers class loading until
 *     reference resolution.
 *   - Thread-safe by JVM guarantee: once Holder is referenced, its static
 *     initializer runs under the class-init lock — exactly once, atomically.
 *   - No synchronized keyword needed.
 *
 * History: Bill Pugh (University of Maryland) drove the Java memory-model
 * changes in Java 5 that made the older "double-checked locking" idiom
 * actually work, and championed this simpler holder-class idiom as the
 * recommended Java pattern.
 *
 * When to use: this is the default modern Java singleton.
 */
public class BillPughSingleton {

    private BillPughSingleton() {
        System.out.println("  [BillPughSingleton] constructor ran");
    }

    /* Holder is loaded lazily — only when getInstance() touches it.
       Its static initializer runs exactly once, under the JVM class-init lock. */
    private static class Holder {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return Holder.INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println("[App start]  Holder class NOT loaded yet");
        BillPughSingleton a = BillPughSingleton.getInstance(); // Holder loads now
        BillPughSingleton b = BillPughSingleton.getInstance();
        System.out.println("a == b ? " + (a == b));
    }
}
