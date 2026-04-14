package interactiveOopCodes.oopPrinciples.abstraction.extendAndImplement;

// Extend and Implement — Combining abstract classes with interfaces
// Rule: extends BEFORE implements (positional notation)

interface Drawable {
    void draw();
}

interface Resizable {
    void resize(double factor);
}

abstract class Widget {
    protected String name;

    Widget(String name) {
        this.name = name;
        System.out.println("Widget constructor: " + name);
    }

    abstract void render();

    void describe() {
        System.out.println("Widget: " + name);
    }
}

// Extends BEFORE implements — compiler needs parent class context first
class Button extends Widget implements Drawable, Resizable {
    private double size = 1.0;

    Button(String name) {
        super(name);
    }

    @Override
    void render() {
        System.out.println("Rendering button: " + name + " (size: " + size + ")");
    }

    @Override
    public void draw() {
        System.out.println("Drawing button: " + name);
    }

    @Override
    public void resize(double factor) {
        size *= factor;
        System.out.println("Resized " + name + " to " + size);
    }
}

// Partial implementation — class becomes abstract
abstract class PartialWidget extends Widget implements Drawable {
    PartialWidget(String name) {
        super(name);
    }

    // Implements draw() but does NOT implement render() from Widget
    @Override
    public void draw() {
        System.out.println("Default drawing for: " + name);
    }

    // render() is still abstract — inherited from Widget
}

// Complete class must finish ALL remaining abstract methods
class Label extends PartialWidget {
    Label(String name) {
        super(name);
    }

    @Override
    void render() {
        System.out.println("Rendering label: " + name);
    }
}

public class ExtendAndImplement {
    public static void main(String[] args) {
        System.out.println("=== Extend and Implement ===\n");

        Button btn = new Button("Submit");
        btn.describe();
        btn.render();
        btn.draw();
        btn.resize(2.0);

        System.out.println("\n--- Partial Implementation ---");
        Label lbl = new Label("Title");
        lbl.describe();
        lbl.render();
        lbl.draw();

        System.out.println("\n--- Polymorphism Through Multiple Types ---");
        Widget w = new Button("Cancel");
        w.render();

        Drawable d = new Button("OK");
        d.draw();

        Resizable r = new Button("Resize Me");
        r.resize(1.5);
    }
}
