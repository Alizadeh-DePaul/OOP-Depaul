package interactiveOopCodes.designPatterns.singleton.eagerSingleton;

/**
 * Eager Initialization Singleton.
 *
 *   - Instance is created at class-loading time (when the JVM first
 *     references EagerSingleton).
 *   - Simplest variant: no synchronization, no checks.
 *   - Trade-off: the instance is built even if the client never calls
 *     getInstance() — wasted memory/init time for heavy singletons.
 *
 * Thread-safety: guaranteed by the JVM's class-initialization contract —
 * the static initializer runs exactly once under a class-init lock.
 *
 * When to use: cheap to construct AND almost certainly needed every run.
 */
public class EagerSingleton {

    /* Created at class load time. final = truly unchangeable. */
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
        System.out.println("  [EagerSingleton] constructor ran");
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public String greet() {
        return "Hello from the single EagerSingleton";
    }

    public static void main(String[] args) {
        System.out.println("[App start]  (note: instance is ALREADY built by now)");
        EagerSingleton a = EagerSingleton.getInstance();
        EagerSingleton b = EagerSingleton.getInstance();
        System.out.println("a == b ? " + (a == b));
        System.out.println(a.greet());
    }
}
