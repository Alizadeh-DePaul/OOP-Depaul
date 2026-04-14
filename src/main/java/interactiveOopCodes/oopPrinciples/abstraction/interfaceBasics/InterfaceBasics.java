package interactiveOopCodes.oopPrinciples.abstraction.interfaceBasics;

// Interfaces — Define specifications (WHAT to do, not HOW)
// A contract that implementing classes must follow

interface Printable {
    void print();   // implicitly public and abstract
}

interface Loggable {
    void log(String message);
}

// Interface can contain constants (public static final by default)
interface AppConstants {
    String APP_NAME = "MyApp";        // public static final
    int MAX_RETRIES = 3;              // public static final
    double VERSION = 2.5;             // public static final
}

// A class implements an interface using 'implements' keyword
class Document implements Printable {
    private String content;

    Document(String content) {
        this.content = content;
    }

    // MUST be public — interface methods are implicitly public
    @Override
    public void print() {
        System.out.println("Printing: " + content);
    }
}

// A class can implement multiple interfaces
class Report implements Printable, Loggable {
    private String title;

    Report(String title) {
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println("Printing report: " + title);
    }

    @Override
    public void log(String message) {
        System.out.println("[Report LOG] " + title + ": " + message);
    }
}

public class InterfaceBasics {
    public static void main(String[] args) {
        System.out.println("=== Interface Basics ===\n");

        Document doc = new Document("Hello World");
        doc.print();

        System.out.println();

        Report report = new Report("Q4 Sales");
        report.print();
        report.log("Generated successfully");

        System.out.println("\n--- Interface Constants ---");
        System.out.println("App: " + AppConstants.APP_NAME);
        System.out.println("Max retries: " + AppConstants.MAX_RETRIES);
        System.out.println("Version: " + AppConstants.VERSION);

        System.out.println("\n--- Interface as Reference Type ---");
        Printable p = new Report("Annual");
        p.print();   // Works — print() is in Printable
        // p.log("test");  // Compile error — Printable has no log()
    }
}
