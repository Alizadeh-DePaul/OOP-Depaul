/**
 * Private Method Rules — Cannot Override Private Methods
 *
 * Private methods are implicitly final — they belong exclusively
 * to the class that declares them. A subclass cannot see, access,
 * or override a private method. If a child defines a method with
 * the same name, it's a completely NEW method — not an override.
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Base {
    // Private method — invisible to subclasses
    private void secret() {
        System.out.println("Base.secret() — private, not overridable");
    }

    // Public method that calls the private method
    public void callSecret() {
        System.out.println("Base.callSecret() invokes:");
        secret();  // Always calls Base.secret(), even from a Sub object
    }
}

class Sub extends Base {
    // This is NOT an override — it's a brand new method in Sub.
    // @Override  // Uncommenting this causes: "method does not override"
    private void secret() {
        System.out.println("Sub.secret() — this is a NEW method, not an override");
    }

    public void callOwnSecret() {
        System.out.println("Sub.callOwnSecret() invokes:");
        secret();  // Calls Sub.secret() — its own private method
    }
}

class PrivateMethodRules {
    public static void main(String[] args) {
        System.out.println("=== Private Methods & Polymorphism ===\n");

        Sub sub = new Sub();

        // callSecret() is inherited from Base.
        // Inside Base.callSecret(), secret() calls Base.secret()
        // because private methods are bound at COMPILE TIME.
        System.out.println("1. sub.callSecret():");
        sub.callSecret();

        System.out.println("\n2. sub.callOwnSecret():");
        sub.callOwnSecret();

        System.out.println("\n3. Through parent reference:");
        Base ref = new Sub();
        ref.callSecret();  // Still calls Base.secret() inside
        // ref.callOwnSecret();  // Error: not defined in Base
    }
}
