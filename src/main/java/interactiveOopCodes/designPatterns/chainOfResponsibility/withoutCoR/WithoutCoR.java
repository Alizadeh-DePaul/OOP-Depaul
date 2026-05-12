package interactiveOopCodes.designPatterns.chainOfResponsibility.withoutCoR;

/**
 * ANTI-PATTERN: ATM dispenser without Chain of Responsibility.
 *
 * Everything lives in one method:
 *   * adding a new denomination = editing this method (breaks OCP)
 *   * logic is duplicated four times (4x copy-paste of the same shape)
 *   * cannot reorder/disable denominations without rewriting
 *   * cannot reuse a denomination handler somewhere else
 *
 * Compare against the atmDispenser package next door - same behavior,
 * but each handler is an independent class wired into a chain.
 */
public class WithoutCoR {

    public static void dispense(int amount) {
        System.out.println();
        if (amount % 10 != 0 || amount <= 0) {
            System.out.println("-- Rejected $" + amount + " --");
            return;
        }
        System.out.println("-- Withdraw $" + amount + " --");

        if (amount >= 100) {
            int num = amount / 100;
            amount = amount % 100;
            System.out.println("  dispensing " + num + " x $100");
        }
        if (amount >= 50) {
            int num = amount / 50;
            amount = amount % 50;
            System.out.println("  dispensing " + num + " x $50");
        }
        if (amount >= 20) {
            int num = amount / 20;
            amount = amount % 20;
            System.out.println("  dispensing " + num + " x $20");
        }
        if (amount >= 10) {
            int num = amount / 10;
            amount = amount % 10;
            System.out.println("  dispensing " + num + " x $10");
        }
        // To support $5 bills you must edit THIS method.
        // To swap order (try $50 before $100) you must edit THIS method.
        // To disable $20 dispensing you must add a flag and edit THIS method.
    }

    public static void main(String[] args) {
        dispense(230);
        dispense(1000);
        dispense(70);
        dispense(5);
    }
}
