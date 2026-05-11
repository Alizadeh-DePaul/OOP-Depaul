package interactiveOopCodes.designPrinciples.grasp.highCohesionDemo;

/**
 * GRASP — High Cohesion.
 *
 * "Assign responsibilities so the cohesion of each class remains high."
 *
 * Cohesion is how RELATED a class's responsibilities are to each other.
 * Larman / Yourdon rank cohesion worst-to-best:
 *     Coincidental < Logical < Temporal < Procedural <
 *     Communicational < Sequential < Functional
 *
 * WRONG: BadRegister has methods for sales, payment authorization, receipt
 * printing, daily reporting, and email notifications. The methods rarely
 * touch each other's fields — they're glued together only because someone
 * needed "register stuff". This is LOGICAL or COINCIDENTAL cohesion: the
 * class name is a category label, not a focus.
 *
 * RIGHT: Register handles ONLY register-shaped responsibilities — owning
 * the current sale and orchestrating its life cycle. Payment, printing,
 * and reporting are delegated to specialists (PaymentProcessor,
 * ReceiptPrinter, SalesReport). Each class has FUNCTIONAL cohesion: every
 * method serves the same single, focused purpose.
 */
public class HighCohesionDemo {

    public static void main(String[] args) {
        System.out.println("=== GRASP — High Cohesion ===\n");

        // --- WRONG ---
        System.out.println("[WRONG] BadRegister does sales + payment + printing + reporting + email:");
        BadRegister bad = new BadRegister();
        bad.startSale();
        bad.scanItem("Pen", 1.50);
        bad.authorizeCreditCard("4111-1111-1111-1111", 1.50);
        bad.printReceipt();
        bad.runDailyReport();
        bad.emailManager();

        System.out.println();

        // --- RIGHT ---
        System.out.println("[RIGHT] Register focused; collaborates with focused specialists:");
        Register good = new Register(new PaymentProcessor(), new ReceiptPrinter());
        good.startSale();
        good.scanItem("Pen", 1.50);
        good.endSale(new CreditPayment("4111-1111-1111-1111", 1.50));
    }
}

/* ─────────────────────────── WRONG ─────────────────────────── */

class BadRegister {                       // god class: low cohesion
    private double saleTotal;

    void startSale()                            { saleTotal = 0;  System.out.println("  startSale"); }
    void scanItem(String n, double p)           { saleTotal += p; System.out.println("  scanned " + n); }
    void authorizeCreditCard(String pan, double amount) { System.out.println("  card " + pan + " authorized"); }
    void printReceipt()                         { System.out.println("  receipt: ESC/POS bytes 0x1B 0x40..."); }
    void runDailyReport()                       { System.out.println("  daily report: SELECT SUM(total) FROM sales..."); }
    void emailManager()                         { System.out.println("  email: SMTP HELO mail.example.com..."); }
    // None of these methods share much state. The class name is a label, not a focus.
}

/* ─────────────────────────── RIGHT ─────────────────────────── */

class Register {                          // functional cohesion: register life-cycle only
    private Sale currentSale;
    private final PaymentProcessor processor;
    private final ReceiptPrinter printer;

    Register(PaymentProcessor proc, ReceiptPrinter prt) {
        processor = proc; printer = prt;
    }

    void startSale() {
        currentSale = new Sale();
        System.out.println("  register -> new sale");
    }

    void scanItem(String name, double price) {
        currentSale.add(name, price);
        System.out.println("  register -> scanned " + name);
    }

    void endSale(Payment payment) {
        processor.process(payment, currentSale.total());
        printer.print(currentSale);
    }
}

class Sale {
    private double total;
    void add(String name, double price) { total += price; }
    double total() { return total; }
}

class PaymentProcessor {                  // does exactly one thing
    void process(Payment p, double amount) {
        System.out.println("  payment -> " + p.method() + " $" + amount);
    }
}

class ReceiptPrinter {                    // does exactly one thing
    void print(Sale s) {
        System.out.println("  printer -> receipt for sale, total=$" + s.total());
    }
}

abstract class Payment {
    abstract String method();
}

class CreditPayment extends Payment {
    private final String pan;
    private final double amount;
    CreditPayment(String pan, double amount) { this.pan = pan; this.amount = amount; }
    String method() { return "credit " + pan.substring(0, 4) + "..."; }
}
