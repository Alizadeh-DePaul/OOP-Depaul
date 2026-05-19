package interactiveOopCodes.designPatterns.decorator.textTransformer;

import java.util.stream.Collectors;

/**
 * Decorator Pattern - Text Transformer chain (Pros & Cons).
 *
 *   PrintText is the Component. PlainPrintText emits the text as-is.
 *   Three decorators transform the text before it prints:
 *     - HexDecorator        appends a hex-encoded view of the text
 *     - AsciiDecorator      appends an ASCII-code view of the text
 *     - AddedTextDecorator  prepends a tag string to the text
 *
 *   This is the "composability" payoff of the pattern - we can
 *   combine the three in any order and get a different result
 *   from each combination. But it ALSO shows the "reduced
 *   readability" cost: trace what happens for
 *
 *       new HexDecorator(new AddedTextDecorator(new PlainPrintText()))
 *
 *   ...without running the program. That nesting is the cost
 *   you trade for the composability.
 */
public class TextTransformer {

    public static void main(String[] args) {
        String input = "Hi";

        System.out.println("-- plain --");
        new PlainPrintText().print(input);

        System.out.println("\n-- AddedText(plain) --");
        new AddedTextDecorator(new PlainPrintText()).print(input);

        System.out.println("\n-- Hex(plain) --");
        new HexDecorator(new PlainPrintText()).print(input);

        System.out.println("\n-- Hex(AddedText(plain)) --");
        new HexDecorator(new AddedTextDecorator(new PlainPrintText())).print(input);

        System.out.println("\n-- AddedText(Hex(plain))  - same decorators, different order --");
        new AddedTextDecorator(new HexDecorator(new PlainPrintText())).print(input);

        System.out.println("\n-- Ascii(Hex(AddedText(plain))) - the full chain --");
        new AsciiDecorator(
            new HexDecorator(
                new AddedTextDecorator(new PlainPrintText()))).print(input);
    }
}

/* Component - the shared printing contract */
interface PrintText {
    void print(String text);
}

/* ConcreteComponent - prints the text as-is */
class PlainPrintText implements PrintText {
    @Override
    public void print(String text) {
        System.out.println("  " + text);
    }
}

/* Decorator (abstract) - IS-A PrintText AND HAS-A PrintText */
abstract class PrintTextDecorator implements PrintText {
    protected final PrintText inner;

    protected PrintTextDecorator(PrintText inner) {
        this.inner = inner;
    }
}

/* ConcreteDecorator - appends a HEX-encoded view */
class HexDecorator extends PrintTextDecorator {
    public HexDecorator(PrintText inner) {
        super(inner);
    }

    @Override
    public void print(String text) {
        String hex = text.chars()
                         .boxed()
                         .map(x -> "0x" + Integer.toHexString(x))
                         .collect(Collectors.joining(" "));
        inner.print(text + "  ->  HEX: " + hex);
    }
}

/* ConcreteDecorator - appends an ASCII-code view */
class AsciiDecorator extends PrintTextDecorator {
    public AsciiDecorator(PrintText inner) {
        super(inner);
    }

    @Override
    public void print(String text) {
        String ascii = text.chars()
                           .boxed()
                           .map(String::valueOf)
                           .collect(Collectors.joining(" "));
        inner.print(text + "  ->  ASCII: " + ascii);
    }
}

/* ConcreteDecorator - prepends a tag string */
class AddedTextDecorator extends PrintTextDecorator {
    public AddedTextDecorator(PrintText inner) {
        super(inner);
    }

    @Override
    public void print(String text) {
        inner.print("[TAG] " + text);
    }
}
