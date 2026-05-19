package interactiveOopCodes.designPatterns.decorator.printerBuffer;

/**
 * Decorator Pattern - Printer Buffer Flusher (Implementation).
 *
 *   ConcretePrinter does the real work: flushBuffer() empties the
 *   print queue. We then layer ConcreteDecorator1 and
 *   ConcreteDecorator2 on top to add LOGGING and FRAMING around
 *   the underlying flush - without touching ConcretePrinter.
 *
 *   The mechanic: AbstractDecorator extends Printer AND holds a
 *   Printer. Each ConcreteDecorator calls super.flushBuffer()
 *   (which forwards to the wrapped printer) and then contributes
 *   its own work before/after.
 */
public class PrinterBuffer {

    public static void main(String[] args) {
        System.out.println("======================================================");
        System.out.println("           Printer Buffer Flusher Demo");
        System.out.println("======================================================");

        // Step 1 - the underlying printer (no decoration)
        Printer base = new ConcretePrinter();

        System.out.println("\n[1] Just the base printer:");
        base.flushBuffer();

        // Step 2 - wrap in ConcreteDecorator1 (adds a trailing log line)
        ConcreteDecorator1 cd1 = new ConcreteDecorator1();
        cd1.selectPrinterToFlush(base);

        System.out.println("\n[2] base wrapped in Decorator1:");
        cd1.flushBuffer();

        // Step 3 - wrap THAT in ConcreteDecorator2 (adds [START]/[END] frame)
        ConcreteDecorator2 cd2 = new ConcreteDecorator2();
        cd2.selectPrinterToFlush(cd1);

        System.out.println("\n[3] base wrapped in Decorator1 wrapped in Decorator2:");
        cd2.flushBuffer();
    }
}

/* Component (abstract) - the shared contract */
abstract class Printer {
    public abstract void flushBuffer();
}

/* ConcreteComponent - the real implementation */
class ConcretePrinter extends Printer {
    @Override
    public void flushBuffer() {
        System.out.println("  ConcretePrinter: actual print queue flushed");
    }
}

/* Decorator (abstract) - IS-A Printer AND HAS-A Printer */
abstract class AbstractDecorator extends Printer {
    protected Printer printer;

    public void selectPrinterToFlush(Printer ptr) {
        this.printer = ptr;
    }

    @Override
    public void flushBuffer() {
        if (printer != null) {
            printer.flushBuffer();
        }
    }
}

/* ConcreteDecorator - adds a trailing log line */
class ConcreteDecorator1 extends AbstractDecorator {
    @Override
    public void flushBuffer() {
        super.flushBuffer();
        System.out.println("  [Decorator1] flush completed - audit log written");
    }
}

/* ConcreteDecorator - wraps the inner flush in a START/END frame */
class ConcreteDecorator2 extends AbstractDecorator {
    @Override
    public void flushBuffer() {
        System.out.println("  [START] Decorator2 frame");
        super.flushBuffer();
        System.out.println("  [END]   Decorator2 frame");
    }
}
