package interactiveOopCodes.designPrinciples.grasp.indirectionProtectedDemo;

/**
 * GRASP — Indirection + Protected Variations.
 *
 * Two "advanced" principles paired here because both answer: "how do you
 * shield stable code from unstable collaborators?"
 *
 * INDIRECTION: Insert an intermediary between two parties to decouple them.
 * The Adapter pattern is the canonical example: Sale should not import the
 * GovTaxService directly — it should depend on a TaxCalculatorAdapter that
 * translates the foreign API into local terms.
 *
 * PROTECTED VARIATIONS: Identify points of predicted variation, wrap them
 * behind a stable interface, and let unstable details live behind that wall.
 * "What is likely to change?" is the driving question. Here: tax rates, tax
 * vendors, jurisdiction. We wrap all three behind one TaxCalculator interface.
 *
 * WRONG: BadSale imports GovTaxService directly. When the government API
 * adds a region argument, removes a method, or is replaced by an
 * altogether different provider, BadSale breaks.
 *
 * RIGHT: Sale depends on a TaxCalculator interface (Protected Variations).
 * Concrete implementations are Adapters that wrap whatever real service is
 * in play (Indirection). The interface is the wall; the adapter is the
 * door. Sale never crosses the wall.
 */
public class IndirectionProtectedDemo {

    public static void main(String[] args) {
        System.out.println("=== GRASP — Indirection + Protected Variations ===\n");

        // --- WRONG ---
        System.out.println("[WRONG] BadSale imports GovTaxService directly:");
        BadSale bad = new BadSale(100);
        System.out.println("  bad total with tax = $" + bad.totalWithTax());

        System.out.println();

        // --- RIGHT — same Sale, different tax providers wired in via Adapter ---
        System.out.println("[RIGHT] Sale depends on TaxCalculator interface (Protected Variations):");

        Sale viaGov = new Sale(100, new GovTaxAdapter());
        System.out.println("  using GovTaxAdapter      -> $" + viaGov.totalWithTax());

        Sale viaThirdParty = new Sale(100, new ThirdPartyTaxAdapter());
        System.out.println("  using ThirdPartyAdapter  -> $" + viaThirdParty.totalWithTax());

        Sale viaTest = new Sale(100, new FlatRateTaxCalculator(0.05));
        System.out.println("  using flat-rate (test)   -> $" + viaTest.totalWithTax());
    }
}

/* ─────────────────────────── WRONG ─────────────────────────── */

class GovTaxService {                       // a foreign / volatile API
    double computeTaxRate(String region, String productCategory) { return 0.10; }
}

class BadSale {
    private final double subtotal;
    private final GovTaxService gov = new GovTaxService();    // welded to the foreign API

    BadSale(double subtotal) { this.subtotal = subtotal; }

    double totalWithTax() {
        // If gov.computeTaxRate is renamed, removed, or returns a different shape,
        // EVERY caller of BadSale breaks.
        return subtotal * (1 + gov.computeTaxRate("IL", "book"));
    }
}

/* ─────────────────────────── RIGHT ─────────────────────────── */

// Protected Variations: stable wall against tax-provider changes.
interface TaxCalculator {
    double rate(String region, String category);
}

// Indirection: adapt the foreign API to OUR vocabulary.
class GovTaxAdapter implements TaxCalculator {
    private final GovTaxService gov = new GovTaxService();
    public double rate(String region, String category) {
        return gov.computeTaxRate(region, category);
    }
}

// Another adapter for a different provider — Sale doesn't know either exists.
class ThirdPartyTaxApi {                                       // another foreign API
    double getRateForState(String state) { return 0.08; }
}

class ThirdPartyTaxAdapter implements TaxCalculator {
    private final ThirdPartyTaxApi api = new ThirdPartyTaxApi();
    public double rate(String region, String category) {
        return api.getRateForState(region);                    // different method, same role
    }
}

// A pure-domain TaxCalculator for tests / fallback.
class FlatRateTaxCalculator implements TaxCalculator {
    private final double rate;
    FlatRateTaxCalculator(double r) { rate = r; }
    public double rate(String region, String category) { return rate; }
}

class Sale {
    private final double subtotal;
    private final TaxCalculator tax;          // depends on the abstraction (PV)

    Sale(double subtotal, TaxCalculator tax) {
        this.subtotal = subtotal; this.tax = tax;
    }

    double totalWithTax() {
        return subtotal * (1 + tax.rate("IL", "book"));        // never touches a vendor API
    }
}
