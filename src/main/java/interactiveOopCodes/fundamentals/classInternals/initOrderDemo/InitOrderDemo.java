package interactiveOopCodes.fundamentals.classInternals.initOrderDemo;

/**
 * Execution Order of Multiple Initialization Blocks
 *
 * Multiple init blocks are executed in the order they appear in
 * the source code. Static blocks run first (class load), then
 * instance blocks (per object), then the constructor.
 */
public class InitOrderDemo {

    // First static block
    static {
        System.out.println("Step 1: Static block A");
    }

    // Second static block
    static {
        System.out.println("Step 2: Static block B");
    }

    // First instance block
    {
        System.out.println("Step 4: Instance block A");
    }

    // Field with inline initializer
    private String name = initName();

    private static String initName() {
        // This helper shows that field initializers also follow source order
        System.out.println("Step 5: Field initializer (name)");
        return "Default";
    }

    // Second instance block
    {
        System.out.println("Step 6: Instance block B");
    }

    // Constructor
    public InitOrderDemo(String name) {
        this.name = name;
        System.out.println("Step 7: Constructor (name = " + name + ")");
    }

    public static void main(String[] args) {
        System.out.println("Step 3: main() starts\n");

        System.out.println("--- Creating first object ---");
        InitOrderDemo obj1 = new InitOrderDemo("Alice");

        System.out.println("\n--- Creating second object ---");
        InitOrderDemo obj2 = new InitOrderDemo("Bob");
    }
}
/*
  Output:
  Step 1: Static block A
  Step 2: Static block B
  Step 3: main() starts

  --- Creating first object ---
  Step 4: Instance block A
  Step 5: Field initializer (name)
  Step 6: Instance block B
  Step 7: Constructor (name = Alice)

  --- Creating second object ---
  Step 4: Instance block A
  Step 5: Field initializer (name)
  Step 6: Instance block B
  Step 7: Constructor (name = Bob)
*/
