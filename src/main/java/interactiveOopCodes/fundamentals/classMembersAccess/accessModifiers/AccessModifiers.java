package interactiveOopCodes.fundamentals.classMembersAccess.accessModifiers;

public class AccessModifiers {
    public int    publicField    = 1;  // Accessible everywhere
    protected int protectedField = 2;  // Same package + subclasses
    int           defaultField   = 3;  // Same package only (package-private)
    private int   privateField   = 4;  // Same class only

    public void showPublic()       { System.out.println("public method");    }
    protected void showProtected() { System.out.println("protected method"); }
    void showDefault()             { System.out.println("default method");   }
    private void showPrivate()     { System.out.println("private method");   }

    // Private members ARE accessible from the same class
    void accessOwnPrivate() {
        System.out.println("Accessing own private field: " + privateField);
        showPrivate(); // Legal — same class
    }

    // Same-class instances can access each other's private members!
    void compareTo(AccessModifiers other) {
        System.out.println("Accessing OTHER object's private field: " + other.privateField);
    }

    public static void main(String[] args) {
        System.out.println("*** Access Modifiers ***\n");

        AccessModifiers obj = new AccessModifiers();

        // All accessible from same class
        System.out.println("public:    " + obj.publicField);
        System.out.println("protected: " + obj.protectedField);
        System.out.println("default:   " + obj.defaultField);
        System.out.println("private:   " + obj.privateField);

        System.out.println();
        obj.accessOwnPrivate();

        System.out.println();
        AccessModifiers obj2 = new AccessModifiers();
        obj.compareTo(obj2); // Same class can access other's private!

        System.out.println("\n--- Access Hierarchy ---");
        System.out.println("public > protected > default > private");
    }
}
