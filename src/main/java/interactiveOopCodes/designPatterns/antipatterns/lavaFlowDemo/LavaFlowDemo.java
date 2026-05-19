package interactiveOopCodes.designPatterns.antipatterns.lavaFlowDemo;

/**
 * LavaFlowDemo - dead code that solidified.
 *
 *   Like cooled lava, abandoned code paths harden into the codebase.
 *   Nobody remembers what they did; nobody dares delete them in case
 *   "something else depends on them".
 *
 *   BAD: A pricing service is studded with commented-out branches,
 *        orphaned helper methods (computeLegacyDiscount), and feature
 *        flags from migrations that finished two years ago.
 *
 *   GOOD: After confirming nothing calls them (search + tests + git
 *         blame), the dead branches and helpers are deleted. The
 *         remaining code describes only what is actually in production.
 *
 *   Both versions produce the same output for live inputs - the BAD one
 *   just drags around hundreds of lines that have not executed in years.
 */
public class LavaFlowDemo {

    /* ----------------- BAD: cooled lava ----------------- */

    static final boolean LEGACY_2019_PRICING = false;  // migration finished Q1 2020
    static final boolean BLACK_FRIDAY_FLAG   = false;  // BF 2021 promotion

    static double finalPriceBad(double base, boolean isMember) {
        double price = base;

        // TODO(jdoe, 2019): remove after migration complete
        if (LEGACY_2019_PRICING) {
            price = computeLegacyDiscount(base);
        }

        // // Old A/B test - keep for now???
        // if (Math.random() < 0.5) price *= 0.95;

        if (BLACK_FRIDAY_FLAG) price *= 0.70;

        if (isMember) price *= 0.95;

        return price;
    }

    /** Dead helper - kept because somebody is afraid to delete it. */
    @SuppressWarnings("unused")
    static double computeLegacyDiscount(double base) {
        return base * 0.80;        // never invoked when the flag is false
    }

    /* ----------------- GOOD: cleaned up ----------------- */

    static double finalPrice(double base, boolean isMember) {
        return isMember ? base * 0.95 : base;
    }

    public static void main(String[] args) {
        // BAD output == GOOD output, but the BAD version drags around
        // hundreds of lines that haven't run since 2020.
        System.out.println("BAD  member : " + finalPriceBad(100, true));
        System.out.println("GOOD member : " + finalPrice   (100, true));
        System.out.println("BAD  guest  : " + finalPriceBad(100, false));
        System.out.println("GOOD guest  : " + finalPrice   (100, false));
    }
}
