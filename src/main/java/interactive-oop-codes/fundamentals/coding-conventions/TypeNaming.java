/**
 * Class, Enum, Interface & Annotation Naming
 *
 * Rule: UpperCamelCase (PascalCase)
 * - Each word capitalized, no separators
 * - Nouns for classes: Student, ArrayList, StringBuilder
 * - Adjectives/verbs for interfaces: Runnable, Serializable, Comparable
 * - Singular nouns for enums: Color, DayOfWeek, Status
 * - Annotations: @Override, @Deprecated, @FunctionalInterface
 */
public class TypeNaming {

    // GOOD class names — UpperCamelCase, descriptive nouns
    // class Student { }
    // class BankAccount { }
    // class HttpRequestHandler { }

    // BAD class names
    // class student { }          // lowercase start
    // class bank_account { }     // snake_case
    // class HTTPREQUESTHANDLER {} // all caps, no camel case

    // GOOD interface names — adjectives or capabilities
    interface Printable {
        void print();
    }

    interface Serializable {
        byte[] serialize();
    }

    // GOOD enum names — UpperCamelCase, singular
    enum Priority {
        LOW, MEDIUM, HIGH, CRITICAL
    }

    enum HttpStatus {
        OK(200), NOT_FOUND(404), SERVER_ERROR(500);

        private final int code;
        HttpStatus(int code) { this.code = code; }
        public int getCode() { return code; }
    }

    // GOOD annotation (shown as usage)
    // @Override, @Deprecated, @SuppressWarnings

    // Example class following conventions
    static class StudentRecord implements Printable {
        private String fullName;
        private Priority enrollmentPriority;

        public StudentRecord(String fullName, Priority priority) {
            this.fullName = fullName;
            this.enrollmentPriority = priority;
        }

        @Override
        public void print() {
            System.out.println(fullName + " [" + enrollmentPriority + "]");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Type Naming Conventions ===\n");

        System.out.println("Classes (nouns, UpperCamelCase):");
        System.out.println("  GOOD: Student, BankAccount, HttpRequestHandler");
        System.out.println("  BAD:  student, bank_account, HTTPREQUESTHANDLER\n");

        System.out.println("Interfaces (adjectives/abilities):");
        System.out.println("  GOOD: Runnable, Serializable, Comparable, Iterable");
        System.out.println("  BAD:  runnable, I_Serializable, IComparable\n");

        System.out.println("Enums (singular nouns, UpperCamelCase):");
        System.out.println("  GOOD: Color, DayOfWeek, Priority, HttpStatus");
        System.out.println("  BAD:  Colors, DAYS_OF_WEEK, http_status\n");

        System.out.println("Annotations (@UpperCamelCase):");
        System.out.println("  GOOD: @Override, @Deprecated, @FunctionalInterface");
        System.out.println("  BAD:  @override, @DEPRECATED, @functional_interface\n");

        // Demo
        StudentRecord student = new StudentRecord("Alice Johnson", Priority.HIGH);
        student.print();

        System.out.println("HTTP 404 = " + HttpStatus.NOT_FOUND.getCode());
    }
}
/*
  Output:
  === Type Naming Conventions ===

  Classes (nouns, UpperCamelCase):
    GOOD: Student, BankAccount, HttpRequestHandler
    BAD:  student, bank_account, HTTPREQUESTHANDLER

  Interfaces (adjectives/abilities):
    GOOD: Runnable, Serializable, Comparable, Iterable
    BAD:  runnable, I_Serializable, IComparable

  Enums (singular nouns, UpperCamelCase):
    GOOD: Color, DayOfWeek, Priority, HttpStatus
    BAD:  Colors, DAYS_OF_WEEK, http_status

  Annotations (@UpperCamelCase):
    GOOD: @Override, @Deprecated, @FunctionalInterface
    BAD:  @override, @DEPRECATED, @functional_interface

  Alice Johnson [HIGH]
  HTTP 404 = 404
*/
