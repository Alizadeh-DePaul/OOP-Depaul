package interactiveOopCodes.designPatterns.builder.telescopingConstructor;

/**
 * Problem demo — telescoping constructor explosion and the
 * "setter fix" that breaks consistency.
 *
 * A User with 2 required fields {name, email} and 4 optional fields
 * {age, phone, address, bio} has 2^4 = 16 valid combinations.
 *
 *   Approach 1 — telescoping constructors: write all 16 overloads.
 *                Adds optional field #5 → doubles to 32.
 *
 *   Approach 2 — constructor + setters: object exists in
 *                INCONSISTENT intermediate state between calls.
 *                Any thread, callback, or audit-log call during
 *                that window sees a half-built object.
 *
 *   Approach 3 — Builder: object jumps from non-existent → fully
 *                built in one atomic build() call. Final state is
 *                guaranteed consistent; the User can even be final
 *                (immutable).
 */
public class TelescopingConstructor {

    public static void main(String[] args) {
        System.out.println("=== Approach 1 — Telescoping Constructors ===");
        UserTelescoping u1 = new UserTelescoping("Alice", "alice@x.com");
        UserTelescoping u2 = new UserTelescoping("Bob",   "bob@x.com", 25);
        UserTelescoping u3 = new UserTelescoping("Carol", "carol@x.com", 30, "555-1234");
        UserTelescoping u4 = new UserTelescoping("Dave",  "dave@x.com", 0,  null, "1 Main St", "Hi");
        // Imagine 16 such constructor overloads. Adding optional #5 → 32.

        System.out.println("\n=== Approach 2 — Constructor + Setters (inconsistent) ===");
        UserSetters u5 = new UserSetters("Eve", "eve@x.com");
        // ⚠  Audit / callback / addToDatabase() called HERE sees a half-built User.
        u5.setAge(28);
        // ⚠  Window is still open — phone, address, bio are all null.
        u5.setPhone("555-9999");
        // Did we remember setBio()? No compile-time check.

        System.out.println("\n=== Approach 3 — Builder (atomic, consistent) ===");
        UserBuilt user = new UserBuilt.Builder("Frank", "frank@x.com")
                .age(35)
                .phone("555-0001")
                .address("99 Oak Ave")
                .bio("Senior engineer")
                .build();
        System.out.println(user);
        // user has been non-existent up until build() and fully built right after.
        // No intermediate state has ever escaped.
    }
}

/* ─── Approach 1: Telescoping constructors ─────────────────── */
class UserTelescoping {
    private final String name;
    private final String email;
    private final int    age;
    private final String phone;
    private final String address;
    private final String bio;

    public UserTelescoping(String name, String email) {
        this(name, email, 0, null, null, null);
    }
    public UserTelescoping(String name, String email, int age) {
        this(name, email, age, null, null, null);
    }
    public UserTelescoping(String name, String email, int age, String phone) {
        this(name, email, age, phone, null, null);
    }
    public UserTelescoping(String name, String email, int age, String phone, String address) {
        this(name, email, age, phone, address, null);
    }
    public UserTelescoping(String name, String email, int age, String phone, String address, String bio) {
        this.name = name; this.email = email; this.age = age;
        this.phone = phone; this.address = address; this.bio = bio;
        System.out.printf("Built UserTelescoping(name=%s, email=%s, age=%d, phone=%s, address=%s, bio=%s)%n",
                name, email, age, phone, address, bio);
    }
    // …and 11 more overloads for the remaining {age,phone,address,bio} combinations.
}

/* ─── Approach 2: Constructor + setters (inconsistent state) ── */
class UserSetters {
    private String name;
    private String email;
    private int    age;
    private String phone;
    private String address;
    private String bio;

    public UserSetters(String name, String email) {
        this.name  = name;
        this.email = email;
        System.out.printf("Created UserSetters(%s, %s) — age/phone/address/bio NOT set yet%n",
                name, email);
    }
    public void setAge(int age)         { this.age = age; }
    public void setPhone(String phone)  { this.phone = phone; }
    public void setAddress(String addr) { this.address = addr; }
    public void setBio(String bio)      { this.bio = bio; }
}

/* ─── Approach 3: Builder (atomic, immutable, consistent) ───── */
class UserBuilt {
    private final String name, email, phone, address, bio;
    private final int age;

    private UserBuilt(Builder b) {
        this.name    = b.name;
        this.email   = b.email;
        this.age     = b.age;
        this.phone   = b.phone;
        this.address = b.address;
        this.bio     = b.bio;
    }

    @Override
    public String toString() {
        return String.format(
                "UserBuilt{name=%s, email=%s, age=%d, phone=%s, address=%s, bio=%s}",
                name, email, age, phone, address, bio);
    }

    public static class Builder {
        // required
        private final String name;
        private final String email;
        // optional
        private int    age;
        private String phone;
        private String address;
        private String bio;

        public Builder(String name, String email) {
            this.name  = name;
            this.email = email;
        }
        public Builder age(int v)        { this.age     = v; return this; }
        public Builder phone(String v)   { this.phone   = v; return this; }
        public Builder address(String v) { this.address = v; return this; }
        public Builder bio(String v)     { this.bio     = v; return this; }
        public UserBuilt build()         { return new UserBuilt(this); }
    }
}
