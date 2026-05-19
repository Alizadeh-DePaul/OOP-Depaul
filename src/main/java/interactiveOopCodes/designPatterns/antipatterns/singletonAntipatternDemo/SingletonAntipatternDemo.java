package interactiveOopCodes.designPatterns.antipatterns.singletonAntipatternDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * SingletonAntipatternDemo - the same instance, two designs.
 *
 *   The Singleton itself is not always an antipattern; it becomes one
 *   when callers reach into Logger.getInstance() from inside their
 *   method bodies. That hides the dependency and welds the caller to
 *   a global, which breaks testability and SRP.
 *
 *   BAD : OrderServiceBad calls Logger.getInstance() inside its method.
 *         The dependency is invisible from the constructor signature;
 *         tests cannot substitute a fake Logger.
 *
 *   GOOD: OrderServiceGood receives an ILogger via its constructor
 *         (dependency injection). Production wires in an adapter that
 *         delegates to the Singleton; tests pass a FakeLogger.
 *
 *   The Singleton class is UNCHANGED. What changes is HOW collaborators
 *   acquire it - through their constructor, where it is visible.
 */
public class SingletonAntipatternDemo {

    public static void main(String[] args) {
        // BAD: hidden global call
        new OrderServiceBad().place("book");

        // GOOD: production wires the real Singleton through DI
        new OrderServiceGood(new LoggerAdapter()).place("laptop");

        // GOOD: tests substitute a fake - only possible with DI
        FakeLogger fake = new FakeLogger();
        new OrderServiceGood(fake).place("pen");
        System.out.println("captured by FakeLogger: " + fake.messages);

        // The Singleton is still a Singleton. We just stopped HIDING it.
    }
}

/* -------------------- The Singleton (unchanged) -------------------- */

class Logger {
    private static final Logger INSTANCE = new Logger();
    private Logger() {}
    public static Logger getInstance() { return INSTANCE; }
    public void info(String msg) { System.out.println("[real] " + msg); }
}

/* -------------------- BAD: hidden dependency -------------------- */

class OrderServiceBad {
    public void place(String item) {
        // Logger.getInstance() is invisible from the constructor.
        // No test can swap it. Every call welds us to the global.
        Logger.getInstance().info("placed: " + item);
    }
}

/* -------------------- GOOD: dependency injection -------------------- */

interface ILogger { void info(String msg); }

/** Adapter so the existing Singleton fits the interface. */
class LoggerAdapter implements ILogger {
    @Override public void info(String msg) { Logger.getInstance().info(msg); }
}

class OrderServiceGood {
    private final ILogger logger;
    public OrderServiceGood(ILogger logger) { this.logger = logger; }
    public void place(String item) { logger.info("placed: " + item); }
}

/** Test double - only possible when the dependency is injected. */
class FakeLogger implements ILogger {
    final List<String> messages = new ArrayList<>();
    @Override public void info(String msg) { messages.add(msg); }
}
