package interactiveOopCodes.designPrinciples.grasp.lowCouplingDemo;

/**
 * GRASP — Low Coupling.
 *
 * "Assign responsibilities so the coupling between classes remains low."
 *
 * Coupling is the count and STRENGTH of dependencies a class has on others.
 * Larman ranks coupling from worst to best:
 *     Content > Common > External > Control > Stamp > Data > Message > No
 *
 * WRONG: BadRegister has fields for the database, the email service, the
 * inventory system, AND the receipt printer. Every one of those collaborators
 * is a reason for BadRegister to recompile. Constructing BadRegister in a
 * test forces stubbing four unrelated services.
 *
 * RIGHT: Register holds ONE direct reference (the current Sale). It receives
 * payment as a parameter, hands the receipt-printing to a passed-in printer,
 * and stays ignorant of email, inventory, and the database. The dependency
 * count drops from four down to one — and the one it has is a domain object,
 * not an infrastructure service.
 */
public class LowCouplingDemo {

    public static void main(String[] args) {
        System.out.println("=== GRASP — Low Coupling ===\n");

        // --- WRONG ---
        System.out.println("[WRONG] BadRegister depends on database + email + inventory + printer:");
        BadRegister bad = new BadRegister();
        bad.endSale();

        System.out.println();

        // --- RIGHT ---
        System.out.println("[RIGHT] Register only knows Sale; collaborators are passed in:");
        Register good = new Register();
        good.startSale();
        good.scan("Pen", 1.50);
        good.scan("Notebook", 3.25);
        good.endSale(new ReceiptPrinter());
    }
}

/* ─────────────────────────── WRONG ─────────────────────────── */

class BadRegister {
    private final BadDatabase    db        = new BadDatabase();
    private final BadEmail       email     = new BadEmail();
    private final BadInventory   inventory = new BadInventory();
    private final BadPrinter     printer   = new BadPrinter();
    // 4 direct dependencies on infrastructure — high coupling, low testability.

    void endSale() {
        db.save("sale ended");
        email.notify("manager@store.com", "sale done");
        inventory.decrement("Pen", 3);
        printer.write("RECEIPT");
        System.out.println("  BadRegister wired to db/email/inventory/printer");
    }
}

class BadDatabase  { void save(String s)    { System.out.println("  (db) " + s); } }
class BadEmail     { void notify(String to, String msg) { System.out.println("  (email) " + msg); } }
class BadInventory { void decrement(String item, int n) { System.out.println("  (inv) -" + n + " " + item); } }
class BadPrinter   { void write(String s)   { System.out.println("  (printer) " + s); } }

/* ─────────────────────────── RIGHT ─────────────────────────── */

class Register {
    private Sale currentSale;     // ONE field, and it is a domain object

    void startSale() {
        currentSale = new Sale();
        System.out.println("  register -> new sale");
    }

    void scan(String name, double price) {
        currentSale.add(name, price);
        System.out.println("  register -> scanned " + name);
    }

    void endSale(ReceiptPrinter printer) {                   // printer passed in, not owned
        System.out.println("  register -> total = $" + currentSale.total());
        printer.print(currentSale);
    }
}

class Sale {
    private double total;
    void add(String name, double price) { total += price; }
    double total() { return total; }
}

class ReceiptPrinter {
    void print(Sale s) { System.out.println("  printer -> printing sale, total=$" + s.total()); }
}
