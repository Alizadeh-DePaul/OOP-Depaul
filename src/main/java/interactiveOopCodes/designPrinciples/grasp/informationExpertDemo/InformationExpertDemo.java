package interactiveOopCodes.designPrinciples.grasp.informationExpertDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * GRASP — Information Expert.
 *
 * "Assign a responsibility to the class that has the information needed to
 *  fulfill it."
 *
 * WRONG: BadBillingCalculator computes the Sale's total by reaching INTO
 * Sale through public getters, walking the line-item collection, and asking
 * each item for its product and quantity. The calculator has no data of its
 * own — it borrows everything from Sale. Two costs follow: (1) Sale must
 * expose internals (getLineItems, getProduct, getQuantity), violating
 * encapsulation; (2) every change to Sale's internal structure forces a
 * change to the external calculator.
 *
 * RIGHT: Sale is the Information Expert for sale total — it OWNS the line
 * items. SalesLineItem is the Information Expert for its OWN subtotal — it
 * owns the product reference and the quantity. The calculation walks the
 * delegation chain: sale.getTotal() asks each line for its subtotal.
 * Encapsulation preserved; collaboration reads like the domain.
 */
public class InformationExpertDemo {

    public static void main(String[] args) {
        System.out.println("=== GRASP — Information Expert ===\n");

        // --- WRONG ---
        System.out.println("[WRONG] external BillingCalculator reaches into Sale:");
        BadSale bad = new BadSale();
        bad.addLineItem(new BadProduct("Clean Code", 45.00), 1);
        bad.addLineItem(new BadProduct("Pragmatic Programmer", 39.99), 2);
        double badTotal = new BadBillingCalculator().calculateTotal(bad);
        System.out.println("  external total = $" + badTotal);

        System.out.println();

        // --- RIGHT ---
        System.out.println("[RIGHT] Sale is the Information Expert for its own total:");
        Sale good = new Sale();
        good.addLineItem(new Product("Clean Code", 45.00), 1);
        good.addLineItem(new Product("Pragmatic Programmer", 39.99), 2);
        double total = good.getTotal();
        System.out.println("  sale.getTotal() = $" + total);
    }
}

/* ─────────────────────────── WRONG ─────────────────────────── */

class BadBillingCalculator {
    // No data of its own — borrows everything from Sale via getters.
    double calculateTotal(BadSale sale) {
        double total = 0;
        for (BadSalesLineItem li : sale.getLineItems()) {
            total += li.getProduct().getPrice() * li.getQuantity();
        }
        return total;
    }
}

class BadSale {
    private final List<BadSalesLineItem> lineItems = new ArrayList<>();
    void addLineItem(BadProduct p, int qty) {
        lineItems.add(new BadSalesLineItem(p, qty));
    }
    List<BadSalesLineItem> getLineItems() { return lineItems; }   // leaks internals
}

class BadSalesLineItem {
    private final BadProduct product;
    private final int quantity;
    BadSalesLineItem(BadProduct p, int q) { product = p; quantity = q; }
    BadProduct getProduct() { return product; }
    int getQuantity() { return quantity; }
}

class BadProduct {
    private final double price;
    private final String name;
    BadProduct(String n, double p) { name = n; price = p; }
    double getPrice() { return price; }
    String getName() { return name; }
}

/* ─────────────────────────── RIGHT ─────────────────────────── */

class Sale {                                       // Information Expert: total
    private final List<SalesLineItem> lineItems = new ArrayList<>();
    void addLineItem(Product p, int qty) {
        lineItems.add(new SalesLineItem(p, qty));
    }
    double getTotal() {
        double sum = 0;
        for (SalesLineItem li : lineItems) sum += li.getSubtotal();
        return sum;
    }
}

class SalesLineItem {                              // Information Expert: subtotal
    private final Product product;
    private final int quantity;
    SalesLineItem(Product p, int q) { product = p; quantity = q; }
    double getSubtotal() { return product.getPrice() * quantity; }
}

class Product {
    private final double price;
    private final String name;
    Product(String n, double p) { name = n; price = p; }
    double getPrice() { return price; }
    String getName() { return name; }
}
