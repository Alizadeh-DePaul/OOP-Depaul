package interactiveOopCodes.designPatterns.chainOfResponsibility.atmDispenser;

/**
 * Canonical Chain of Responsibility: ATM Cash Dispenser.
 *
 *   Client (this main) builds a chain $100 -> $50 -> $20 -> $10.
 *   Each handler peels off as many of its denomination as it can,
 *   then forwards the REMAINDER to the next handler.
 *
 * Order matters: denominations descend so the dispenser hands out
 * the fewest possible bills. Reverse the order and you get a wad
 * of singles.
 */
public class AtmDispenser {

    public static void main(String[] args) {
        // Step 1 - instantiate handlers (one per denomination)
        DispenseChain h100 = new Dollar100Dispenser();
        DispenseChain h50  = new Dollar50Dispenser();
        DispenseChain h20  = new Dollar20Dispenser();
        DispenseChain h10  = new Dollar10Dispenser();

        // Step 2 - link them into a chain
        h100.setNext(h50);
        h50.setNext(h20);
        h20.setNext(h10);

        // Step 3 - fire requests through the head of the chain
        int[] requests = { 230, 1000, 70, 10, 5, 73 };
        for (int amount : requests) {
            System.out.println();
            if (amount % 10 != 0 || amount <= 0) {
                System.out.println("-- Rejected $" + amount + " (must be positive multiple of $10).");
                continue;
            }
            System.out.println("-- Withdraw $" + amount + " --");
            h100.dispense(new Currency(amount));
        }
    }
}

abstract class DispenseChain {
    protected DispenseChain next;

    public void setNext(DispenseChain next) {
        this.next = next;
    }

    public abstract void dispense(Currency currency);
}

class Currency {
    private final int amount;
    public Currency(int amount) { this.amount = amount; }
    public int getAmount() { return amount; }
}

class Dollar100Dispenser extends DispenseChain {
    @Override
    public void dispense(Currency c) {
        if (c.getAmount() >= 100) {
            int num = c.getAmount() / 100;
            int rem = c.getAmount() % 100;
            System.out.println("  dispensing " + num + " x $100");
            if (rem > 0 && next != null) next.dispense(new Currency(rem));
        } else if (next != null) {
            next.dispense(c);
        }
    }
}

class Dollar50Dispenser extends DispenseChain {
    @Override
    public void dispense(Currency c) {
        if (c.getAmount() >= 50) {
            int num = c.getAmount() / 50;
            int rem = c.getAmount() % 50;
            System.out.println("  dispensing " + num + " x $50");
            if (rem > 0 && next != null) next.dispense(new Currency(rem));
        } else if (next != null) {
            next.dispense(c);
        }
    }
}

class Dollar20Dispenser extends DispenseChain {
    @Override
    public void dispense(Currency c) {
        if (c.getAmount() >= 20) {
            int num = c.getAmount() / 20;
            int rem = c.getAmount() % 20;
            System.out.println("  dispensing " + num + " x $20");
            if (rem > 0 && next != null) next.dispense(new Currency(rem));
        } else if (next != null) {
            next.dispense(c);
        }
    }
}

class Dollar10Dispenser extends DispenseChain {
    @Override
    public void dispense(Currency c) {
        if (c.getAmount() >= 10) {
            int num = c.getAmount() / 10;
            System.out.println("  dispensing " + num + " x $10");
        } else {
            System.out.println("  unhandled: cannot dispense $" + c.getAmount() + " (end of chain)");
        }
    }
}
