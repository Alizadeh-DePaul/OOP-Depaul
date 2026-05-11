package interactiveOopCodes.designPrinciples.grasp.polymorphismFabricationDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * GRASP — Polymorphism + Pure Fabrication.
 *
 * Two "advanced" principles paired here because both answer: "what if no
 * obvious domain class is a good fit for the responsibility?"
 *
 * POLYMORPHISM: When alternative behavior varies by TYPE, assign the
 * responsibility to each type — never to a switch statement that asks
 * "what type is this?". The Sale should not contain a `switch (payment.type)`
 * — it should call `payment.validate()` and let each subtype answer for
 * itself.
 *
 * PURE FABRICATION: When no domain class is a good fit for a responsibility
 * (or assigning it to one would harm cohesion / coupling), invent a class
 * that doesn't exist in the domain. PersistenceManager, Repository, Mapper,
 * Translator, Strategy — none of these are in the POS domain, but they hold
 * responsibilities cleanly so the domain classes don't have to.
 *
 * WRONG: A type-switching SalePosTerminal computes by inspecting payment
 * tag strings. AND the Sale itself owns the file-IO save logic, because
 * there was no obvious place for it.
 *
 * RIGHT: PaymentMethod is an abstract base; Cash, Credit, Check override
 * validate() / process(). AND a fabricated SaleRepository class owns the
 * save() responsibility — fabricated because no domain entity wanted it,
 * but it's the cohesive home for persistence.
 */
public class PolymorphismFabricationDemo {

    public static void main(String[] args) {
        System.out.println("=== GRASP — Polymorphism + Pure Fabrication ===\n");

        // --- WRONG: type switch + Sale doing persistence ---
        System.out.println("[WRONG] type-switch on payment + Sale owns file IO:");
        BadSale badSale = new BadSale();
        badSale.processPayment("cash",   100);
        badSale.processPayment("credit", 100);
        badSale.processPayment("check",  100);
        badSale.saveToDisk();         // <-- doesn't belong on Sale

        System.out.println();

        // --- RIGHT: polymorphic dispatch + fabricated repository ---
        System.out.println("[RIGHT] polymorphic dispatch + fabricated repository:");
        List<PaymentMethod> tenders = List.of(
            new Cash  (100),
            new Credit(100, "4111-..."),
            new Check (100, "0123")
        );
        for (PaymentMethod m : tenders) {
            if (m.validate()) m.process();
        }
        // Persistence lives on the FABRICATED class — not on Sale.
        Sale realSale = new Sale("S-001");
        new SaleRepository().save(realSale);
    }
}

/* ─────────────────────────── WRONG ─────────────────────────── */

class BadSale {
    private final String id = "S-bad";

    void processPayment(String type, double amount) {        // type-switch SMELL
        if (type.equals("cash")) {
            System.out.println("  cash $" + amount + " accepted");
        } else if (type.equals("credit")) {
            System.out.println("  credit $" + amount + " authorized");
        } else if (type.equals("check")) {
            System.out.println("  check $" + amount + " held for clearing");
        } else {
            throw new IllegalArgumentException("Unknown payment type: " + type);
        }
        // Adding ApplePay = ANOTHER if/else here, in the wrong class.
    }

    void saveToDisk() {                                       // doesn't belong on Sale
        System.out.println("  Sale.saveToDisk writing " + id + ".dat ...");
    }
}

/* ─────────────────────────── RIGHT (Polymorphism) ─────────────────────────── */

abstract class PaymentMethod {
    abstract boolean validate();
    abstract void    process();
}

class Cash extends PaymentMethod {
    final double amount;
    Cash(double a) { amount = a; }
    boolean validate() { return amount > 0; }
    void    process()  { System.out.println("  cash $" + amount + " accepted"); }
}

class Credit extends PaymentMethod {
    final double amount; final String pan;
    Credit(double a, String p) { amount = a; pan = p; }
    boolean validate() { return amount > 0 && pan.length() > 0; }
    void    process()  { System.out.println("  credit $" + amount + " authorized (" + pan + ")"); }
}

class Check extends PaymentMethod {
    final double amount; final String checkNo;
    Check(double a, String n) { amount = a; checkNo = n; }
    boolean validate() { return amount > 0 && !checkNo.isEmpty(); }
    void    process()  { System.out.println("  check #" + checkNo + " $" + amount + " held for clearing"); }
}

/* ─────────────────────────── RIGHT (Pure Fabrication) ─────────────────────────── */

class Sale {
    final String id;
    Sale(String id) { this.id = id; }
    // Sale focuses on sale logic. It does NOT save itself.
}

class SaleRepository {                  // FABRICATED — not in the POS domain
    private final List<Sale> store = new ArrayList<>();
    void save(Sale s) {
        store.add(s);
        System.out.println("  SaleRepository -> saved " + s.id);
    }
}
