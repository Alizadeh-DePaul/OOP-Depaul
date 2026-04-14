package interactiveOopCodes.fundamentals.codingConventions.formattingRules;

/**
 * Code Formatting: Indentation, Braces, Whitespace & Comments
 *
 * Key formatting rules:
 * - Consistent indentation (4 spaces Oracle, 2 spaces Google)
 * - Opening brace on same line (K&R style)
 * - Spaces around operators and after commas
 * - Blank lines to separate logical sections
 * - Meaningful comments (Javadoc for public API)
 */
public class FormattingRules {

    // === Braces: K&R style (opening brace on same line) ===

    // GOOD: K&R brace style (Java standard)
    public static int goodBraces(int x) {
        if (x > 0) {
            return x;
        } else {
            return -x;
        }
    }

    // === Whitespace around operators ===

    public static void whitespaceDemo() {
        // GOOD: spaces around operators
        int total = 10 + 20;
        boolean isValid = total > 25 && total < 100;

        // BAD: no spaces
        // int total=10+20;
        // boolean isValid=total>25&&total<100;

        // GOOD: space after comma in arguments
        System.out.println("Total: " + total + ", Valid: " + isValid);

        // GOOD: space after keywords (if, for, while, etc.)
        if (isValid) {
            for (int i = 0; i < 3; i++) {
                System.out.println("  Item " + i);
            }
        }
    }

    // === Comments: Javadoc, block, and inline ===

    /**
     * Calculates the area of a rectangle.
     * <p>
     * This is a Javadoc comment — used for public API documentation.
     * It generates HTML docs via the {@code javadoc} tool.
     *
     * @param width  the width of the rectangle (must be positive)
     * @param height the height of the rectangle (must be positive)
     * @return the area (width * height)
     * @throws IllegalArgumentException if width or height is negative
     */
    public static double calculateArea(double width, double height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Dimensions must be positive");
        }
        return width * height;
    }

    // === Blank lines: separate logical sections ===

    public static void main(String[] args) {
        // Section 1: Brace style
        System.out.println("=== Brace Style (K&R) ===");
        System.out.println("abs(-5) = " + goodBraces(-5));
        System.out.println("abs(3)  = " + goodBraces(3));

        // Section 2: Whitespace
        System.out.println("\n=== Whitespace Rules ===");
        whitespaceDemo();

        // Section 3: Comments
        System.out.println("\n=== Javadoc & Comments ===");
        double area = calculateArea(5.0, 3.0);
        System.out.println("Area of 5x3 rectangle: " + area);

        // Section 4: Summary
        System.out.println("\n=== Formatting Checklist ===");
        String[] rules = {
            "Indent: 4 spaces (Oracle) or 2 spaces (Google)",
            "Braces: opening on same line (K&R style)",
            "Operators: spaces around =, +, -, ==, &&, ||",
            "Commas: space after, not before",
            "Keywords: space after if, for, while, switch",
            "Blank lines: between methods and logical sections",
            "Comments: Javadoc for public API, // for inline",
            "Line length: 80 (Oracle) or 100 (Google) characters",
        };
        for (String rule : rules) {
            System.out.println("  * " + rule);
        }
    }
}
/*
  Output:
  === Brace Style (K&R) ===
  abs(-5) = 5
  abs(3)  = 3

  === Whitespace Rules ===
  Total: 30, Valid: true
    Item 0
    Item 1
    Item 2

  === Javadoc & Comments ===
  Area of 5x3 rectangle: 15.0

  === Formatting Checklist ===
    * Indent: 4 spaces (Oracle) or 2 spaces (Google)
    * Braces: opening on same line (K&R style)
    * Operators: spaces around =, +, -, ==, &&, ||
    * Commas: space after, not before
    * Keywords: space after if, for, while, switch
    * Blank lines: between methods and logical sections
    * Comments: Javadoc for public API, // for inline
    * Line length: 80 (Oracle) or 100 (Google) characters
*/
