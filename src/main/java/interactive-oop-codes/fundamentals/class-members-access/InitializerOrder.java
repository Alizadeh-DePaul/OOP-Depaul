public class InitializerOrder {
    // Static variable
    static int staticVar;

    // Instance variable
    int instanceVar;

    // Static initializer block — runs when CLASS is loaded
    static {
        staticVar = 100;
        System.out.println("1. Static block executed (staticVar = " + staticVar + ")");
    }

    // Instance initializer block — runs before EACH constructor
    {
        instanceVar = 200;
        System.out.println("   3. Instance block executed (instanceVar = " + instanceVar + ")");
    }

    // Second static block — runs after the first
    static {
        System.out.println("2. Second static block executed");
    }

    // Constructor — runs after instance blocks
    InitializerOrder() {
        System.out.println("   4. Constructor executed");
        instanceVar = 300;
    }

    InitializerOrder(int value) {
        System.out.println("   4. Parameterized constructor (value = " + value + ")");
        instanceVar = value;
    }

    public static void main(String[] args) {
        System.out.println("*** Initialization Order ***\n");
        System.out.println("=== main() starts (static blocks already ran) ===\n");

        System.out.println("--- Creating first object ---");
        InitializerOrder obj1 = new InitializerOrder();
        System.out.println("   obj1.instanceVar = " + obj1.instanceVar);

        System.out.println("\n--- Creating second object ---");
        InitializerOrder obj2 = new InitializerOrder(999);
        System.out.println("   obj2.instanceVar = " + obj2.instanceVar);

        System.out.println("\n--- Execution Order Summary ---");
        System.out.println("1. Static blocks (once, when class loads)");
        System.out.println("2. main() starts");
        System.out.println("3. Instance blocks (before EACH constructor)");
        System.out.println("4. Constructor (after instance blocks)");
        System.out.println("\nStatic blocks ran: 1 time");
        System.out.println("Instance blocks ran: " + 2 + " times (once per new object)");
    }
}
