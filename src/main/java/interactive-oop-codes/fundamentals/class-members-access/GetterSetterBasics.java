public class GetterSetterBasics {
    private String name;
    private int age;
    private final String id; // read-only — no setter

    // Constructor
    public GetterSetterBasics(String id, String name, int age) {
        this.id = id;
        setName(name);  // use setter for validation
        setAge(age);    // use setter for validation
    }

    // --- Getter: read access ---
    public String getName() { return name; }
    public int getAge()     { return age; }
    public String getId()   { return id; } // read-only: getter only, no setter

    // --- Setter: write access with validation ---
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Age must be between 0 and 150");
        }
        this.age = age;
    }

    public static void main(String[] args) {
        System.out.println("*** Getter & Setter Methods ***\n");

        GetterSetterBasics student = new GetterSetterBasics("S001", "Alice", 20);

        // Using getters
        System.out.println("ID:   " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Age:  " + student.getAge());

        // Using setter — controlled modification
        student.setName("Alice Johnson");
        student.setAge(21);
        System.out.println("\nAfter update:");
        System.out.println("Name: " + student.getName());
        System.out.println("Age:  " + student.getAge());

        // ID has no setter — it's read-only!
        // student.setId("S999"); // This method doesn't exist!

        // Setter validation in action
        System.out.println("\n--- Validation Demo ---");
        try {
            student.setAge(-5); // Invalid!
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        try {
            student.setName(""); // Invalid!
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        System.out.println("\n--- Benefits of Getters & Setters ---");
        System.out.println("1. Controlled access — validate before setting");
        System.out.println("2. Read-only fields — provide getter only (e.g., id)");
        System.out.println("3. Write-only — provide setter only (e.g., password)");
        System.out.println("4. Data security — reject invalid values");
    }
}
