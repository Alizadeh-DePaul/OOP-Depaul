/**
 * Init Blocks vs Constructors — When to Use Which?
 *
 * Init blocks are useful when:
 * 1. You have shared setup logic across multiple constructors
 * 2. You need to initialize instance fields with complex logic
 * 3. You're using anonymous classes (which can't have constructors)
 */
public class InitVsConstructor {

    private String id;
    private String name;
    private long createdAt;

    // Instance init block: shared setup for ALL constructors
    {
        this.id = "USR-" + System.nanoTime();
        this.createdAt = System.currentTimeMillis();
        System.out.println("  Init block: generated id = " + id);
    }

    // Constructor 1: name only
    public InitVsConstructor(String name) {
        this.name = name;
        System.out.println("  Constructor(name): " + name);
    }

    // Constructor 2: no-arg
    public InitVsConstructor() {
        this.name = "Anonymous";
        System.out.println("  Constructor(): default name");
    }

    public String toString() {
        return name + " [id=" + id + "]";
    }

    public static void main(String[] args) {
        System.out.println("Creating user with name:");
        InitVsConstructor user1 = new InitVsConstructor("Alice");
        System.out.println("Result: " + user1 + "\n");

        System.out.println("Creating user without name:");
        InitVsConstructor user2 = new InitVsConstructor();
        System.out.println("Result: " + user2 + "\n");

        // Anonymous class: no named constructor possible!
        System.out.println("Creating via anonymous class:");
        Runnable task = new Runnable() {
            // Instance init block is the ONLY way to add setup logic
            {
                System.out.println("  Anonymous class init block runs!");
            }

            public void run() {
                System.out.println("  Task running...");
            }
        };
        task.run();
    }
}
/*
  Output:
  Creating user with name:
    Init block: generated id = USR-...
    Constructor(name): Alice
  Result: Alice [id=USR-...]

  Creating user without name:
    Init block: generated id = USR-...
    Constructor(): default name
  Result: Anonymous [id=USR-...]

  Creating via anonymous class:
    Anonymous class init block runs!
    Task running...
*/
