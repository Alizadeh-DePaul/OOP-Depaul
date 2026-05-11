package interactiveOopCodes.designPatterns.factoryMethod.crossPlatformDialog;

/**
 * CrossPlatformDialog -- Factory Method in a UI toolkit.
 *
 * Same pattern, different domain:
 *   Creators:  Dialog (abstract) <- WindowsDialog, MacDialog, LinuxDialog
 *   Products:  Button (interface) <- WindowsButton, MacButton, LinuxButton
 *
 * The Dialog's render() method calls the factory method createButton().
 * The concrete Dialog subclass decides WHICH look-and-feel Button gets
 * rendered. One client UI workflow, three platforms.
 */
public class CrossPlatformDialog {

    public static void main(String[] args) {
        renderOn(new WindowsDialog());
        renderOn(new MacDialog());
        renderOn(new LinuxDialog());
    }

    private static void renderOn(Dialog dialog) {
        System.out.println("------- " + dialog.getClass().getSimpleName() + " -------");
        dialog.render();
        System.out.println();
    }
}

/* PRODUCT */
interface Button {
    String paint();
    void onClick();
}

/* CONCRETE PRODUCTS */
class WindowsButton implements Button {
    @Override public String paint() { return "[ Win-style rounded button ]"; }
    @Override public void onClick() { System.out.println("  -> Windows click handler"); }
}
class MacButton implements Button {
    @Override public String paint() { return "( Aqua-style pill button )"; }
    @Override public void onClick() { System.out.println("  -> Mac click handler"); }
}
class LinuxButton implements Button {
    @Override public String paint() { return "{ GTK rectangle button }"; }
    @Override public void onClick() { System.out.println("  -> Linux click handler"); }
}

/* CREATOR */
abstract class Dialog {

    /** FACTORY METHOD -- concrete Dialog chooses the Button look. */
    abstract Button createButton();

    /** Template method -- same workflow on all platforms. */
    public void render() {
        Button btn = createButton();
        System.out.println("  draw: " + btn.paint());
        btn.onClick();
    }
}

/* CONCRETE CREATORS */
class WindowsDialog extends Dialog {
    @Override Button createButton() { return new WindowsButton(); }
}
class MacDialog extends Dialog {
    @Override Button createButton() { return new MacButton(); }
}
class LinuxDialog extends Dialog {
    @Override Button createButton() { return new LinuxButton(); }
}
