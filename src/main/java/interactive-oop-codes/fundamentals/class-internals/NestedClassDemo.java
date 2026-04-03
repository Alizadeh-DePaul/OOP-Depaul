/**
 * Nested Classes — Static vs Non-Static (Inner)
 *
 * A nested class is a class defined inside another class.
 * - Static nested class: belongs to the outer class itself
 * - Non-static nested class (inner class): belongs to an instance
 */
public class NestedClassDemo {

    private String university = "DePaul";
    private static int totalDepartments = 5;

    // Static nested class — can access static members only
    static class Department {
        private String name;

        public Department(String name) {
            this.name = name;
        }

        public void display() {
            System.out.println("Department: " + name);
            // Can access static members of outer class
            System.out.println("Total departments: " + totalDepartments);
            // Cannot access: university (non-static)
        }
    }

    // Non-static nested class (inner class) — can access ALL members
    class Course {
        private String title;

        public Course(String title) {
            this.title = title;
        }

        public void display() {
            System.out.println("Course: " + title);
            // Can access instance members of outer class!
            System.out.println("University: " + university);
            System.out.println("Total departments: " + totalDepartments);
        }
    }

    public static void main(String[] args) {
        // Static nested class: no outer instance needed
        System.out.println("=== Static Nested Class ===");
        Department dept = new Department("Computer Science");
        dept.display();

        // Inner class: REQUIRES an outer instance
        System.out.println("\n=== Inner Class (via outer instance) ===");
        NestedClassDemo outer = new NestedClassDemo();
        Course course = outer.new Course("SE 450");
        course.display();
    }
}
/*
  Output:
  === Static Nested Class ===
  Department: Computer Science
  Total departments: 5

  === Inner Class (via outer instance) ===
  Course: SE 450
  University: DePaul
  Total departments: 5
*/
