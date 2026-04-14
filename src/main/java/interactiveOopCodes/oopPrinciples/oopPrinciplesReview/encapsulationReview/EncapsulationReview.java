package interactiveOopCodes.oopPrinciples.oopPrinciplesReview.encapsulationReview;

// Encapsulation Review — Protecting state across an inheritance hierarchy
// Shows the tension between inheritance (wants access) and encapsulation (wants restriction)

import java.util.*;

class Account {
    private double balance;         // Private — not even subclasses can touch
    private List<String> history;   // Mutable internal state

    public Account(double initial) {
        if (initial < 0) throw new IllegalArgumentException("Negative initial balance");
        this.balance = initial;
        this.history = new ArrayList<>();
        this.history.add("Opened with $" + initial);
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Must be positive");
        balance += amount;
        history.add("Deposit: +$" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Must be positive");
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds");
        balance -= amount;
        history.add("Withdraw: -$" + amount);
    }

    // Controlled access — subclasses read balance through this method
    protected double getBalance() { return balance; }

    // Defensive copy — never expose the mutable list
    public List<String> getHistory() {
        return List.copyOf(history);
    }

    public String toString() {
        return getClass().getSimpleName() + " [Balance: $" + balance + "]";
    }
}

class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(double initial, double rate) {
        super(initial);
        this.interestRate = rate;
    }

    public void applyInterest() {
        // Uses protected getBalance() — cannot access 'balance' field directly
        double interest = getBalance() * interestRate;
        deposit(interest);  // Goes through validation — encapsulation preserved!
        System.out.println("Interest applied: $" + interest);
    }
}

public class EncapsulationReview {
    public static void main(String[] args) {
        System.out.println("=== Encapsulation Protects Hierarchy ===");
        SavingsAccount savings = new SavingsAccount(1000, 0.05);
        savings.deposit(500);
        savings.applyInterest();
        System.out.println(savings);

        System.out.println("\n=== Defensive Copy in Action ===");
        List<String> hist = savings.getHistory();
        System.out.println("History: " + hist);
        try {
            hist.add("HACKED");  // Fails — unmodifiable!
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify history: defensive copy works");
        }

        System.out.println("\n=== Private vs Protected ===");
        // savings.balance    — ERROR: balance is private to Account
        // savings.getBalance() — OK: protected getter, visible to subclass
        System.out.println("Balance via getter: $" + savings.getBalance());

        System.out.println("\n=== Validation Enforced Across Hierarchy ===");
        try {
            savings.withdraw(5000);  // Fails — encapsulation catches it
        } catch (IllegalArgumentException e) {
            System.out.println("Blocked: " + e.getMessage());
        }
    }
}
