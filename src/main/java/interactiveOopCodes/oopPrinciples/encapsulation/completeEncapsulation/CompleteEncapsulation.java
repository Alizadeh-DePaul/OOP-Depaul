package interactiveOopCodes.oopPrinciples.encapsulation.completeEncapsulation;

// Complete Encapsulation — wrapping data + behavior into a single unit
// All fields are private; access only through public methods with validation

class BankAccount {
    private String owner;
    private double balance;
    private int transactionCount;

    public BankAccount(String owner, double initialDeposit) {
        if (initialDeposit < 0) {
            throw new IllegalArgumentException("Initial deposit cannot be negative");
        }
        this.owner = owner;
        this.balance = initialDeposit;
        this.transactionCount = 0;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit must be positive");
        }
        this.balance += amount;
        this.transactionCount++;
        System.out.println("Deposited $" + amount + " → Balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        this.balance -= amount;
        this.transactionCount++;
        System.out.println("Withdrew $" + amount + " → Balance: $" + balance);
    }

    public double getBalance() { return balance; }
    public String getOwner() { return owner; }
    public int getTransactionCount() { return transactionCount; }
    // No setBalance() — balance can ONLY change through deposit/withdraw
    // This is what makes it "complete" encapsulation: controlled access
}

public class CompleteEncapsulation {
    public static void main(String[] args) {
        BankAccount acct = new BankAccount("Alice", 1000);

        // acct.balance = -999;  // Compile error! Field is private
        // acct.balance += 500;  // Compile error! Cannot access directly

        acct.deposit(500);           // Deposited $500.0 → Balance: $1500.0
        acct.withdraw(200);          // Withdrew $200.0 → Balance: $1300.0

        System.out.println("Owner: " + acct.getOwner());
        System.out.println("Balance: $" + acct.getBalance());
        System.out.println("Transactions: " + acct.getTransactionCount());

        // Try invalid operations — encapsulation enforces invariants
        try {
            acct.withdraw(5000);     // IllegalArgumentException: Insufficient funds
        } catch (IllegalArgumentException e) {
            System.out.println("Blocked: " + e.getMessage());
        }

        try {
            acct.deposit(-100);      // IllegalArgumentException: Deposit must be positive
        } catch (IllegalArgumentException e) {
            System.out.println("Blocked: " + e.getMessage());
        }
    }
}
