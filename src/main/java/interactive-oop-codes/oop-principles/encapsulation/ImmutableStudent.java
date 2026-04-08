// Read-Only & Write-Only Classes — controlling data access patterns
// Immutable class (read-only) and PasswordHolder (write-only)

import java.util.*;

// READ-ONLY: Immutable class — strongest form of encapsulation
// Recipe: final class, private final fields, no setters, defensive copies
final class Student {
    private final String name;
    private final int id;
    private final List<String> courses;

    public Student(String name, int id, List<String> courses) {
        this.name = name;
        this.id = id;
        this.courses = List.copyOf(courses);  // Defensive copy — unmodifiable
    }

    public String getName() { return name; }
    public int getId() { return id; }
    public List<String> getCourses() { return courses; }  // Already unmodifiable

    public String toString() {
        return name + " (#" + id + "): " + courses;
    }
}

// WRITE-ONLY: Accepts data but never reveals it
class PasswordHolder {
    private int hashedPassword;

    public void setPassword(String plaintext) {
        this.hashedPassword = plaintext.hashCode();  // Simplified hash
        System.out.println("Password set (hash stored, plaintext discarded)");
    }

    public boolean matches(String attempt) {
        return attempt.hashCode() == hashedPassword;
    }

    // NO getter for hashedPassword — write-only by design
}

public class ImmutableStudent {
    public static void main(String[] args) {
        // === Read-Only (Immutable) ===
        System.out.println("=== Immutable Student (Read-Only) ===");
        List<String> originalCourses = new ArrayList<>(Arrays.asList("SE 450", "SE 350"));
        Student student = new Student("Alice", 101, originalCourses);

        System.out.println("Student: " + student);

        // Try to break encapsulation by modifying the original list
        originalCourses.add("CSC 100");
        System.out.println("Modified original list: " + originalCourses);
        System.out.println("Student courses unchanged: " + student.getCourses());

        // Try to modify through the getter
        try {
            student.getCourses().add("HACKED");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify: " + e.getClass().getSimpleName());
        }

        System.out.println();

        // === Write-Only ===
        System.out.println("=== PasswordHolder (Write-Only) ===");
        PasswordHolder pw = new PasswordHolder();
        pw.setPassword("secret123");

        System.out.println("Correct password?  " + pw.matches("secret123"));
        System.out.println("Wrong password?    " + pw.matches("wrong"));
        // pw.getPassword()  — no such method exists! Write-only by design
    }
}
