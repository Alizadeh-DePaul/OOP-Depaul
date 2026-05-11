package interactiveOopCodes.designPrinciples.couplingCohesion.synthesisDemo;

/**
 * Synthesis — every SOLID/GRASP principle is a MOVE in
 * (cohesion, coupling) space.
 *
 * Four stages of the SAME Report concept, each at a different coordinate:
 *
 *   Stage 1: BadReport         — (Coincidental cohesion, Common coupling)        worst corner
 *   Stage 2: ProceduralReport  — (Procedural,            Stamp)                   one rung up
 *   Stage 3: SaleReport        — (Communicational,       Data)                    two more rungs
 *   Stage 4: Reporter family   — (Functional,            Message + DIP)           best corner
 *
 * Each stage compiles and runs; each TRANSITION names the principle that
 * drove the move.
 */
public class SynthesisDemo {

    public static void main(String[] args) {
        System.out.println("=== Synthesis: principles as moves in (cohesion, coupling) space ===\n");

        // ── Stage 1: Coincidental cohesion + Common coupling (worst) ──
        System.out.println("[1] BadReport — Coincidental cohesion, Common coupling:");
        BadReport.run();

        // ── Stage 2: SRP splits unrelated work; struct passed in (Stamp).
        System.out.println("\n[2] ProceduralReport — SRP -> Procedural cohesion, Stamp coupling:");
        new ProceduralReport().run(new SaleData(100, 0.07, "Vahid"));

        // ── Stage 3: Information Expert moves total() onto the data owner.
        System.out.println("\n[3] SaleReport — Information Expert -> Communicational cohesion, Data coupling:");
        SaleReport sr = new SaleReport(100, 0.07);
        sr.print("Vahid");

        // ── Stage 4: DIP + Polymorphism inverts the dependency.
        System.out.println("\n[4] Reporter family — DIP + Polymorphism -> Functional cohesion, Message coupling:");
        Sale sale = new Sale(100, 0.07);
        ReportWriter writer = new TextReportWriter();    // swap for any implementation
        new Reporter(writer).reportOn(sale);

        System.out.println("\nThe climb: worst corner -> best corner is ONE refactor at a time,");
        System.out.println("each driven by a NAMED principle from SOLID or GRASP.");
    }
}

/* ═══════════════════════════════════════════════════════════════════
   STAGE 1: BadReport
   Cohesion: Coincidental  (methods are unrelated to each other)
   Coupling: Common        (touches global Settings + global counter)
   ═══════════════════════════════════════════════════════════════════ */

class Settings {                                         // shared mutable global
    static double globalTaxRate = 0.07;
    static int    reportsRun    = 0;
}

class BadReport {
    static void run() {
        Settings.reportsRun++;                           // Common coupling
        System.out.println("  BadReport: today's date is 2026-05-11");
        System.out.println("  BadReport: greet the user");
        double tax = 100 * Settings.globalTaxRate;       // Common coupling, again
        System.out.println("  BadReport: total with tax = " + (100 + tax));
        System.out.println("  BadReport: reports so far = " + Settings.reportsRun);
    }
}

/* ═══════════════════════════════════════════════════════════════════
   STAGE 2: ProceduralReport
   Applied principle:  SRP — split unrelated work out of BadReport
   Cohesion: Procedural    (methods run in order, on different fields)
   Coupling: Stamp         (takes the whole SaleData, uses subsets)
   ═══════════════════════════════════════════════════════════════════ */

class SaleData {
    double price;
    double taxRate;
    String customer;
    SaleData(double p, double r, String c) { price = p; taxRate = r; customer = c; }
}

class ProceduralReport {
    void run(SaleData data) {                            // STAMP — sees all of SaleData
        format(data);                                    // uses customer only
        compute(data);                                   // uses price + taxRate only
        emit(data);                                      // uses customer again
    }
    private void format(SaleData d)  { System.out.println("  ProceduralReport: header for " + d.customer); }
    private void compute(SaleData d) { System.out.println("  ProceduralReport: total = " + (d.price * (1 + d.taxRate))); }
    private void emit(SaleData d)    { System.out.println("  ProceduralReport: emit row for " + d.customer); }
}

/* ═══════════════════════════════════════════════════════════════════
   STAGE 3: SaleReport
   Applied principle:  Information Expert (GRASP) — give totals to the data owner
   Cohesion: Communicational (every method operates on the same Sale data)
   Coupling: Data            (constructor takes primitives only)
   ═══════════════════════════════════════════════════════════════════ */

class SaleReport {
    private final double price;
    private final double taxRate;
    SaleReport(double price, double taxRate) {           // DATA coupling — primitives
        this.price   = price;
        this.taxRate = taxRate;
    }
    void print(String customer) {
        System.out.println("  SaleReport: customer = " + customer);
        System.out.println("  SaleReport: total    = " + total());
    }
    private double total() { return price * (1 + taxRate); }
}

/* ═══════════════════════════════════════════════════════════════════
   STAGE 4: Reporter / Sale / ReportWriter
   Applied principles: DIP + Polymorphism — depend on an abstraction
   Cohesion: Functional    (each class has ONE clear job)
   Coupling: Message       (Reporter sends a message; never reads internals)
   ═══════════════════════════════════════════════════════════════════ */

class Sale {                                             // Information Expert
    private final double price;
    private final double taxRate;
    Sale(double price, double taxRate) { this.price = price; this.taxRate = taxRate; }
    double total() { return price * (1 + taxRate); }     // owns the data, owns the math
}

interface ReportWriter {                                 // DIP — depend on abstraction
    void write(String header, double total);
}

class TextReportWriter implements ReportWriter {
    public void write(String header, double total) {
        System.out.println("  TextReport: " + header + " | total = " + total);
    }
}

class Reporter {
    private final ReportWriter writer;                   // injected — Message coupling
    Reporter(ReportWriter w) { writer = w; }
    void reportOn(Sale sale) {
        writer.write("SALE", sale.total());              // never touches sale's internals
    }
}
