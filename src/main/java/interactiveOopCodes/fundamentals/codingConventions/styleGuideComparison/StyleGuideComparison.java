package interactiveOopCodes.fundamentals.codingConventions.styleGuideComparison;

/**
 * Oracle vs Google Java Style Guides
 *
 * Oracle Code Conventions (last revised: 1999)
 * - 4-space indentation
 * - 80-character line limit
 * - Wildcard imports allowed
 * - Traditional Java style
 *
 * Google Java Style Guide (last revised: 2018)
 * - 2-space indentation
 * - 100-character column limit
 * - No wildcard imports
 * - Stricter Javadoc requirements
 */
public class StyleGuideComparison {

    // Oracle style: 4-space indent, 80-char lines
    public static void oracleStyleExample() {
        String message = "Oracle conventions use "
                + "4-space indentation and "
                + "80-character line limit.";
        System.out.println(message);

        // Oracle allows wildcard imports:
        // import java.util.*;

        // Braces: opening brace on same line
        if (message.length() > 0) {
            System.out.println("Line length: " + message.length());
        }
    }

    // Google style: 2-space indent, 100-char columns
    public static void googleStyleExample() {
        String message = "Google style uses 2-space indentation and 100-character column limit.";
        System.out.println(message);

        // Google forbids wildcard imports:
        // import java.util.ArrayList;  (explicit only)
        // import java.util.List;       (each class separately)

        // Google: same brace style, but different continuation indent (4 for line wraps)
        if (message.length() > 0) {
            System.out.println("Line length: " + message.length());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Oracle Style (1999) ===");
        oracleStyleExample();

        System.out.println("\n=== Google Style (2018) ===");
        googleStyleExample();

        System.out.println("\n=== Key Differences ===");
        System.out.println("Indentation:  Oracle=4 spaces, Google=2 spaces");
        System.out.println("Line limit:   Oracle=80 chars,  Google=100 chars");
        System.out.println("Imports:      Oracle=wildcards OK, Google=no wildcards");
        System.out.println("Last updated: Oracle=1999, Google=2018");
    }
}
/*
  Output:
  === Oracle Style (1999) ===
  Oracle conventions use 4-space indentation and 80-character line limit.
  Line length: 64

  === Google Style (2018) ===
  Google style uses 2-space indentation and 100-character column limit.
  Line length: 69

  === Key Differences ===
  Indentation:  Oracle=4 spaces, Google=2 spaces
  Line limit:   Oracle=80 chars,  Google=100 chars
  Imports:      Oracle=wildcards OK, Google=no wildcards
  Last updated: Oracle=1999, Google=2018
*/
