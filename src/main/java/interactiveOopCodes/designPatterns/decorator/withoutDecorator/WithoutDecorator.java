package interactiveOopCodes.designPatterns.decorator.withoutDecorator;

/**
 * The PROBLEM that motivates the Decorator pattern.
 *
 *   You want a "TextPrinter" with three optional embellishments:
 *     - Bold
 *     - Italic
 *     - Underline
 *
 *   Trying to model the combinations through INHERITANCE forces
 *   one subclass per combination = 2 ^ 3 = 8 subclasses, just
 *   for three flags. Add a fourth (Strikethrough) and you double
 *   it to 16. The math is brutal: a feature-flag explosion.
 *
 *   Worse: every flag combination is locked at COMPILE TIME.
 *   You cannot decide "make it bold and italic" at runtime
 *   without instantiating the matching subclass yourself.
 *
 *   The Decorator pattern collapses this 2^N tree into
 *   N decorators that compose at runtime.
 */
public class WithoutDecorator {

    public static void main(String[] args) {
        System.out.println("Combinations possible via subclassing (2^3 = 8):\n");

        TextPrinter[] all = {
            new PlainPrinter(),
            new BoldPrinter(),
            new ItalicPrinter(),
            new UnderlinePrinter(),
            new BoldItalicPrinter(),
            new BoldUnderlinePrinter(),
            new ItalicUnderlinePrinter(),
            new BoldItalicUnderlinePrinter()
        };

        for (TextPrinter p : all) {
            p.print("Hello");
        }

        System.out.println("\n-- Add 'Strikethrough' -> 16 subclasses needed --");
        System.out.println("-- Add a 5th flag        -> 32 subclasses --");
        System.out.println("-- Add a 6th flag        -> 64 subclasses --");
        System.out.println("\nDecorator collapses this to N decorators that compose at runtime.");
    }
}

abstract class TextPrinter {
    public abstract void print(String text);
}

class PlainPrinter extends TextPrinter {
    @Override
    public void print(String text) { System.out.println(text); }
}

class BoldPrinter extends TextPrinter {
    @Override
    public void print(String text) { System.out.println("**" + text + "**"); }
}

class ItalicPrinter extends TextPrinter {
    @Override
    public void print(String text) { System.out.println("*" + text + "*"); }
}

class UnderlinePrinter extends TextPrinter {
    @Override
    public void print(String text) { System.out.println("_" + text + "_"); }
}

class BoldItalicPrinter extends TextPrinter {
    @Override
    public void print(String text) { System.out.println("***" + text + "***"); }
}

class BoldUnderlinePrinter extends TextPrinter {
    @Override
    public void print(String text) { System.out.println("**_" + text + "_**"); }
}

class ItalicUnderlinePrinter extends TextPrinter {
    @Override
    public void print(String text) { System.out.println("*_" + text + "_*"); }
}

class BoldItalicUnderlinePrinter extends TextPrinter {
    @Override
    public void print(String text) { System.out.println("***_" + text + "_***"); }
}
