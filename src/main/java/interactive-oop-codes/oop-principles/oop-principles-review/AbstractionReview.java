// Abstraction Review — Hiding complexity behind contracts
// Abstract class for shared behavior, interface for capabilities

// INTERFACE — defines WHAT (capability contract)
interface Sendable {
    void send(String to, String message);
    String getChannel();
}

// ABSTRACT CLASS — defines shared STRUCTURE + partial implementation
abstract class Notifier implements Sendable {
    private String senderName;

    public Notifier(String senderName) {
        this.senderName = senderName;
    }

    // Template method — uses abstraction + polymorphism together
    public void notify(String to, String message) {
        System.out.println("[" + getChannel() + "] From: " + senderName);
        send(to, message);  // Polymorphic call — each subclass sends differently
        log(to);
    }

    protected String getSenderName() { return senderName; }

    private void log(String to) {
        System.out.println("  Logged: notification to " + to + " via " + getChannel());
    }
}

// CONCRETE — completes the abstraction
class EmailNotifier extends Notifier {
    public EmailNotifier(String sender) { super(sender); }

    @Override
    public void send(String to, String message) {
        System.out.println("  Email to " + to + ": " + message);
    }

    @Override
    public String getChannel() { return "EMAIL"; }
}

class SmsNotifier extends Notifier {
    public SmsNotifier(String sender) { super(sender); }

    @Override
    public void send(String to, String message) {
        System.out.println("  SMS to " + to + ": " + message);
    }

    @Override
    public String getChannel() { return "SMS"; }
}

public class AbstractionReview {
    // Client uses the ABSTRACTION — doesn't know concrete type
    static void sendAlert(Notifier notifier, String to) {
        notifier.notify(to, "System alert: check dashboard");
    }

    public static void main(String[] args) {
        System.out.println("=== Abstract Class + Interface ===");
        EmailNotifier email = new EmailNotifier("Admin");
        SmsNotifier sms = new SmsNotifier("Admin");

        sendAlert(email, "alice@example.com");
        System.out.println();
        sendAlert(sms, "+1-555-0123");

        System.out.println("\n=== Interface as Type ===");
        Sendable s = email;  // Interface reference — only send() and getChannel() visible
        s.send("bob@example.com", "Hello from interface ref");
        // s.notify(...)  — NOT available through Sendable interface
        // notify() is defined in Notifier, not Sendable

        System.out.println("\n=== Abstraction Hides Complexity ===");
        System.out.println("sendAlert() doesn't know if it's email or SMS");
        System.out.println("It only knows Notifier has notify() — that's abstraction");
    }
}
