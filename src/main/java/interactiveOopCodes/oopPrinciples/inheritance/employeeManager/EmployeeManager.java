package interactiveOopCodes.oopPrinciples.inheritance.employeeManager;

/**
 * Inheritance Basics — Employee / Manager
 *
 * Demonstrates the core inheritance mechanism:
 * - Employee (superclass) defines common state and behavior
 * - Manager (subclass) extends Employee, adding new members
 * - "extends" keyword creates the IS-A relationship
 * - Subclass inherits all non-private members
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Employee {
    private long id;
    private String firstName;
    private String lastName;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String describe() {
        return "Employee #" + id + ": " + firstName + " " + lastName;
    }
}

class Manager extends Employee {
    private String department;

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String describe() {
        return "Manager of " + department + " — " + getFirstName() + " " + getLastName();
    }
}

public class EmployeeManager {
    public static void main(String[] args) {
        Manager mgr = new Manager();
        mgr.setId(1);
        mgr.setFirstName("Vahid");
        mgr.setLastName("Alizadeh");
        mgr.setDepartment("Engineering");

        // Manager IS-A Employee — inherited methods work
        System.out.println(mgr.describe());
        System.out.println("ID: " + mgr.getId());

        // Reference type: Employee, actual object: Manager
        Employee emp = mgr;
        System.out.println(emp.describe());
    }
}
