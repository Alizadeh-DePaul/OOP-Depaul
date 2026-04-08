// The Four Pillars United — All 4 OOP principles in one hierarchy
// Payment processing: abstraction + inheritance + polymorphism + encapsulation

// ABSTRACTION — interface defines the payment contract
interface Payable {
    boolean charge(double amount);
    String receipt();
}

// INHERITANCE + ABSTRACTION — abstract class provides shared structure
abstract class PaymentMethod implements Payable {
    // ENCAPSULATION — private fields, controlled access
    private String holder;
    private double totalCharged;
    private int transactionCount;

    public PaymentMethod(String holder) {
        this.holder = holder;
        this.totalCharged = 0;
        this.transactionCount = 0;
    }

    // Template method — combines inheritance + polymorphism
    public final boolean charge(double amount) {
        if (amount <= 0) return false;
        if (!authorize(amount)) {
            System.out.println("  DECLINED: " + getType() + " authorization failed");
            return false;
        }
        totalCharged += amount;
        transactionCount++;
        System.out.println("  APPROVED: $" + amount + " via " + getType());
        return true;
    }

    // ABSTRACTION — subclasses define their own authorization
    protected abstract boolean authorize(double amount);
    protected abstract String getType();

    protected String getHolder() { return holder; }
    public double getTotalCharged() { return totalCharged; }

    public String receipt() {
        return getType() + " [" + holder + "]: " + transactionCount +
               " transactions, total $" + totalCharged;
    }
}

// INHERITANCE — concrete implementations
class CreditCard extends PaymentMethod {
    private String last4;
    private double limit;

    public CreditCard(String holder, String last4, double limit) {
        super(holder);
        this.last4 = last4;
        this.limit = limit;
    }

    // POLYMORPHISM — each payment type authorizes differently
    @Override
    protected boolean authorize(double amount) {
        return amount <= limit - getTotalCharged();
    }

    @Override
    protected String getType() { return "CreditCard(***" + last4 + ")"; }
}

class DigitalWallet extends PaymentMethod {
    private double walletBalance;

    public DigitalWallet(String holder, double balance) {
        super(holder);
        this.walletBalance = balance;
    }

    @Override
    protected boolean authorize(double amount) {
        if (amount > walletBalance) return false;
        walletBalance -= amount;  // Deduct immediately
        return true;
    }

    @Override
    protected String getType() { return "DigitalWallet"; }
}

public class FourPillarsUnited {
    // POLYMORPHISM — processes ANY Payable without knowing concrete type
    static void processOrder(Payable method, double amount) {
        System.out.println("Processing $" + amount + "...");
        method.charge(amount);
    }

    public static void main(String[] args) {
        System.out.println("=== The Four Pillars in Action ===\n");

        // Create concrete payment methods
        CreditCard visa = new CreditCard("Alice", "4242", 500);
        DigitalWallet wallet = new DigitalWallet("Bob", 200);

        // POLYMORPHISM — same method, different behavior
        processOrder(visa, 150);
        processOrder(visa, 200);
        processOrder(wallet, 180);
        processOrder(wallet, 50);   // Exceeds wallet balance → declined

        System.out.println("\n=== Receipts ===");
        System.out.println(visa.receipt());
        System.out.println(wallet.receipt());

        System.out.println("\n=== Principle Breakdown ===");
        System.out.println("ABSTRACTION:   Payable interface + abstract PaymentMethod");
        System.out.println("INHERITANCE:   CreditCard/DigitalWallet extend PaymentMethod");
        System.out.println("POLYMORPHISM:  processOrder() works with any Payable");
        System.out.println("ENCAPSULATION: holder, limit, balance are all private");
    }
}
