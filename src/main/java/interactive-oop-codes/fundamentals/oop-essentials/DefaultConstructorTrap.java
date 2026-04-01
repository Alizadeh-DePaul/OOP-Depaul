public class DefaultConstructorTrap {
    int i;

    // When you define ANY constructor, the compiler STOPS
    // providing the default no-argument constructor!
    public DefaultConstructorTrap(int i) {
        this.i = i;
    }

    // Fix Option 1: Explicitly add a no-arg constructor
    // public DefaultConstructorTrap() { this.i = 0; }

    public static void main(String[] args) {
        System.out.println("*** The Vanishing Default Constructor ***\n");

        // This WORKS — matches our custom constructor
        DefaultConstructorTrap ob1 = new DefaultConstructorTrap(25);
        System.out.println("ob1.i = " + ob1.i);

        // THE TRAP: The line below would NOT compile!
        // DefaultConstructorTrap ob2 = new DefaultConstructorTrap();
        // Error: "constructor DefaultConstructorTrap() is undefined"

        // Why? Because we defined DefaultConstructorTrap(int i),
        // the compiler no longer provides a free default constructor.

        // Fix Option 2: Pass the required argument
        DefaultConstructorTrap ob2 = new DefaultConstructorTrap(0);
        System.out.println("ob2.i = " + ob2.i);

        System.out.println("\nRule: Define ANY constructor -> default disappears!");
    }
}
