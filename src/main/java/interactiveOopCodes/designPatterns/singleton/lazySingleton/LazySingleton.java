package interactiveOopCodes.designPatterns.singleton.lazySingleton;

/**
 * Lazy Initialization Singleton (single-threaded version).
 *
 *   - Instance is created only on the FIRST call to getInstance().
 *   - Saves resources if the client never needs the singleton.
 *
 * Thread-safety: NOT safe under concurrent first-time access.
 *   Two threads can both see instance == null, both enter the if-block,
 *   and both construct their own instance. See RaceConditionDemo for proof.
 *
 * Fixes: synchronize getInstance(), or use double-checked locking with
 * volatile — or skip both and use BillPughSingleton, which is lazy AND
 * thread-safe by JVM guarantee.
 */
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton() {
        System.out.println("  [LazySingleton] constructor ran");
    }

    public static LazySingleton getInstance() {
        if (instance == null) {              // check + new is not atomic
            instance = new LazySingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println("[App start]  no instance yet");
        LazySingleton a = LazySingleton.getInstance();  // builds it here
        LazySingleton b = LazySingleton.getInstance();  // reuses it
        System.out.println("a == b ? " + (a == b));
    }
}
