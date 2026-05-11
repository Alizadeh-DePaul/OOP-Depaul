package interactiveOopCodes.designPrinciples.grasp.controllerDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * GRASP — Controller.
 *
 * "Assign the responsibility for receiving and handling a system-event
 *  message to a class representing one of:
 *     - the overall system (a 'facade controller')
 *     - the business / organization
 *     - something in the real world that is active (a 'role controller')
 *     - an artificial handler for the use case ('use-case controller')"
 *
 * WRONG: BadSaleGui directly walks the line-item collection, computes the
 * total, prints the receipt, and persists the sale. The UI class has become
 * a god class — UI + domain + persistence. Two cracks open up:
 *   1. Any UI redesign forces re-testing domain logic.
 *   2. The same logic cannot be reused by a CLI, a REST endpoint, or a test.
 *
 * RIGHT: NewSaleController is a use-case controller — its sole job is to
 * receive system events ("endSale", "addLineItem") and coordinate the
 * domain objects. The GUI does ONE thing: translate user clicks into
 * controller calls. The controller is reusable from any UI (CLI, REST, ...)
 * and trivially testable without a screen.
 */
public class ControllerDemo {

    public static void main(String[] args) {
        System.out.println("=== GRASP — Controller ===\n");

        // --- WRONG ---
        System.out.println("[WRONG] BadSaleGui directly runs the domain logic:");
        BadSaleGui badGui = new BadSaleGui();
        badGui.onAddItemButton("Pen", 1.50, 3);
        badGui.onEndSaleButton();

        System.out.println();

        // --- RIGHT ---
        System.out.println("[RIGHT] SaleGui delegates to NewSaleController:");
        NewSaleController controller = new NewSaleController();
        SaleGui gui = new SaleGui(controller);
        gui.onAddItemButton("Pen", 1.50, 3);
        gui.onEndSaleButton();

        // Same controller, different UI — no domain code in the CLI:
        System.out.println();
        System.out.println("[RIGHT] CLI uses the SAME controller:");
        NewSaleController cliCtrl = new NewSaleController();
        SaleCli cli = new SaleCli(cliCtrl);
        cli.handleLine("add Notebook 3.25 2");
        cli.handleLine("end");
    }
}

/* ─────────────────────────── WRONG ─────────────────────────── */

class BadSaleGui {                       // UI class doing domain work
    private final List<BadLineItem> lines = new ArrayList<>();

    void onAddItemButton(String name, double price, int qty) {
        lines.add(new BadLineItem(name, price, qty));     // GUI owns domain state
        System.out.println("  (GUI) added " + qty + " x " + name);
    }

    void onEndSaleButton() {
        double total = 0;
        for (BadLineItem li : lines) total += li.price * li.qty;   // GUI computes
        System.out.println("  (GUI) total = $" + total);
        System.out.println("  (GUI) printing receipt...");          // GUI prints
        System.out.println("  (GUI) saving sale...");               // GUI persists
    }
}

class BadLineItem {
    final String name; final double price; final int qty;
    BadLineItem(String n, double p, int q) { name = n; price = p; qty = q; }
}

/* ─────────────────────────── RIGHT ─────────────────────────── */

class NewSaleController {                // use-case controller
    private final Sale sale = new Sale();

    void addItem(String name, double price, int qty) {
        sale.makeLineItem(name, price, qty);
        System.out.println("  controller -> added " + qty + " x " + name);
    }

    void endSale() {
        System.out.println("  controller -> total = $" + sale.getTotal());
        System.out.println("  controller -> orchestrating print + persist...");
    }
}

class SaleGui {                          // UI: pure delegation
    private final NewSaleController controller;
    SaleGui(NewSaleController c) { controller = c; }
    void onAddItemButton(String name, double price, int qty) {
        controller.addItem(name, price, qty);
    }
    void onEndSaleButton() { controller.endSale(); }
}

class SaleCli {                          // a SECOND UI on the SAME controller
    private final NewSaleController controller;
    SaleCli(NewSaleController c) { controller = c; }
    void handleLine(String input) {
        String[] tok = input.split("\\s+");
        if (tok[0].equals("add")) {
            controller.addItem(tok[1], Double.parseDouble(tok[2]), Integer.parseInt(tok[3]));
        } else if (tok[0].equals("end")) {
            controller.endSale();
        }
    }
}

class Sale {
    private final List<LineItem> lineItems = new ArrayList<>();
    LineItem makeLineItem(String name, double price, int qty) {
        LineItem li = new LineItem(name, price, qty);
        lineItems.add(li);
        return li;
    }
    double getTotal() {
        double s = 0; for (LineItem li : lineItems) s += li.price * li.qty;
        return s;
    }
}

class LineItem {
    final String name; final double price; final int qty;
    LineItem(String n, double p, int q) { name = n; price = p; qty = q; }
}
