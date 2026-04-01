public class FieldInitialization {
    // Field initialization is OPTIONAL.
    // Here myInt is initialized with the value 25.
    public int myInt = 25;

    // If NOT initialized, Java assigns default values:
    // int -> 0, double -> 0.0, boolean -> false, String -> null
    public int uninitializedInt;
    public double uninitializedDouble;
    public boolean uninitializedBool;
    public String uninitializedStr;

    public static void main(String[] args) {
        System.out.println("*** Field Initialization Demo ***");

        FieldInitialization obA = new FieldInitialization();
        FieldInitialization obB = new FieldInitialization();

        System.out.println("obA.myInt = " + obA.myInt);      // 25
        System.out.println("obB.myInt = " + obB.myInt);      // 25

        // Modify one object's field — the other is unaffected
        obA.myInt = 450;
        System.out.println("\nAfter obA.myInt = 450:");
        System.out.println("obA.myInt = " + obA.myInt);      // 450
        System.out.println("obB.myInt = " + obB.myInt);      // 25

        // Default values for uninitialized fields
        System.out.println("\nDefault values:");
        System.out.println("int:     " + obA.uninitializedInt);     // 0
        System.out.println("double:  " + obA.uninitializedDouble);  // 0.0
        System.out.println("boolean: " + obA.uninitializedBool);    // false
        System.out.println("String:  " + obA.uninitializedStr);     // null
    }
}
