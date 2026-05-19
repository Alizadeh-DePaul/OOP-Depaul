package interactiveOopCodes.designPatterns.antipatterns.spaghettiCodeDemo;

/**
 * SpaghettiCodeDemo - uncontrolled flow with no structure.
 *
 *   BAD: One processOrder() method handles validation, pricing, discounts,
 *        shipping, and notification - interleaved with flag-based branches.
 *        Cyclomatic complexity explodes; you have to read every line to
 *        understand any one rule.
 *
 *   GOOD: Same business logic, but each concern lives in its own
 *         well-named method. Each method does ONE thing the way you would
 *         explain it in plain English - the single-purpose principle.
 *
 *   Cure: Extract Method + (sometimes) Replace Conditional with Strategy.
 */
public class SpaghettiCodeDemo {

    /* ----------------- BAD: Spaghetti ----------------- */

    static String processOrderSpaghetti(String item, int qty, boolean isMember, String country) {
        // Validation, pricing, discount, shipping, formatting - all mashed.
        if (item == null || item.isEmpty()) return "ERROR: empty item";
        if (qty <= 0) return "ERROR: qty must be > 0";
        double price;
        if (item.equals("book")) price = 12.0;
        else if (item.equals("laptop")) price = 950.0;
        else if (item.equals("pen")) price = 1.5;
        else return "ERROR: unknown item";
        double subtotal = price * qty;
        double discount = 0;
        if (isMember && subtotal > 100) discount = subtotal * 0.10;
        else if (isMember) discount = subtotal * 0.05;
        else if (subtotal > 500) discount = subtotal * 0.03;
        double afterDiscount = subtotal - discount;
        double shipping;
        if (country.equals("US")) shipping = 5;
        else if (country.equals("CA")) shipping = 12;
        else shipping = 25;
        if (afterDiscount > 200) shipping = 0;
        double total = afterDiscount + shipping;
        // Pretend payment and notification also live here...
        return "OK: total=" + total + " (subtotal=" + subtotal
                + " discount=" + discount + " shipping=" + shipping + ")";
    }

    /* ----------------- GOOD: Refactored ----------------- */

    static String processOrderClean(String item, int qty, boolean isMember, String country) {
        String validationError = validate(item, qty);
        if (validationError != null) return validationError;

        double subtotal      = priceFor(item) * qty;
        double discount      = discountFor(subtotal, isMember);
        double afterDiscount = subtotal - discount;
        double shipping      = shippingFor(afterDiscount, country);
        double total         = afterDiscount + shipping;

        return formatReceipt(total, subtotal, discount, shipping);
    }

    static String validate(String item, int qty) {
        if (item == null || item.isEmpty()) return "ERROR: empty item";
        if (qty <= 0)                       return "ERROR: qty must be > 0";
        if (priceFor(item) < 0)             return "ERROR: unknown item";
        return null;
    }

    static double priceFor(String item) {
        return switch (item) {
            case "book"   -> 12.0;
            case "laptop" -> 950.0;
            case "pen"    -> 1.5;
            default       -> -1;
        };
    }

    static double discountFor(double subtotal, boolean isMember) {
        if (isMember && subtotal > 100) return subtotal * 0.10;
        if (isMember)                   return subtotal * 0.05;
        if (subtotal > 500)             return subtotal * 0.03;
        return 0;
    }

    static double shippingFor(double afterDiscount, String country) {
        if (afterDiscount > 200) return 0;
        return switch (country) {
            case "US" -> 5.0;
            case "CA" -> 12.0;
            default   -> 25.0;
        };
    }

    static String formatReceipt(double total, double subtotal, double discount, double shipping) {
        return "OK: total=" + total + " (subtotal=" + subtotal
                + " discount=" + discount + " shipping=" + shipping + ")";
    }

    public static void main(String[] args) {
        // Same inputs -> same outputs. The clean version is simply readable.
        System.out.println("BAD  : " + processOrderSpaghetti("laptop", 1, true, "US"));
        System.out.println("GOOD : " + processOrderClean   ("laptop", 1, true, "US"));
        System.out.println("BAD  : " + processOrderSpaghetti("book",  0, false, "FR"));
        System.out.println("GOOD : " + processOrderClean   ("book",  0, false, "FR"));
    }
}
