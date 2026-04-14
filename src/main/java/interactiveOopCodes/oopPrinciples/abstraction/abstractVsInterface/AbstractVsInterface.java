package interactiveOopCodes.oopPrinciples.abstraction.abstractVsInterface;

// Abstract Class vs Interface — Same problem, two approaches
// Shows when to use each and how they differ

// --- Approach 1: Abstract Class ---
abstract class AbstractNotifier {
    private String recipient;

    AbstractNotifier(String recipient) {
        this.recipient = recipient;
    }

    // Template: shared logic in abstract class
    void sendNotification(String message) {
        System.out.println("Preparing notification for: " + recipient);
        deliver(message);
        System.out.println("Notification sent!\n");
    }

    // Subclass decides HOW to deliver
    abstract void deliver(String message);

    protected String getRecipient() {
        return recipient;
    }
}

class EmailNotifier extends AbstractNotifier {
    EmailNotifier(String recipient) {
        super(recipient);
    }

    @Override
    void deliver(String message) {
        System.out.println("  EMAIL to " + getRecipient() + ": " + message);
    }
}

class SmsNotifier extends AbstractNotifier {
    SmsNotifier(String recipient) {
        super(recipient);
    }

    @Override
    void deliver(String message) {
        System.out.println("  SMS to " + getRecipient() + ": " + message);
    }
}

// --- Approach 2: Interface ---
interface Notifiable {
    void deliver(String recipient, String message);

    // Default method — shared behavior (Java 8+)
    default void sendNotification(String recipient, String message) {
        System.out.println("Preparing notification for: " + recipient);
        deliver(recipient, message);
        System.out.println("Notification sent!\n");
    }
}

class PushNotifier implements Notifiable {
    @Override
    public void deliver(String recipient, String message) {
        System.out.println("  PUSH to " + recipient + ": " + message);
    }
}

// Interface allows multiple inheritance — a class can be both
class MultiChannelNotifier implements Notifiable {
    @Override
    public void deliver(String recipient, String message) {
        System.out.println("  MULTI-CHANNEL to " + recipient + ": " + message);
    }
}

public class AbstractVsInterface {
    public static void main(String[] args) {
        System.out.println("=== Abstract Class vs Interface ===\n");

        System.out.println("--- Abstract Class Approach ---");
        AbstractNotifier email = new EmailNotifier("alice@example.com");
        email.sendNotification("Meeting at 3pm");

        AbstractNotifier sms = new SmsNotifier("+1-555-0100");
        sms.sendNotification("Reminder: deadline today");

        System.out.println("--- Interface Approach ---");
        Notifiable push = new PushNotifier();
        push.sendNotification("bob", "New update available");

        Notifiable multi = new MultiChannelNotifier();
        multi.sendNotification("carol", "Urgent alert");

        System.out.println("--- Key Differences ---");
        System.out.println("Abstract class: has state (recipient field), constructor, protected methods");
        System.out.println("Interface: no state, no constructor, but supports multiple inheritance");
        System.out.println("Abstract class: IS-A relationship (EmailNotifier IS-A Notifier)");
        System.out.println("Interface: CAN-DO capability (PushNotifier CAN-DO Notifiable)");
    }
}
