package interactiveOopCodes.designPatterns.singleton.staticBlockSingleton;

/**
 * Static Block Initialization Singleton.
 *
 *   - Like Eager, but wraps construction in try/catch so checked
 *     exceptions thrown during init can be re-thrown as RuntimeException.
 *   - Still built at class-load time — NOT lazy.
 *
 * Thread-safety: same JVM class-init guarantee as Eager.
 *
 * When to use: instance construction can throw (file I/O, network,
 * config parsing) and you want a fail-fast error at class load.
 */
public class StaticBlockSingleton {

    private static final StaticBlockSingleton INSTANCE;

    /* Runs once, at class load. Wraps init so exceptions become RuntimeException. */
    static {
        try {
            INSTANCE = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Singleton init failed", e);
        }
    }

    private StaticBlockSingleton() {
        System.out.println("  [StaticBlockSingleton] constructor ran");
        // pretend this could throw on a real init: load config, open file, ...
    }

    public static StaticBlockSingleton getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println("[App start]  static block has already initialized INSTANCE");
        StaticBlockSingleton a = StaticBlockSingleton.getInstance();
        StaticBlockSingleton b = StaticBlockSingleton.getInstance();
        System.out.println("a == b ? " + (a == b));
    }
}
