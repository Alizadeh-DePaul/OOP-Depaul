package interactiveOopCodes.designPatterns.antipatterns.copyPasteDemo;

import java.util.function.DoubleUnaryOperator;

/**
 * CopyPasteDemo - duplication that drifts.
 *
 *   BAD: Three tax functions for US/EU/Asia were copy-pasted from one
 *        template. Each carries the same special cases - but next quarter
 *        one rule changes and somebody only fixes two of the three.
 *
 *   GOOD: Extract the SHAPE of the calculation once; pass the varying
 *         parts (rate, surcharge) as arguments, or - when the variation is
 *         logic, not just numbers - as a Strategy.
 *
 *   Cousin antipattern - Cargo Cult: copying not just the body of a
 *   method but its surrounding rituals (the `synchronized` block, the
 *   comment header, the `static` modifier) without knowing why they
 *   were there to begin with.
 *
 *   Rule of Three (Fowler): one copy is fine; two is a coincidence;
 *   three is the signal to extract.
 */
public class CopyPasteDemo {

    /* ----------------- BAD: Copy-Paste ----------------- */

    static double computeTaxUs(double amount, boolean digital) {
        double base = amount * 0.07;
        if (digital) base *= 0.5;                     // digital-goods discount
        if (amount > 1000) base += amount * 0.005;     // high-value surcharge
        return Math.round(base * 100.0) / 100.0;
    }

    static double computeTaxEu(double amount, boolean digital) {
        double base = amount * 0.20;
        if (digital) base *= 0.5;
        if (amount > 1000) base += amount * 0.005;     // <- if this rule changes,
        return Math.round(base * 100.0) / 100.0;        //    you must remember to
    }                                                   //    update ALL THREE.

    static double computeTaxAsia(double amount, boolean digital) {
        double base = amount * 0.10;
        if (digital) base *= 0.5;
        if (amount > 1000) base += amount * 0.005;
        return Math.round(base * 100.0) / 100.0;
    }

    /* ----------------- GOOD: Extracted ----------------- */

    /** Region carries only the data that varies. */
    record Region(String name, double rate) {}

    static final Region US   = new Region("US",   0.07);
    static final Region EU   = new Region("EU",   0.20);
    static final Region ASIA = new Region("ASIA", 0.10);

    /** One implementation. Variations are arguments, not new methods. */
    static double computeTax(Region region, double amount, boolean digital) {
        double base = amount * region.rate();
        if (digital)        base *= 0.5;
        if (amount > 1000)  base += amount * 0.005;
        return Math.round(base * 100.0) / 100.0;
    }

    /** When the variation is RULES, not just numbers, pass behaviour. */
    static double computeTaxStrategy(double amount, DoubleUnaryOperator rateRule) {
        return Math.round(rateRule.applyAsDouble(amount) * 100.0) / 100.0;
    }

    public static void main(String[] args) {
        // BAD: same call shape x 3 (and three places to fix when rules change)
        System.out.printf("US   bad : %.2f%n", computeTaxUs  (1500, true));
        System.out.printf("EU   bad : %.2f%n", computeTaxEu  (1500, true));
        System.out.printf("ASIA bad : %.2f%n", computeTaxAsia(1500, true));

        // GOOD: one method, parameterised
        System.out.printf("US   ok  : %.2f%n", computeTax(US,   1500, true));
        System.out.printf("EU   ok  : %.2f%n", computeTax(EU,   1500, true));
        System.out.printf("ASIA ok  : %.2f%n", computeTax(ASIA, 1500, true));

        // GOOD: Strategy form - varying RULES, not just numbers
        double quirky = computeTaxStrategy(1500,
            amt -> amt * 0.12 + (amt > 1000 ? 5 : 0));
        System.out.printf("Quirky   : %.2f%n", quirky);
    }
}
