package interactiveOopCodes.designPrinciples.grasp.creatorDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * GRASP — Creator.
 *
 * "Assign class B the responsibility to create instance of class A if one
 *  of these is true:
 *     - B aggregates A
 *     - B contains A
 *     - B records A
 *     - B closely uses A
 *     - B has the initializing data for A"
 *
 * WRONG: A client constructs a SalesLineItem with `new` and then asks Sale
 * to add it. The construction concern leaks out of Sale, and every caller
 * has to know the right constructor arguments. If a line item later needs
 * extra context (timestamp, sale-id, sequence number), every caller has to
 * be edited.
 *
 * RIGHT: Sale is the Creator for SalesLineItem — Sale AGGREGATES line items,
 * Sale CONTAINS them, Sale RECORDS them, Sale CLOSELY USES them, Sale HAS
 * the initializing data (it can attach the timestamp / line number).
 * sale.makeLineItem(product, qty) hides construction behind the aggregator
 * that owns the new object.
 */
public class CreatorDemo {

    public static void main(String[] args) {
        System.out.println("=== GRASP — Creator ===\n");

        // --- WRONG ---
        System.out.println("[WRONG] client constructs the line item:");
        BadSale bad = new BadSale();
        BadSalesLineItem li = new BadSalesLineItem(new BadProduct("Pen", 1.50), 4);
        bad.addLineItem(li);
        System.out.println("  caller had to call `new BadSalesLineItem(...)` itself");
        System.out.println("  caller knew the constructor signature; future fields = caller edits");

        System.out.println();

        // --- RIGHT ---
        System.out.println("[RIGHT] Sale is the Creator — it aggregates, so it creates:");
        Sale good = new Sale();
        SalesLineItem first  = good.makeLineItem(new Product("Pen", 1.50), 4);
        SalesLineItem second = good.makeLineItem(new Product("Notebook", 3.25), 2);
        System.out.println("  line " + first.lineNumber  + " — auto-numbered by Sale");
        System.out.println("  line " + second.lineNumber + " — auto-numbered by Sale");
        System.out.println("  caller never wrote `new SalesLineItem(...)`");
    }
}

/* ─────────────────────────── WRONG ─────────────────────────── */

class BadSale {
    private final List<BadSalesLineItem> lineItems = new ArrayList<>();
    void addLineItem(BadSalesLineItem li) { lineItems.add(li); }
    // Notice: no makeLineItem() — caller is forced to `new` it.
}

class BadSalesLineItem {
    private final BadProduct product;
    private final int quantity;
    BadSalesLineItem(BadProduct p, int q) { product = p; quantity = q; }
}

class BadProduct {
    final String name; final double price;
    BadProduct(String n, double p) { name = n; price = p; }
}

/* ─────────────────────────── RIGHT ─────────────────────────── */

class Sale {
    private final List<SalesLineItem> lineItems = new ArrayList<>();

    SalesLineItem makeLineItem(Product p, int qty) {            // Creator
        SalesLineItem li = new SalesLineItem(p, qty, lineItems.size() + 1);
        lineItems.add(li);
        return li;
    }

    int count() { return lineItems.size(); }
}

class SalesLineItem {
    private final Product product;
    private final int quantity;
    final int lineNumber;                                       // auto-assigned by Sale
    SalesLineItem(Product p, int q, int n) {
        product = p; quantity = q; lineNumber = n;
    }
}

class Product {
    final String name; final double price;
    Product(String n, double p) { name = n; price = p; }
}
