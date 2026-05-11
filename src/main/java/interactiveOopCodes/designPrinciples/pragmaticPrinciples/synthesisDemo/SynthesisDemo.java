package interactiveOopCodes.designPrinciples.pragmaticPrinciples.synthesisDemo;

import java.util.HashMap;
import java.util.Map;

/**
 * Synthesis — all five pragmatic principles in one place.
 *
 * Each is a small move. Together they cash out as low coupling,
 * high cohesion, and readable code — the same goals SOLID and GRASP
 * encode under their formal names.
 *
 *   Stage 1: BadReport       — DRY, KISS, YAGNI, CoI, LoD all violated
 *   Stage 2: MidReport       — DRY + KISS + YAGNI fixed
 *   Stage 3: GoodReport     — CoI fixed (delegate), LoD fixed (tell, don't ask)
 *
 * Each transition names the principle that drove the move.
 */
public class SynthesisDemo {

    public static void main(String[] args) {
        System.out.println("[1] BadReport — all five violated:");
        new BadReport().run(new HashMap<>(Map.of("verbose", Boolean.TRUE)));

        System.out.println("\n[2] MidReport — DRY + KISS + YAGNI fixed:");
        new MidReport().run(new Sale(100, 0.07));

        System.out.println("\n[3] GoodReport — CoI + LoD also fixed:");
        new GoodReport(new TextWriter()).reportOn(new Sale(100, 0.07));
    }
}

/* ─── Stage 1: every smell at once ─────────────────────────────────── */

class BadReport extends HashMap<String, Object> {              // CoI: IS-A HashMap?!
    void run(Map<String, Object> options) {
        // KISS: nested ternary, YAGNI: ignored options bag
        String header = options.containsKey("verbose")
            ? (Boolean.TRUE.equals(options.get("verbose")) ? "VERBOSE" : "QUIET")
            : "QUIET";
        // DRY: same "non-blank" check inlined twice
        if (header == null || header.trim().isEmpty()) return;
        if ("hdr".trim().isEmpty())                     return;
        Sale s = new Sale(100, 0.07);
        // LoD: train wreck through Sale's internals
        double total = s.getPrice() * (1 + s.getTaxRate());
        System.out.println("  Bad: " + header + " total=" + total);
    }
}

/* ─── Stage 2: DRY + KISS + YAGNI fixed; CoI + LoD still pending ──── */

class MidReport {
    void run(Sale s) {
        double total = s.getPrice() * (1 + s.getTaxRate());    // LoD still violated
        System.out.println("  Mid: total=" + total);
    }
}

/* ─── Stage 3: CoI fixed via composition; LoD fixed via tell-don't-ask ─ */

class Sale {
    private final double price;
    private final double taxRate;
    Sale(double price, double taxRate) { this.price = price; this.taxRate = taxRate; }
    double getPrice()   { return price; }       // kept so MidReport compiles
    double getTaxRate() { return taxRate; }
    double total()      { return price * (1 + taxRate); }   // Information Expert
}

interface ReportWriter { void write(double total); }

class TextWriter implements ReportWriter {
    public void write(double total) { System.out.println("  Good: total=" + total); }
}

class GoodReport {
    private final ReportWriter writer;                       // composition, not extends
    GoodReport(ReportWriter w) { writer = w; }
    void reportOn(Sale sale) { writer.write(sale.total()); } // tell, don't ask
}
