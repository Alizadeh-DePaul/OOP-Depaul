package interactiveOopCodes.fundamentals.classMembersAccess.accessLevelDemo;

public class AccessLevelDemo {
    // Class-level: only public or package-private (default)
    // This class is public — accessible everywhere

    // Member-level: all four modifiers allowed
    public String    name;
    private int      age;
    protected String department;
    String           office;  // default (package-private)

    public AccessLevelDemo(String name, int age) {
        this.name = name;
        this.age = age;
        this.department = "Computer Science";
        this.office = "CDM 842";
    }

    // Public getter — controlled access to private field
    public int getAge() {
        return age;
    }

    public static void main(String[] args) {
        System.out.println("*** Access Levels: Class vs Member ***\n");

        AccessLevelDemo prof = new AccessLevelDemo("Dr. Smith", 45);

        // From same class — everything is accessible
        System.out.println("Name (public):       " + prof.name);
        System.out.println("Age (private):       " + prof.age);
        System.out.println("Dept (protected):    " + prof.department);
        System.out.println("Office (default):    " + prof.office);

        System.out.println("\n--- Class-Level Access Rules ---");
        System.out.println("Top-level class: public or default ONLY");
        System.out.println("  public class Foo { }    -> visible everywhere");
        System.out.println("  class Bar { }           -> visible in same package only");
        System.out.println("  private class? NO — would be completely unusable!");
        System.out.println("  protected class? NO — only meaningful inside another class");

        System.out.println("\n--- Member-Level Access Rules ---");
        System.out.println("Methods & fields: all four modifiers allowed");
        System.out.println("  public    -> accessible everywhere");
        System.out.println("  protected -> same package + subclasses in other packages");
        System.out.println("  default   -> same package only");
        System.out.println("  private   -> same class only");
    }
}

// Package-private (default) class — no public modifier
class Helper {
    void assist() {
        System.out.println("Helper is package-private — only visible in same package");

        // Can access public and default members from same package
        AccessLevelDemo demo = new AccessLevelDemo("Test", 30);
        System.out.println(demo.name);       // public — OK
        System.out.println(demo.office);     // default — OK (same package)
        System.out.println(demo.department); // protected — OK (same package)
        // System.out.println(demo.age);     // private — COMPILE ERROR!
    }
}
