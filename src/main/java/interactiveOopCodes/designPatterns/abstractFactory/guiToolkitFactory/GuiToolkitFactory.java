package interactiveOopCodes.designPatterns.abstractFactory.guiToolkitFactory;

/**
 * Abstract Factory - cross-platform UI toolkit. The canonical
 * GoF book example (alongside the original Motif/Look-and-Feel
 * application).
 *
 * Matrix:  3 OSes    (Windows / Mac / Linux)
 *        x 3 widgets (Button / Checkbox / Window)
 *
 * The Dialog client paints its UI using whichever UIFactory you
 * inject. Detect OS once at startup -> theme the entire dialog
 * without touching its layout logic.
 */
public class GuiToolkitFactory {

    public static void main(String[] args) {
        Dialog dialog = new Dialog("Save changes?");

        System.out.println("--- Booting on Windows ---");
        dialog.paint(new WindowsFactory());

        System.out.println();
        System.out.println("--- Booting on macOS ---");
        dialog.paint(new MacFactory());

        System.out.println();
        System.out.println("--- Booting on Linux ---");
        dialog.paint(new LinuxFactory());
    }
}

interface Button   { void render(String label); }
interface Checkbox { void render(boolean on);   }
interface Window   { void render(String title); }

interface UIFactory {
    Button   createButton();
    Checkbox createCheckbox();
    Window   createWindow();
}

/* =====================  WINDOWS  ===================== */

class WindowsButton   implements Button   { public void render(String label) { System.out.println("  [Win Button  ] " + label); } }
class WindowsCheckbox implements Checkbox { public void render(boolean on)   { System.out.println("  [Win Checkbox] " + (on ? "[v]" : "[ ]")); } }
class WindowsWindow   implements Window   { public void render(String title) { System.out.println("  [Win Window  ] |-- " + title + " --|"); } }

class WindowsFactory implements UIFactory {
    public Button   createButton()   { return new WindowsButton();   }
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
    public Window   createWindow()   { return new WindowsWindow();   }
}

/* =====================  MACOS  ===================== */

class MacButton   implements Button   { public void render(String label) { System.out.println("  [Mac Button  ] " + label + "  (cmd)"); } }
class MacCheckbox implements Checkbox { public void render(boolean on)   { System.out.println("  [Mac Checkbox] " + (on ? "(o)" : "( )")); } }
class MacWindow   implements Window   { public void render(String title) { System.out.println("  [Mac Window  ] (-- " + title + " --)"); } }

class MacFactory implements UIFactory {
    public Button   createButton()   { return new MacButton();   }
    public Checkbox createCheckbox() { return new MacCheckbox(); }
    public Window   createWindow()   { return new MacWindow();   }
}

/* =====================  LINUX  ===================== */

class LinuxButton   implements Button   { public void render(String label) { System.out.println("  [Lnx Button  ] [" + label + "]"); } }
class LinuxCheckbox implements Checkbox { public void render(boolean on)   { System.out.println("  [Lnx Checkbox] " + (on ? "[x]" : "[ ]")); } }
class LinuxWindow   implements Window   { public void render(String title) { System.out.println("  [Lnx Window  ] -=[ " + title + " ]=-"); } }

class LinuxFactory implements UIFactory {
    public Button   createButton()   { return new LinuxButton();   }
    public Checkbox createCheckbox() { return new LinuxCheckbox(); }
    public Window   createWindow()   { return new LinuxWindow();   }
}

/* =====================  CLIENT  ===================== */

class Dialog {
    private final String title;
    Dialog(String title) { this.title = title; }

    /** Platform-neutral layout body. */
    void paint(UIFactory ui) {
        Window   w      = ui.createWindow();
        Checkbox c      = ui.createCheckbox();
        Button   ok     = ui.createButton();
        Button   cancel = ui.createButton();

        w.render(title);
        c.render(true);
        ok.render("OK");
        cancel.render("Cancel");
    }
}
