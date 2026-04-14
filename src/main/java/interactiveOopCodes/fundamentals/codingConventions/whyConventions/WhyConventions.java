package interactiveOopCodes.fundamentals.codingConventions.whyConventions;

/**
 * Why Coding Conventions Matter
 *
 * Coding conventions are sets of guidelines for:
 * - Coding style and formatting
 * - Naming rules
 * - File organization
 * - Best practices and programming principles
 *
 * Goals:
 * 1. Maintain unified code style across a team
 * 2. Minimize software maintenance costs
 * 3. Improve code readability
 * 4. Speed up onboarding and development
 */
public class WhyConventions {

    // BAD: No conventions — what does this code do?
    static void bad_example() {
        int x = 100;
        int y = 12;
        double z = x * y / 100.0;
        boolean b = z > 10;
        if (b) {
            System.out.println("Result: " + z);
        }
    }

    // GOOD: Conventions applied — self-documenting code
    static void goodExample() {
        int annualSalary = 100;
        int taxRatePercent = 12;
        double taxAmount = annualSalary * taxRatePercent / 100.0;
        boolean isHighTax = taxAmount > 10;
        if (isHighTax) {
            System.out.println("Tax amount: " + taxAmount);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Without Conventions ===");
        bad_example();

        System.out.println("\n=== With Conventions ===");
        goodExample();

        // Both produce the same result, but which would you
        // rather maintain 6 months from now?
    }
}
/*
  Output:
  === Without Conventions ===
  Result: 12.0

  === With Conventions ===
  Tax amount: 12.0
*/
