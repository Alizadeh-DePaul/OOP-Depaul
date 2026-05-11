package interactiveOopCodes.designPrinciples.couplingCohesion.cohesionLadderDemo;

/**
 * Cohesion Ladder — 7 levels from worst (Coincidental) to best (Functional).
 *
 * Ordering, Stevens/Myers/Constantine 1974:
 *   Coincidental < Logical < Temporal < Procedural < Communicational < Sequential < Functional
 *   worst                                                                              best
 *
 * A class is COHESIVE to the extent that its members collaborate on ONE task.
 * Each section here shows ONE class whose internal structure sits at that
 * rung of the ladder.
 */
public class CohesionLadderDemo {

    public static void main(String[] args) {
        System.out.println("=== Cohesion Ladder (worst -> best) ===\n");
        coincidental();
        logical();
        temporal();
        procedural();
        communicational();
        sequential();
        functional();
    }

    /* L7 — COINCIDENTAL: members are bundled at random. */
    static void coincidental() {
        System.out.println("[L7 Coincidental] members have NO meaningful relationship:");
        UtilsC7 u = new UtilsC7();
        u.printDate();
        u.computeSqrt(16);
        u.sendEmail("a@b.c");
        System.out.println("  -> 'Misc utilities' — pure junk-drawer class. AVOID.\n");
    }

    /* L6 — LOGICAL: same category, but each branch is unrelated. */
    static void logical() {
        System.out.println("[L6 Logical] members categorically similar but unrelated in execution:");
        ValidatorC6 v = new ValidatorC6();
        v.validate(1, "abc");                            // string mode
        v.validate(2, 42);                               // int mode
        v.validate(3, true);                             // bool mode
        System.out.println("  -> 'All validations' but each branch is its own thing. BAD.\n");
    }

    /* L5 — TEMPORAL: members run at the same time, but do unrelated work. */
    static void temporal() {
        System.out.println("[L5 Temporal] members run at the same TIME but unrelated WORK:");
        StartupC5.boot();
        System.out.println("  -> 'Things to do at boot' — same moment, different jobs. POOR.\n");
    }

    /* L4 — PROCEDURAL: members must run in order, but on different data. */
    static void procedural() {
        System.out.println("[L4 Procedural] members must run in order, but touch different data:");
        ReportC4 r = new ReportC4();
        r.fetchFromDb();
        r.formatHtml();
        r.emailToManager();
        System.out.println("  -> Sequence matters; the data differs at each step. MEH.\n");
    }

    /* L3 — COMMUNICATIONAL: members operate on the SAME data. */
    static void communicational() {
        System.out.println("[L3 Communicational] members operate on the SAME data:");
        SaleC3 s = new SaleC3();
        s.recordItem();
        s.applyTax();
        s.printReceipt();
        System.out.println("  -> All methods read/write the same Sale. GOOD.\n");
    }

    /* L2 — SEQUENTIAL: output of one is input of the next. */
    static void sequential() {
        System.out.println("[L2 Sequential] output of one is the input of the next:");
        PipelineC2 p = new PipelineC2();
        String result = p.parse(p.normalize(p.read("input.txt")));
        System.out.println("  pipeline result: " + result);
        System.out.println("  -> Linear data flow — VERY GOOD.\n");
    }

    /* L1 — FUNCTIONAL: every member contributes to ONE task. */
    static void functional() {
        System.out.println("[L1 Functional] every member contributes to ONE task:");
        double price = new PriceCalculatorC1().compute(10.0, 0.07, 0.05);
        System.out.println("  computed price = " + price);
        System.out.println("  -> One purpose, one verb. IDEAL.\n");
    }
}

/* ─── C7 (Coincidental) ─── */
class UtilsC7 {
    void printDate()             { System.out.println("  UtilsC7: 2026-05-11"); }
    void computeSqrt(int n)      { System.out.println("  UtilsC7: sqrt(" + n + ") = " + Math.sqrt(n)); }
    void sendEmail(String addr)  { System.out.println("  UtilsC7: mailing " + addr); }
}

/* ─── C6 (Logical) ─── */
class ValidatorC6 {
    boolean validate(int kind, Object data) {
        switch (kind) {                                  // type-switch — three unrelated branches
            case 1:
                System.out.println("  ValidatorC6: as String  -> " + data);
                return data instanceof String;
            case 2:
                System.out.println("  ValidatorC6: as Integer -> " + data);
                return data instanceof Integer;
            case 3:
                System.out.println("  ValidatorC6: as Boolean -> " + data);
                return data instanceof Boolean;
            default:
                return false;
        }
    }
}

/* ─── C5 (Temporal) ─── */
class StartupC5 {
    static void boot() {
        // All these run "at boot" — same moment, no shared data, no shared purpose.
        System.out.println("  StartupC5: open log file");
        System.out.println("  StartupC5: warm up caches");
        System.out.println("  StartupC5: greet the user");
        System.out.println("  StartupC5: read config");
    }
}

/* ─── C4 (Procedural) ─── */
class ReportC4 {
    void fetchFromDb()    { System.out.println("  ReportC4: fetch rows"); }
    void formatHtml()     { System.out.println("  ReportC4: format HTML"); }
    void emailToManager() { System.out.println("  ReportC4: email out"); }
}

/* ─── C3 (Communicational) ─── */
class SaleC3 {
    private double subtotal = 0;
    void recordItem()  { subtotal += 10;       System.out.println("  SaleC3 subtotal: " + subtotal); }
    void applyTax()    { subtotal *= 1.07;     System.out.println("  SaleC3 +tax:     " + subtotal); }
    void printReceipt(){                       System.out.println("  SaleC3 receipt:  $" + subtotal); }
}

/* ─── C2 (Sequential) ─── */
class PipelineC2 {
    String read(String path)      { System.out.println("  read " + path);  return "raw bytes"; }
    String normalize(String text) { System.out.println("  normalize");     return text.trim(); }
    String parse(String text)     { System.out.println("  parse");         return "[parsed:" + text + "]"; }
}

/* ─── C1 (Functional) ─── */
class PriceCalculatorC1 {
    double compute(double base, double taxRate, double discountRate) {
        double afterDiscount = base * (1 - discountRate);
        double withTax       = afterDiscount * (1 + taxRate);
        return Math.round(withTax * 100.0) / 100.0;
    }
}
