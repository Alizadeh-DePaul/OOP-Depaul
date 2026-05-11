package interactiveOopCodes.designPrinciples.solid.dipDemo;

/**
 * DIP — Dependency Inversion Principle.
 *
 * 1. High-level modules should NOT depend on low-level modules.
 *    Both should depend on abstractions.
 * 2. Abstractions should NOT depend on details.
 *    Details should depend on abstractions.
 *
 * VIOLATION: BadOrderService (HIGH-level business logic) directly
 * instantiates EmailSender (LOW-level transport detail). The arrow
 * of dependency points DOWN — the business rule is shackled to a
 * single transport. You cannot test BadOrderService without sending
 * real email; you cannot swap email for SMS without editing the
 * business class.
 *
 * SOLUTION: Introduce a Notifier ABSTRACTION. The business class
 * depends on Notifier (the interface). Concrete senders also depend
 * on Notifier. Both arrows now point UP toward the abstraction —
 * the dependency has been "inverted". Wiring happens at the seam
 * (main / framework), the business rule never names a concrete
 * sender. Compare to the wall socket: your laptop charger and your
 * lamp both depend on the SOCKET abstraction, never on each other.
 */
public class DipDemo {

    public static void main(String[] args) {
        System.out.println("=== DIP — OrderService Notifications ===\n");

        // --- VIOLATION ---
        System.out.println("[VIOLATION] OrderService hardcoded to EmailSender:");
        BadOrderService bad = new BadOrderService();
        bad.placeOrder("Clean Code", "alice@example.com");

        System.out.println();

        // --- SOLUTION ---
        System.out.println("[SOLUTION] Inject the abstraction; swap freely:");
        OrderService viaEmail = new OrderService(new EmailNotifier());
        viaEmail.placeOrder("Clean Code", "alice@example.com");

        OrderService viaSms = new OrderService(new SmsNotifier());
        viaSms.placeOrder("The Pragmatic Programmer", "+1-555-0100");

        OrderService viaTest = new OrderService(new TestNotifier()); // unit-test seam
        viaTest.placeOrder("Effective Java", "test-recipient");
    }
}

/* ─────────────────────────── VIOLATION ─────────────────────────── */

class EmailSender {
    void send(String to, String message) {
        System.out.println("  EmailSender -> " + to + " : " + message);
    }
}

class BadOrderService {
    // High-level rule is welded to a low-level transport.
    private final EmailSender emailSender = new EmailSender();   // 'new' smell

    void placeOrder(String item, String customer) {
        emailSender.send(customer, "Order placed: " + item);
    }
}

/* ─────────────────────────── SOLUTION ─────────────────────────── */

interface Notifier {
    void notify(String recipient, String message);
}

class EmailNotifier implements Notifier {
    public void notify(String recipient, String message) {
        System.out.println("  Email   -> " + recipient + " : " + message);
    }
}

class SmsNotifier implements Notifier {
    public void notify(String recipient, String message) {
        System.out.println("  SMS     -> " + recipient + " : " + message);
    }
}

class TestNotifier implements Notifier {
    public void notify(String recipient, String message) {
        System.out.println("  TEST    -> captured for assertion: '" + message + "' to " + recipient);
    }
}

class OrderService {
    private final Notifier notifier;   // depends on ABSTRACTION

    OrderService(Notifier notifier) {  // injected at the seam
        this.notifier = notifier;
    }

    void placeOrder(String item, String customer) {
        notifier.notify(customer, "Order placed: " + item);
    }
}
