package interactiveOopCodes.designPatterns.composite.financialAccounts;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern — Item 5: REAL-WORLD USE CASE.
 *
 * Banks bundle accounts together. A customer's portfolio might contain
 * checking accounts, savings accounts, and OTHER PORTFOLIOS (joint
 * holdings with a spouse, for example, which themselves nest). The
 * recursive sum-up — "give me the total balance across everything" —
 * is the Composite pattern's signature operation.
 *
 *   AccountComponent     — Component (interface)
 *   DepositAccount/      \
 *   SavingsAccount       — Leaves
 *   CompositeAccount     — Composite (holds AccountComponent children)
 *
 * The Client never asks "is this one account or a portfolio?". It just
 * calls .getBalance() and lets the recursion roll up the answer.
 *
 * This same shape lives inside the JDK:
 *   - java.io.File (a File can be a leaf-file or a directory of Files)
 *   - javax.swing.JComponent / java.awt.Container (a Container holds
 *     Components, which may themselves be Containers)
 */
public class FinancialAccounts {

    public static void main(String[] args) {
        // --- Customer Alice's portfolio ---
        CompositeAccount alicePortfolio = new CompositeAccount("Alice's portfolio");
        alicePortfolio.add(new DepositAccount("DA-100",   1_200.50f));
        alicePortfolio.add(new DepositAccount("DA-101",   3_400.00f));
        alicePortfolio.add(new SavingsAccount("SA-102",  18_750.25f));

        System.out.println("--- Single portfolio (1 level deep) ---");
        System.out.printf("Alice's total: $%.2f%n", alicePortfolio.getBalance());
        alicePortfolio.printStructure(0);

        // --- Alice + Bob joint account, nested inside a household portfolio ---
        CompositeAccount jointWithBob = new CompositeAccount("Alice+Bob joint");
        jointWithBob.add(new DepositAccount("DA-200",  5_000.00f));
        jointWithBob.add(new SavingsAccount("SA-201", 12_000.00f));

        CompositeAccount household = new CompositeAccount("Household total");
        household.add(alicePortfolio);                  // a composite child!
        household.add(jointWithBob);                    // another composite child
        household.add(new DepositAccount("DA-999", 250.00f));  // a leaf sibling

        System.out.println("\n--- Nested portfolio (3 levels deep) ---");
        System.out.printf("Household total: $%.2f%n", household.getBalance());
        household.printStructure(0);

        // Uniform treatment — same method on a leaf, same answer shape.
        System.out.println("\n--- The SAME .getBalance() on a single Leaf ---");
        AccountComponent solo = new DepositAccount("DA-SOLO", 42.00f);
        System.out.printf("solo.getBalance() = $%.2f%n", solo.getBalance());
    }
}

/* ───────────────────────── Component ───────────────────────── */

/** Component — every account answers .getBalance() and .printStructure(). */
abstract class AccountComponent {
    abstract float getBalance();
    abstract void  printStructure(int indent);
    abstract String getId();
}

/* ───────────────────────── Leaves ───────────────────────── */

class DepositAccount extends AccountComponent {
    private final String accountNo;
    private final float  balance;

    DepositAccount(String accountNo, float balance) {
        this.accountNo = accountNo;
        this.balance   = balance;
    }

    @Override float  getBalance()  { return balance; }
    @Override String getId()       { return accountNo; }
    @Override void   printStructure(int indent) {
        System.out.printf("%s● Deposit %s   $%,.2f%n",
                "  ".repeat(indent), accountNo, balance);
    }
}

class SavingsAccount extends AccountComponent {
    private final String accountNo;
    private final float  balance;

    SavingsAccount(String accountNo, float balance) {
        this.accountNo = accountNo;
        this.balance   = balance;
    }

    @Override float  getBalance()  { return balance; }
    @Override String getId()       { return accountNo; }
    @Override void   printStructure(int indent) {
        System.out.printf("%s● Savings %s   $%,.2f%n",
                "  ".repeat(indent), accountNo, balance);
    }
}

/* ───────────────────────── Composite ───────────────────────── */

/**
 * A CompositeAccount IS A AccountComponent (uniform with leaves) and HAS A
 * list of child AccountComponents (which may themselves be Composites).
 *
 * getBalance() is the textbook recursive roll-up: zero, then accumulate
 * by polymorphic forwarding. Children that are leaves return their own
 * balance; children that are composites recurse one more level.
 */
class CompositeAccount extends AccountComponent {

    private final String label;
    private final List<AccountComponent> children = new ArrayList<>();

    public CompositeAccount(String label) { this.label = label; }

    public void add(AccountComponent c)    { children.add(c); }
    public void remove(AccountComponent c) { children.remove(c); }

    @Override
    float getBalance() {
        float total = 0f;
        for (AccountComponent c : children) {
            total += c.getBalance();        // ← the recursion
        }
        return total;
    }

    @Override
    String getId() { return label; }

    @Override
    void printStructure(int indent) {
        System.out.printf("%s▼ %s   ($%,.2f total)%n",
                "  ".repeat(indent), label, getBalance());
        for (AccountComponent c : children) c.printStructure(indent + 1);
    }
}
