package interactiveOopCodes.designPrinciples.solid.lspDemo;

import java.util.List;

/**
 * LSP — Liskov Substitution Principle (Barbara Liskov, 1988).
 *
 * "Derived types must be completely substitutable for their base types."
 *
 * VIOLATION: BadFixedDeposit IS-A BadAccount, but it THROWS on withdraw().
 * Any client that loops over List<BadAccount> and calls withdraw() will
 * crash the moment a fixed deposit slips into the list — even though the
 * type system says it should be fine. The subclass strengthened the
 * precondition ("you can withdraw" → "you can NEVER withdraw"), which
 * violates the contract its parent promised.
 *
 * SOLUTION: Split the hierarchy by BEHAVIOR, not by name. A FixedDeposit
 * is NOT a kind of withdrawable account — give it its own type. Now the
 * type system enforces the contract: only Withdrawable.withdraw() exists.
 */
public class LspDemo {

    public static void main(String[] args) {
        System.out.println("=== LSP — Banking Substitution ===\n");

        // --- VIOLATION ---
        System.out.println("[VIOLATION] Substituting BadFixedDeposit explodes the loop:");
        List<BadAccount> badAccounts = List.of(
            new BadSavingsAccount("A-1", 500),
            new BadFixedDeposit ("A-2", 1000)   // <-- the saboteur
        );
        for (BadAccount a : badAccounts) {
            try {
                a.withdraw(50);
                System.out.println("  " + a.id() + " withdrew 50 -> balance=" + a.balance());
            } catch (UnsupportedOperationException ex) {
                System.out.println("  " + a.id() + " threw: " + ex.getMessage());
            }
        }

        System.out.println();

        // --- SOLUTION ---
        System.out.println("[SOLUTION] Type system enforces the contract — no surprises:");
        List<Withdrawable> withdrawables = List.of(new SavingsAccount("A-1", 500));
        for (Withdrawable w : withdrawables) {
            w.withdraw(50);
            System.out.println("  " + w.id() + " withdrew 50 -> balance=" + w.balance());
        }
        // FixedDeposit lives in its own family — withdraw() does not exist:
        FixedDeposit fd = new FixedDeposit("A-2", 1000);
        System.out.println("  " + fd.id() + " (fixed) accrues interest -> balance=" + fd.accrue(0.05));
        // fd.withdraw(50);   // <-- COMPILER ERROR. The type system kept us safe.
    }
}

/* ─────────────────────────── VIOLATION ─────────────────────────── */

abstract class BadAccount {
    private final String id;
    protected double balance;
    BadAccount(String id, double balance) { this.id = id; this.balance = balance; }
    String id() { return id; }
    double balance() { return balance; }
    abstract void withdraw(double amount);
}

class BadSavingsAccount extends BadAccount {
    BadSavingsAccount(String id, double balance) { super(id, balance); }
    void withdraw(double amount) { balance -= amount; }
}

class BadFixedDeposit extends BadAccount {
    BadFixedDeposit(String id, double balance) { super(id, balance); }
    void withdraw(double amount) {
        // STRENGTHENS the precondition — parent allowed any positive amount.
        throw new UnsupportedOperationException("FixedDeposit cannot be withdrawn from");
    }
}

/* ─────────────────────────── SOLUTION ─────────────────────────── */

interface Account {
    String id();
    double balance();
}

interface Withdrawable extends Account {
    void withdraw(double amount);
}

class SavingsAccount implements Withdrawable {
    private final String id;
    private double balance;
    SavingsAccount(String id, double balance) { this.id = id; this.balance = balance; }
    public String id() { return id; }
    public double balance() { return balance; }
    public void withdraw(double amount) { balance -= amount; }
}

class FixedDeposit implements Account {        // NOT Withdrawable — by design
    private final String id;
    private double balance;
    FixedDeposit(String id, double balance) { this.id = id; this.balance = balance; }
    public String id() { return id; }
    public double balance() { return balance; }
    double accrue(double rate) { balance += balance * rate; return balance; }
}
