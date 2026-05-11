package interactiveOopCodes.designPrinciples.couplingCohesion.couplingLadderDemo;

import java.lang.reflect.Field;

/**
 * Coupling Ladder — 7 levels from worst (Content) to best (No coupling).
 *
 * Ordering, Stevens/Myers/Constantine 1974 (refined by Yourdon/Constantine):
 *   Content  >  Common  >  Control  >  Stamp  >  Data  >  Message  >  No coupling
 *   worst                                                                best
 *
 * Each section shows a tiny pair: a module B and a caller A whose
 * relationship sits at exactly that rung of the ladder.
 *
 * Run main() and read the comments to walk the ladder top-to-bottom.
 */
public class CouplingLadderDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Coupling Ladder (worst -> best) ===\n");
        contentCoupling();
        commonCoupling();
        controlCoupling();
        stampCoupling();
        dataCoupling();
        messageCoupling();
        noCoupling();
    }

    /* L7 — CONTENT (worst): A reaches INTO B and modifies private state. */
    static void contentCoupling() throws Exception {
        System.out.println("[L7 Content] caller modifies B's private field via reflection:");
        BoxL7 b = new BoxL7(0);
        Field f = BoxL7.class.getDeclaredField("value"); // private — wall is being broken
        f.setAccessible(true);
        f.setInt(b, 99);                                 // *modifying* B's internals
        System.out.println("  b.read() = " + b.read());
        System.out.println("  -> A and B can never evolve independently. AVOID.\n");
    }

    /* L6 — COMMON: A and B both touch a shared mutable global. */
    static void commonCoupling() {
        System.out.println("[L6 Common] caller and B both touch a global:");
        GlobalsL6.counter = 0;
        new WriterL6().increment();
        new ReaderL6().read();
        System.out.println("  -> Any change to the global ripples to every toucher. AVOID.\n");
    }

    /* L5 — CONTROL: A passes a flag that tells B which branch to run. */
    static void controlCoupling() {
        System.out.println("[L5 Control] caller toggles B's logic via a boolean flag:");
        ServiceL5 s = new ServiceL5();
        s.process(true);                                 // flag = "do this branch"
        s.process(false);                                // same method, different behavior
        System.out.println("  -> Caller must KNOW the internals to pick the flag. Smelly.\n");
    }

    /* L4 — STAMP: B receives a composite struct but only uses part of it. */
    static void stampCoupling() {
        System.out.println("[L4 Stamp] B takes an Order but only reads two of four fields:");
        OrderL4 order = new OrderL4(10, 5, "Customer X", "Address Y");
        PrinterL4.printReceipt(order);                   // overshare
        System.out.println("  -> If Order's unused fields change, PrinterL4 may still rebuild. Acceptable.\n");
    }

    /* L3 — DATA: B receives only the primitive(s) it actually needs. */
    static void dataCoupling() {
        System.out.println("[L3 Data] B takes the total only:");
        DataPrinter.printReceipt(50.0);                  // exactly what's needed, nothing else
        System.out.println("  -> Minimal interface, easy to swap. GOOD.\n");
    }

    /* L2 — MESSAGE: A asks B by sending it a message; A holds NO data. */
    static void messageCoupling() {
        System.out.println("[L2 Message] caller sends a behavior request (no shared data):");
        new SaleL2().close();                            // "close yourself" — B decides everything
        System.out.println("  -> A has no idea HOW B closes. VERY GOOD.\n");
    }

    /* L1 — NO COUPLING: A and B never refer to each other directly. */
    static void noCoupling() {
        System.out.println("[L1 No coupling] independent modules:");
        String formatted = new FormatterL1().format("hello");
        System.out.println("  formatted = " + formatted);
        new LoggerL1().log("world");
        System.out.println("  -> Each module evolves without affecting the other. IDEAL.\n");
    }
}

/* ─── L7 helpers (Content) ─── */
class BoxL7 {
    private int value;                                   // PRIVATE — but reflection breaks the wall
    BoxL7(int v) { value = v; }
    int read() { return value; }
}

/* ─── L6 helpers (Common) ─── */
class GlobalsL6 { static int counter = 0; }
class WriterL6 {
    void increment() {
        GlobalsL6.counter++;
        System.out.println("  WriterL6 incremented global to " + GlobalsL6.counter);
    }
}
class ReaderL6 {
    void read() {
        System.out.println("  ReaderL6 sees global = " + GlobalsL6.counter);
    }
}

/* ─── L5 helpers (Control) ─── */
class ServiceL5 {
    void process(boolean fast) {
        if (fast) System.out.println("  ServiceL5: fast path");
        else      System.out.println("  ServiceL5: slow path with extra logging");
    }
}

/* ─── L4 helpers (Stamp) ─── */
class OrderL4 {
    double price;
    int    qty;
    String customer;
    String address;
    OrderL4(double p, int q, String c, String a) {
        price = p; qty = q; customer = c; address = a;
    }
}
class PrinterL4 {
    static void printReceipt(OrderL4 o) {
        // Only uses price + qty. Doesn't care about customer/address.
        System.out.println("  PrinterL4: total = " + (o.price * o.qty));
    }
}

/* ─── L3 helpers (Data) ─── */
class DataPrinter {
    static void printReceipt(double total) {
        System.out.println("  DataPrinter: total = " + total);  // exactly what is needed
    }
}

/* ─── L2 helpers (Message) ─── */
class SaleL2 {
    void close() {
        System.out.println("  SaleL2: I'll close myself, thanks");
    }
}

/* ─── L1 helpers (No coupling) ─── */
class FormatterL1 {
    String format(String s) { return "[" + s + "]"; }
}
class LoggerL1 {
    void log(String s) { System.out.println("  LoggerL1: " + s); }
}
