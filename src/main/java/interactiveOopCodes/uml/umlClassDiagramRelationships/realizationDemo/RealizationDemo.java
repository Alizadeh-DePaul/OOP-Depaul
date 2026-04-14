package interactiveOopCodes.uml.umlClassDiagramRelationships.realizationDemo;

/**
 * UML Realization Relationship Demo
 *
 * Realization means a class implements a Java interface,
 * providing concrete behavior for an abstract contract.
 *
 * UML Notation: dashed line with open (unfilled) triangular arrowhead
 * Direction:    EmailNotifier - - -▷ <<Notifier>>
 *
 * Key difference from Generalization:
 *   - Generalization: solid line (inherits implementation)
 *   - Realization:    dashed line (implements specification only)
 */

// ── Interface (the contract / specification) ─────────────
interface Notifier {
    void send(String recipient, String message);
    boolean isAvailable();
}

// ── Another interface (classes can realize multiple) ─────
interface Loggable {
    void log(String entry);
}

// ── Realization: EmailNotifier implements Notifier ───────
class EmailNotifier implements Notifier, Loggable {
    private String smtpServer;

    public EmailNotifier(String smtpServer) {
        this.smtpServer = smtpServer;
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("EMAIL to " + recipient
            + " via " + smtpServer + ": " + message);
    }

    @Override
    public boolean isAvailable() {
        return smtpServer != null && !smtpServer.isEmpty();
    }

    @Override
    public void log(String entry) {
        System.out.println("[EMAIL LOG] " + entry);
    }
}

// ── Realization: SMSNotifier implements Notifier ─────────
class SMSNotifier implements Notifier {
    private String phoneNumber;

    public SMSNotifier(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("SMS from " + phoneNumber
            + " to " + recipient + ": " + message);
    }

    @Override
    public boolean isAvailable() {
        return phoneNumber != null && phoneNumber.length() == 10;
    }
}

// ── Realization: PushNotifier implements Notifier ────────
class PushNotifier implements Notifier {
    private String appId;

    public PushNotifier(String appId) {
        this.appId = appId;
    }

    @Override
    public void send(String recipient, String message) {
        System.out.println("PUSH [" + appId + "] to "
            + recipient + ": " + message);
    }

    @Override
    public boolean isAvailable() {
        return appId != null;
    }
}

// ── Main ─────────────────────────────────────────────────
public class RealizationDemo {
    public static void main(String[] args) {
        // Polymorphism through realization (interface type)
        Notifier[] notifiers = {
            new EmailNotifier("smtp.example.com"),
            new SMSNotifier("3125551234"),
            new PushNotifier("com.myapp")
        };

        System.out.println("=== Realization: All implement Notifier ===");
        for (Notifier n : notifiers) {
            if (n.isAvailable()) {
                n.send("student@depaul.edu", "Class starts at 6 PM!");
            }
        }

        System.out.println("\n=== Multiple Realization ===");
        EmailNotifier email = new EmailNotifier("smtp.depaul.edu");
        email.send("admin@depaul.edu", "Server restarted.");
        email.log("Notification sent successfully.");
    }
}
