// Encapsulate What Varies — isolating change points behind stable interfaces
// "Whatever changes, encapsulate it" — a core design principle

// BAD: Tax logic scattered everywhere — a rate change requires editing many files
class BadOrderProcessor {
    static double calculateOrderTotal(double subtotal) {
        double tax = subtotal * 0.10;   // Hardcoded rate!
        return subtotal + tax;
    }

    static double calculateShippingCost(double weight) {
        double handlingTax = weight * 2.5 * 0.10;  // Same rate, duplicated!
        return weight * 2.5 + handlingTax;
    }
}

// GOOD: Tax calculation encapsulated in one place
class TaxCalculator {
    private double rate;

    public TaxCalculator(double rate) {
        if (rate < 0 || rate > 1) {
            throw new IllegalArgumentException("Rate must be between 0 and 1");
        }
        this.rate = rate;
    }

    public double calculate(double amount) {
        return amount * rate;
    }

    public double getRate() { return rate; }
}

class GoodOrderProcessor {
    private TaxCalculator taxCalc;

    public GoodOrderProcessor(TaxCalculator taxCalc) {
        this.taxCalc = taxCalc;
    }

    public double calculateOrderTotal(double subtotal) {
        double tax = taxCalc.calculate(subtotal);
        return subtotal + tax;
    }

    public double calculateShippingCost(double weight) {
        double shippingBase = weight * 2.5;
        double tax = taxCalc.calculate(shippingBase);
        return shippingBase + tax;
    }
}

public class EncapsulateWhatVaries {
    public static void main(String[] args) {
        System.out.println("=== BAD: Hardcoded tax rate (scattered) ===");
        System.out.println("Order total: $" + BadOrderProcessor.calculateOrderTotal(100));
        System.out.println("Shipping:    $" + BadOrderProcessor.calculateShippingCost(10));
        System.out.println("To change rate: edit EVERY file that mentions 0.10\n");

        System.out.println("=== GOOD: Encapsulated tax rate (one place) ===");
        TaxCalculator tax = new TaxCalculator(0.10);
        GoodOrderProcessor processor = new GoodOrderProcessor(tax);
        System.out.println("Order total: $" + processor.calculateOrderTotal(100));
        System.out.println("Shipping:    $" + processor.calculateShippingCost(10));

        // Rate changes? ONE place to update
        System.out.println("\n=== Tax rate changed to 8.5% ===");
        TaxCalculator newTax = new TaxCalculator(0.085);
        GoodOrderProcessor newProcessor = new GoodOrderProcessor(newTax);
        System.out.println("Order total: $" + newProcessor.calculateOrderTotal(100));
        System.out.println("Shipping:    $" + newProcessor.calculateShippingCost(10));
        System.out.println("Changed in ONE place — zero other files touched");
    }
}
