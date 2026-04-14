package interactiveOopCodes.fundamentals.oopEssentials.constructorOverloading;

public class ConstructorOverloading {
    int i;

    // No-arg constructor delegates to parameterized constructor
    ConstructorOverloading() {
        this(5);  // Constructor chaining — this() MUST be first statement!
        // In Java, this(5) calls ConstructorOverloading(5)
        // Other languages may not support this construct
    }

    // Parameterized constructor — does the actual initialization
    public ConstructorOverloading(int i) {
        this.i = i;  // this.i = instance variable, i = parameter
    }

    // Alternative: if parameter name differs, 'this' is optional
    // ConstructorOverloading(int myInteger) {
    //     i = myInteger;  // No collision, so 'this' not needed
    // }

    public static void main(String[] args) {
        System.out.println("*** Constructor Overloading Demo ***");

        ConstructorOverloading obA = new ConstructorOverloading();
        ConstructorOverloading obB = new ConstructorOverloading(75);

        System.out.println("obA.i = " + obA.i);  // 5 (from this(5))
        System.out.println("obB.i = " + obB.i);  // 75

        System.out.println("\nKey: this(5) in no-arg calls the int constructor");
    }
}
