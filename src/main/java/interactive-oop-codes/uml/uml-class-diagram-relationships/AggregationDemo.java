/**
 * UML Aggregation Relationship Demo
 *
 * Aggregation is a special kind of association: a whole-part
 * relationship where parts can exist independently of the whole.
 *
 * UML Notation: solid line with hollow (unfilled) diamond
 * Diamond points to the "whole" class.
 *
 *   Department ◇———— Employee
 *   (whole)          (part — can exist without Department)
 *
 * Key property: parts survive if the whole is destroyed.
 * An Employee can exist without a Department, and can move
 * between Departments.
 */

import java.util.ArrayList;
import java.util.List;

// ── Part class (exists independently) ────────────────────
class Employee {
    private String name;
    private String role;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() { return name; }
    public String getRole() { return role; }

    public String toString() {
        return name + " (" + role + ")";
    }
}

// ── Whole class (aggregates parts) ───────────────────────
// Department HAS Employees, but Employees can exist independently.
// If the Department is dissolved, the Employees still exist.
class Department {
    private String name;
    private List<Employee> employees;  // hollow diamond: aggregation

    public Department(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    // Employees are added from outside — not created by Department
    public void addEmployee(Employee emp) {
        employees.add(emp);
        System.out.println("  Added " + emp + " to " + name);
    }

    public void removeEmployee(Employee emp) {
        employees.remove(emp);
        System.out.println("  Removed " + emp + " from " + name);
    }

    public void listMembers() {
        System.out.println(name + " department (" + employees.size() + " members):");
        for (Employee e : employees) {
            System.out.println("    - " + e);
        }
    }

    // Dissolving the department does NOT destroy employees
    public List<Employee> dissolve() {
        System.out.println(name + " department dissolved!");
        List<Employee> survivors = new ArrayList<>(employees);
        employees.clear();
        return survivors;  // employees live on
    }
}

// ── Main ─────────────────────────────────────────────────
public class AggregationDemo {
    public static void main(String[] args) {
        // Employees exist independently of any department
        Employee alice = new Employee("Alice", "Developer");
        Employee bob   = new Employee("Bob", "Designer");
        Employee carol = new Employee("Carol", "Manager");

        System.out.println("=== Building Departments (Aggregation) ===");
        Department engineering = new Department("Engineering");
        Department design      = new Department("Design");

        engineering.addEmployee(alice);
        engineering.addEmployee(carol);
        design.addEmployee(bob);

        engineering.listMembers();
        design.listMembers();

        // An employee can move between departments (parts are shared/transferable)
        System.out.println("\n=== Transferring Employee ===");
        engineering.removeEmployee(carol);
        design.addEmployee(carol);
        design.listMembers();

        // Dissolving the whole — parts survive
        System.out.println("\n=== Dissolving Department ===");
        List<Employee> survivors = design.dissolve();
        System.out.println("Survivors still exist:");
        for (Employee e : survivors) {
            System.out.println("  " + e + " is still available.");
        }
    }
}
