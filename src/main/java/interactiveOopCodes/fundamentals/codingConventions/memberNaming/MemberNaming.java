package interactiveOopCodes.fundamentals.codingConventions.memberNaming;

/**
 * Method, Field, Constant & Variable Naming
 *
 * Methods and fields:     lowerCamelCase  (add, getName, isValid)
 * Constants (static final): UPPER_SNAKE   (MAX_VALUE, DEFAULT_NAME)
 * Local variables:        lowerCamelCase  (count, itemList, isReady)
 * Type parameters:        Single letter   (T, E, K, V)
 * Boolean methods:        is/has/can/should prefix
 */
public class MemberNaming {

    // === Constants: UPPER_SNAKE_CASE ===
    public static final int MAX_STUDENTS = 30;
    public static final double TAX_RATE = 0.08;
    public static final String DEFAULT_CAMPUS = "LOOP";

    // BAD constants:
    // public static final int maxStudents = 30;   // camelCase!
    // public static final int Max_Students = 30;  // mixed!

    // === Instance fields: lowerCamelCase ===
    private String firstName;
    private String lastName;
    private int enrollmentYear;
    private boolean activeStudent;

    // BAD fields:
    // private String FirstName;    // UpperCamelCase is for classes!
    // private String first_name;   // snake_case is for Python!
    // private int YEAR;            // UPPER is for constants!

    public MemberNaming(String firstName, String lastName, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.enrollmentYear = year;
        this.activeStudent = true;
    }

    // === Methods: lowerCamelCase, verb phrases ===
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void deactivate() {
        this.activeStudent = false;
    }

    // === Boolean methods: is/has/can/should prefix ===
    public boolean isActive() {
        return activeStudent;
    }

    public boolean hasGraduated() {
        return enrollmentYear <= 2022;
    }

    public boolean canEnroll() {
        return activeStudent && !hasGraduated();
    }

    // BAD boolean methods:
    // public boolean active() { ... }     // missing prefix
    // public boolean checkActive() { ... } // vague "check"

    public static void main(String[] args) {
        System.out.println("=== Member Naming Conventions ===\n");

        // Local variables: lowerCamelCase
        int studentCount = 0;
        String campusName = DEFAULT_CAMPUS;
        boolean isFirstDay = true;

        MemberNaming student = new MemberNaming("Alice", "Johnson", 2024);
        studentCount++;

        System.out.println("Constants (UPPER_SNAKE_CASE):");
        System.out.println("  MAX_STUDENTS = " + MAX_STUDENTS);
        System.out.println("  TAX_RATE = " + TAX_RATE);
        System.out.println("  DEFAULT_CAMPUS = " + DEFAULT_CAMPUS);

        System.out.println("\nFields (lowerCamelCase):");
        System.out.println("  firstName, lastName, enrollmentYear");

        System.out.println("\nMethods (lowerCamelCase, verb phrases):");
        System.out.println("  getFullName() = " + student.getFullName());

        System.out.println("\nBoolean methods (is/has/can prefix):");
        System.out.println("  isActive()     = " + student.isActive());
        System.out.println("  hasGraduated() = " + student.hasGraduated());
        System.out.println("  canEnroll()    = " + student.canEnroll());

        System.out.println("\nLocal variables (lowerCamelCase):");
        System.out.println("  studentCount = " + studentCount);
        System.out.println("  campusName = " + campusName);
        System.out.println("  isFirstDay = " + isFirstDay);
    }
}
/*
  Output:
  === Member Naming Conventions ===

  Constants (UPPER_SNAKE_CASE):
    MAX_STUDENTS = 30
    TAX_RATE = 0.08
    DEFAULT_CAMPUS = LOOP

  Fields (lowerCamelCase):
    firstName, lastName, enrollmentYear

  Methods (lowerCamelCase, verb phrases):
    getFullName() = Alice Johnson

  Boolean methods (is/has/can prefix):
    isActive()     = true
    hasGraduated() = false
    canEnroll()    = true

  Local variables (lowerCamelCase):
    studentCount = 1
    campusName = LOOP
    isFirstDay = true
*/
