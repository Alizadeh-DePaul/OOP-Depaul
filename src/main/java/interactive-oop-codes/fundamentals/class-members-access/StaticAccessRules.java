public class StaticAccessRules {
    // Instance variable
    int instanceVar = 10;

    // Static variable
    static int staticVar = 20;

    // Instance method — can access BOTH static and instance
    void instanceMethod() {
        System.out.println("Instance method accessing instanceVar: " + instanceVar);
        System.out.println("Instance method accessing staticVar:   " + staticVar);
    }

    // Static method — can access ONLY static members
    static void staticMethod() {
        System.out.println("Static method accessing staticVar: " + staticVar);

        // COMPILE ERROR if uncommented:
        // System.out.println(instanceVar);
        // Error: non-static variable instanceVar cannot be referenced from a static context

        // To access instance members from static context, create an object:
        StaticAccessRules obj = new StaticAccessRules();
        System.out.println("Static method accessing instanceVar via object: " + obj.instanceVar);
    }

    public static void main(String[] args) {
        System.out.println("*** Static Access Rules ***\n");

        // main() is static — same rules apply
        // System.out.println(instanceVar); // COMPILE ERROR!

        System.out.println("--- Calling static method (no object needed) ---");
        StaticAccessRules.staticMethod();

        System.out.println("\n--- Calling instance method (object required) ---");
        StaticAccessRules obj = new StaticAccessRules();
        obj.instanceMethod();

        System.out.println("\n--- Why is main() public static? ---");
        System.out.println("public: JVM must call it from outside the class");
        System.out.println("static: JVM calls it BEFORE any objects exist");
        System.out.println("void:   returns control to JVM, exit via System.exit()");
    }
}
