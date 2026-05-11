package interactiveOopCodes.designPrinciples.solid.srpDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * SRP — Single Responsibility Principle.
 *
 * "A class should have one — and only one — reason to change."
 *
 * VIOLATION: BadInvoice mixes three responsibilities:
 *   1. Hold invoice data         (Accounting owns this)
 *   2. Calculate totals          (Accounting owns this)
 *   3. Save to a database        (IT / Persistence owns this)
 *   4. Print / format output     (Marketing / UI owns this)
 *
 * Every stakeholder change (tax rule, DB migration, output format) forces
 * an edit to BadInvoice — and risks breaking the other two concerns.
 *
 * SOLUTION: Split into four collaborating classes, each with ONE
 * reason to change.
 */
public class SrpDemo {

    public static void main(String[] args) {
        System.out.println("=== SRP — Bookstore Invoice Problem ===\n");

        // --- VIOLATION ---
        System.out.println("[VIOLATION] BadInvoice does everything:");
        BadInvoice bad = new BadInvoice("INV-001");
        bad.addLine("Clean Code",            45.00, 1);
        bad.addLine("The Pragmatic Programmer", 39.99, 2);
        bad.calculateTotal();
        bad.saveToDatabase();
        bad.printInvoice();

        System.out.println();

        // --- SOLUTION ---
        System.out.println("[SOLUTION] Each class has one reason to change:");
        Invoice good = new Invoice("INV-002");
        good.addLine("Clean Code",            45.00, 1);
        good.addLine("The Pragmatic Programmer", 39.99, 2);

        double total = new InvoiceCalculator().total(good);
        new InvoicePersistence().save(good, total);
        new InvoicePrinter().print(good, total);
    }
}

/* ─────────────────────────── VIOLATION ─────────────────────────── */

class BadInvoice {
    private final String id;
    private final List<String[]> lines = new ArrayList<>();
    private double total;

    BadInvoice(String id) { this.id = id; }

    void addLine(String title, double price, int qty) {
        lines.add(new String[]{ title, String.valueOf(price), String.valueOf(qty) });
    }

    // Reason #1 to change: pricing / tax rules
    void calculateTotal() {
        total = 0;
        for (String[] l : lines) total += Double.parseDouble(l[1]) * Integer.parseInt(l[2]);
    }

    // Reason #2 to change: database migration, schema change
    void saveToDatabase() {
        System.out.println("  (BAD) BadInvoice → INSERT INTO invoices VALUES('" + id + "', " + total + ")");
    }

    // Reason #3 to change: receipt layout, marketing copy
    void printInvoice() {
        System.out.println("  (BAD) BadInvoice → printing receipt " + id + "  total=$" + total);
    }
}

/* ─────────────────────────── SOLUTION ─────────────────────────── */

class Invoice {
    final String id;
    final List<String[]> lines = new ArrayList<>();

    Invoice(String id) { this.id = id; }

    void addLine(String title, double price, int qty) {
        lines.add(new String[]{ title, String.valueOf(price), String.valueOf(qty) });
    }
}

class InvoiceCalculator {
    // ONLY reason to change: pricing / tax rules
    double total(Invoice inv) {
        double sum = 0;
        for (String[] l : inv.lines) sum += Double.parseDouble(l[1]) * Integer.parseInt(l[2]);
        return sum;
    }
}

class InvoicePersistence {
    // ONLY reason to change: database / storage mechanism
    void save(Invoice inv, double total) {
        System.out.println("  InvoicePersistence → INSERT INTO invoices VALUES('" + inv.id + "', " + total + ")");
    }
}

class InvoicePrinter {
    // ONLY reason to change: receipt format / output channel
    void print(Invoice inv, double total) {
        System.out.println("  InvoicePrinter → " + inv.id + "  lines=" + inv.lines.size() + "  total=$" + total);
    }
}
