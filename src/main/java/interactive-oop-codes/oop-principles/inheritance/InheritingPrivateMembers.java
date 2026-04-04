/**
 * Inheriting Private Members
 *
 * Demonstrates that private members ARE inherited but NOT accessible:
 * - Class B extends A which has a private field "secret"
 * - B inherits "secret" (it exists in B's object memory)
 * - But B cannot access "secret" directly — compile error
 * - Proof: obB.secret gives "private access" error, not "cannot find symbol"
 * - Access is possible only through public/protected methods of A
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class A {
    private int secret = 42;

    public int getSecret() {
        return secret;
    }
}

class B extends A {
    // B inherits 'secret' but CANNOT access it directly:
    // this.secret → Error: secret has private access in A
    //
    // If 'secret' were NOT inherited, the error would be:
    // "cannot find symbol" — but Java says "private access"
    // This PROVES the field exists in B, just not accessible.

    public void tryAccess() {
        // this.secret;  // COMPILE ERROR: secret has private access in A
        System.out.println("Via getter: " + getSecret());
    }
}

public class InheritingPrivateMembers {
    public static void main(String[] args) {
        B obj = new B();
        obj.tryAccess();

        // Also proving from outside:
        // obj.secret;  // Error: secret has private access in A
        System.out.println("Direct via getter: " + obj.getSecret());
    }
}
