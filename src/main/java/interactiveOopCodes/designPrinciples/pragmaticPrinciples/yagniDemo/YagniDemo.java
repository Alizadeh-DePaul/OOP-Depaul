package interactiveOopCodes.designPrinciples.pragmaticPrinciples.yagniDemo;

import java.util.HashMap;
import java.util.Map;

/**
 * YAGNI — You Aren't Gonna Need It.
 *
 * Bad : a "configurable" report factory with a Map&lt;String, Object&gt;
 *       options bag and a strategy registry — for a system that has
 *       exactly ONE report.
 * Good: a single class that does the one thing the product actually needs.
 *
 * Add complexity when extension becomes real, not when it is imagined.
 * Every "configurable" knob costs maintenance even when nobody turns it.
 */
public class YagniDemo {

    public static void main(String[] args) {
        System.out.println("=== Bad: over-engineered for hypothetical futures ===");
        Map<String, Object> opts = new HashMap<>();
        opts.put("currency", "USD");
        opts.put("locale",   "en-US");
        opts.put("verbose",  Boolean.FALSE);
        BadReportFactory.register("sales", new SalesStrategy());
        BadReportFactory.create("sales", opts);

        System.out.println("\n=== Good: just the report we actually have ===");
        new SalesReport(100, 0.07).print();
    }
}

interface ReportStrategy { void render(Map<String, Object> opts); }

class SalesStrategy implements ReportStrategy {
    public void render(Map<String, Object> opts) {
        System.out.println("  Bad: rendered sales with " + opts.size() + " unused options");
    }
}

class BadReportFactory {
    private static final Map<String, ReportStrategy> REG = new HashMap<>();
    static void register(String key, ReportStrategy s) { REG.put(key, s); }
    static void create(String key, Map<String, Object> opts) {
        ReportStrategy s = REG.get(key);
        if (s == null) throw new IllegalArgumentException("unknown: " + key);
        s.render(opts);
    }
}

class SalesReport {
    private final double price;
    private final double taxRate;
    SalesReport(double price, double taxRate) { this.price = price; this.taxRate = taxRate; }
    void print() { System.out.println("  Good: sales total = " + price * (1 + taxRate)); }
}
