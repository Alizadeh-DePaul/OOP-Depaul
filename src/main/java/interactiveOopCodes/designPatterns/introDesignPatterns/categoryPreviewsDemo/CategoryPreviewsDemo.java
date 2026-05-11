package interactiveOopCodes.designPatterns.introDesignPatterns.categoryPreviewsDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * One peek at each GoF category.
 *
 *   Creational  -> Factory Method  (ShapeFactory)
 *   Structural  -> Adapter         (LegacyPrinterAdapter)
 *   Behavioral  -> Observer        (WeatherStation + Display)
 *
 * Each preview is intentionally tiny — enough to show the SHAPE
 * of the category, not enough to be a full pattern lesson. Full
 * treatments come in later topics in this module.
 */
public class CategoryPreviewsDemo {

    public static void main(String[] args) {
        // Creational: ask a factory to MAKE the object — we don't `new` directly.
        Shape s = ShapeFactory.create("circle");
        System.out.println("[Creational] " + s.describe());

        // Structural: adapter wraps a foreign API to fit our interface.
        Printer p = new LegacyPrinterAdapter(new LegacyPrinter());
        p.print("hello");

        // Behavioral: subject notifies observers when state changes.
        WeatherStation station = new WeatherStation();
        station.subscribe(new Display("Phone"));
        station.subscribe(new Display("Tablet"));
        station.setTemp(72);
    }
}

/* ─── Creational preview: Factory Method ─────────────────────────── */
interface Shape { String describe(); }
class Circle implements Shape { public String describe() { return "Shape: circle"; } }
class Square implements Shape { public String describe() { return "Shape: square"; } }

class ShapeFactory {
    static Shape create(String kind) {
        return switch (kind) {
            case "circle" -> new Circle();
            case "square" -> new Square();
            default -> throw new IllegalArgumentException(kind);
        };
    }
}

/* ─── Structural preview: Adapter ────────────────────────────────── */
interface Printer { void print(String msg); }                        // what we want

class LegacyPrinter {                                                 // what we have
    void writeLine(String s) { System.out.println("[Structural] legacy prints: " + s); }
}

class LegacyPrinterAdapter implements Printer {                       // the bridge
    private final LegacyPrinter legacy;
    LegacyPrinterAdapter(LegacyPrinter legacy) { this.legacy = legacy; }
    public void print(String msg) { legacy.writeLine(msg); }
}

/* ─── Behavioral preview: Observer ───────────────────────────────── */
interface Observer { void update(int temp); }

class Display implements Observer {
    private final String name;
    Display(String name) { this.name = name; }
    public void update(int temp) { System.out.println("[Behavioral] " + name + " shows " + temp + "F"); }
}

class WeatherStation {
    private final List<Observer> subs = new ArrayList<>();
    void subscribe(Observer o) { subs.add(o); }
    void setTemp(int t) { for (Observer o : subs) o.update(t); }
}
