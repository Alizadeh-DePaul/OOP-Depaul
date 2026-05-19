package interactiveOopCodes.designPatterns.decorator.guiToolkit;

/**
 * Decorator Pattern - GUI Toolkit (Real-World example).
 *
 *   A classic real-world Decorator use case: GUI widgets.
 *   You have two basic ConcreteComponents - Button and TextBox -
 *   and three decorators that can be stacked on either:
 *     - ColorDecorator   (foreground / background tint)
 *     - FontDecorator    (font family + size)
 *     - BorderDecorator  (drawn border)
 *
 *   Java's own Swing toolkit does this: JScrollPane wraps any
 *   JComponent, JViewport wraps any Component, and so on.
 *   Any combination, any order, decided at runtime.
 */
public class GuiToolkit {

    public static void main(String[] args) {
        System.out.println("-- a plain Button --");
        GuiComponent b1 = new Button();
        b1.render();

        System.out.println("\n-- Button with a colored skin --");
        GuiComponent b2 = new ColorDecorator(new Button());
        b2.render();

        System.out.println("\n-- TextBox: font + color + border (three decorations) --");
        GuiComponent t1 = new BorderDecorator(
                            new ColorDecorator(
                                new FontDecorator(new TextBox())));
        t1.render();

        System.out.println("\n-- same TextBox, decorators applied in a different order --");
        GuiComponent t2 = new FontDecorator(
                            new BorderDecorator(
                                new ColorDecorator(new TextBox())));
        t2.render();
    }
}

/* Component - the shared GUI contract */
interface GuiComponent {
    void render();
}

/* ConcreteComponent - a button */
class Button implements GuiComponent {
    @Override
    public void render() {
        System.out.println("  rendering [Button]");
    }
}

/* ConcreteComponent - a text input box */
class TextBox implements GuiComponent {
    @Override
    public void render() {
        System.out.println("  rendering [TextBox]");
    }
}

/* Decorator - the abstract wrapper */
abstract class GuiDecorator implements GuiComponent {
    protected final GuiComponent component;

    protected GuiDecorator(GuiComponent component) {
        this.component = component;
    }

    @Override
    public void render() {
        component.render();
    }
}

/* ConcreteDecorator - tint the rendered widget */
class ColorDecorator extends GuiDecorator {
    public ColorDecorator(GuiComponent component) {
        super(component);
    }

    @Override
    public void render() {
        super.render();
        System.out.println("    + ColorDecorator: applying foreground/background tint");
    }
}

/* ConcreteDecorator - apply a font */
class FontDecorator extends GuiDecorator {
    public FontDecorator(GuiComponent component) {
        super(component);
    }

    @Override
    public void render() {
        super.render();
        System.out.println("    + FontDecorator: setting font family and size");
    }
}

/* ConcreteDecorator - draw a border */
class BorderDecorator extends GuiDecorator {
    public BorderDecorator(GuiComponent component) {
        super(component);
    }

    @Override
    public void render() {
        super.render();
        System.out.println("    + BorderDecorator: drawing border");
    }
}
