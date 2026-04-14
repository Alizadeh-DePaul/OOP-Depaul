package interactiveOopCodes.fundamentals.oopBuildingBlocks.getterSetterDemo;

public class GetterSetterDemo {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("Alice", 1000);

        // Getter — read private data
        System.out.println("Owner: " + acc.getOwner());
        System.out.println("Balance: $" + acc.getBalance());

        // Setter with validation
        acc.deposit(500);
        System.out.println("After deposit: $" + acc.getBalance());

        // Invalid operations caught by validation
        acc.deposit(-100);  // Prints: Invalid deposit amount
        acc.withdraw(2000); // Prints: Insufficient funds
    }
}

class BankAccount {
    private String owner;
    private double balance;

    BankAccount(String owner, double initialBalance) {
        this.owner = owner;
        this.balance = initialBalance;
    }

    // Getter — simply returns the value
    String getOwner() { return owner; }
    double getBalance() { return balance; }

    // Setter with validation — controls HOW data changes
    void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount");
            return;
        }
        balance += amount;
    }

    void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds");
            return;
        }
        balance -= amount;
    }
}
