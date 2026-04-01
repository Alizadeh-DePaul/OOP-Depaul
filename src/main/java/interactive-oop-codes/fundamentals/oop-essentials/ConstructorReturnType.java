public class ConstructorReturnType {
    int value;

    // CONSTRUCTOR: same name as class, NO return type (not even void)
    // Constructors are NOT methods — they initialize new objects
    ConstructorReturnType(int value) {
        this.value = value;
        System.out.println("Constructor called! value = " + value);
    }

    // WARNING: This LOOKS like a constructor but is actually a METHOD.
    // Adding "void" makes it a regular method, not a constructor!
    void ConstructorReturnType() {
        System.out.println("I am a METHOD, not a constructor!");
        System.out.println("I have a return type: void");
    }

    public static void main(String[] args) {
        System.out.println("*** Constructor Return Type Demo ***");
        System.out.println("Is a constructor's return type void? NO!\n");

        // This calls the CONSTRUCTOR (no return type)
        ConstructorReturnType obj = new ConstructorReturnType(42);
        System.out.println("obj.value = " + obj.value);

        System.out.println();

        // This calls the METHOD named ConstructorReturnType (has void)
        obj.ConstructorReturnType();

        System.out.println("\nKey insight:");
        System.out.println("- Constructor: no return type at all");
        System.out.println("- void method with class name: legal but misleading!");
    }
}
