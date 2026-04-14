package interactiveOopCodes.fundamentals.classInternals.initBlockDemo;

/**
 * Initialization Blocks — Static vs Instance
 *
 * Initialization blocks are alternatives to constructors for
 * setting up state. They can be static (class-level) or
 * non-static (instance-level, also called IIB).
 */
public class InitBlockDemo {

    // Static initialization block — runs once when class is loaded
    static {
        System.out.println("1. Static init block executed (class loading)");
    }

    // Instance initialization block (IIB) — runs every time an object is created
    {
        System.out.println("2. Instance init block executed (before constructor)");
    }

    // Constructor
    public InitBlockDemo() {
        System.out.println("3. Constructor executed");
    }

    public static void main(String[] args) {
        System.out.println("=== Creating first object ===");
        InitBlockDemo obj1 = new InitBlockDemo();

        System.out.println("\n=== Creating second object ===");
        InitBlockDemo obj2 = new InitBlockDemo();
    }
}
/*
  Output:
  1. Static init block executed (class loading)
  === Creating first object ===
  2. Instance init block executed (before constructor)
  3. Constructor executed

  === Creating second object ===
  2. Instance init block executed (before constructor)
  3. Constructor executed
*/
