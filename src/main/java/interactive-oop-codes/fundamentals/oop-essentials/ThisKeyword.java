public class ThisKeyword {
    int value;
    String name;

    // CONSTRUCTOR using 'this' to resolve naming collision
    ThisKeyword(int value, String name) {
        this.value = value;  // this.value = field, value = parameter
        this.name = name;
    }

    // BUG: Without 'this', parameter shadows the instance variable!
    // Java's = operator has RIGHT-TO-LEFT associativity:
    //   value = value  assigns parameter to itself (field unchanged!)
    void setBroken(int value) {
        value = value;  // Does NOTHING to the instance variable
    }

    // FIX: 'this' disambiguates field from parameter
    void setCorrect(int value) {
        this.value = value;  // Sets the FIELD to the parameter
    }

    // When names differ, 'this' is optional — no collision exists
    void setAlias(int v) {
        value = v;  // 'v' is unambiguous, 'this' not required
    }

    public static void main(String[] args) {
        System.out.println("*** The 'this' Keyword Demo ***\n");

        ThisKeyword obj = new ThisKeyword(0, "Demo");
        System.out.println("Initial value: " + obj.value);

        // The broken way — does nothing!
        obj.setBroken(42);
        System.out.println("After setBroken(42):  " + obj.value);  // Still 0

        // The correct way — uses 'this'
        obj.setCorrect(42);
        System.out.println("After setCorrect(42): " + obj.value);  // 42

        // No collision — works without 'this'
        obj.setAlias(99);
        System.out.println("After setAlias(99):   " + obj.value);  // 99

        System.out.println("\nRule: 'this' is only REQUIRED for namespace collision");
        System.out.println("Name: " + obj.name);
    }
}
