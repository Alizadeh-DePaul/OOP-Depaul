public class LocalVsInstance {
    // INSTANCE VARIABLES: declared in class, outside methods
    // - Get default values (int -> 0, String -> null, etc.)
    // - Exist as long as the object exists
    // - Each object gets its own copy
    int instanceInt;
    String instanceStr;

    void demonstrateLocal() {
        // LOCAL VARIABLES: declared inside methods, blocks, or constructors
        // - Do NOT get default values — must initialize before use!
        // - Exist only while the method executes
        // - Not accessible outside this method
        int localVar = 10;

        System.out.println("Local variable: " + localVar);
        System.out.println("Instance int:   " + instanceInt);
        System.out.println("Instance str:   " + instanceStr);

        // Uncommenting the line below would cause a compile error:
        // int uninitialized;
        // System.out.println(uninitialized); // Error: not initialized
    }

    void modifyInstance() {
        instanceInt = 100;
        instanceStr = "Hello";
    }

    public static void main(String[] args) {
        System.out.println("*** Local vs Instance Variables ***\n");

        LocalVsInstance obj = new LocalVsInstance();

        System.out.println("Before modification:");
        obj.demonstrateLocal();

        obj.modifyInstance();
        System.out.println("\nAfter modifyInstance():");
        obj.demonstrateLocal();

        // localVar is NOT accessible here — it is local to demonstrateLocal()
        // System.out.println(obj.localVar); // Compile error!

        System.out.println("\nKey differences:");
        System.out.println("- Instance: class scope, default values, per-object");
        System.out.println("- Local: method scope, no defaults, temporary");
    }
}
