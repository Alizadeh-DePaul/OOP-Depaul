// Information Hiding — using access modifiers to control visibility
// Demonstrates all four access levels and their effects

class Employee {
    public String name;            // Visible everywhere — NO protection
    String department;             // Package-private (default) — same package only
    protected double salary;       // Protected — same package + subclasses
    private String ssn;            // Private — this class ONLY

    public Employee(String name, String department, double salary, String ssn) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.ssn = ssn;
    }

    // Public method provides controlled access to private data
    public String getMaskedSSN() {
        return "***-**-" + ssn.substring(ssn.length() - 4);
    }

    // Package-private helper — only classes in same package can call
    void transferTo(String newDept) {
        System.out.println(name + " transferred from " + department + " to " + newDept);
        this.department = newDept;
    }

    // Private helper — internal use only
    private boolean isValidSSN(String ssn) {
        return ssn != null && ssn.matches("\\d{3}-\\d{2}-\\d{4}");
    }

    public void printInfo() {
        System.out.println("Name: " + name + " (public — anyone can read)");
        System.out.println("Dept: " + department + " (package-private)");
        System.out.println("SSN:  " + getMaskedSSN() + " (private — masked for safety)");
    }
}

class Manager extends Employee {
    private String title;

    public Manager(String name, String dept, double salary, String ssn, String title) {
        super(name, dept, salary, ssn);
        this.title = title;
    }

    public void showAccess() {
        System.out.println("=== What Manager (subclass) can access ===");
        System.out.println("name:       " + name);         // OK — public
        System.out.println("department: " + department);    // OK — package-private (same package)
        System.out.println("salary:     " + salary);        // OK — protected (subclass)
        // System.out.println(ssn);                         // ERROR — private to Employee
        System.out.println("ssn:        " + getMaskedSSN()); // OK — through public method
    }
}

public class InformationHiding {
    public static void main(String[] args) {
        Employee emp = new Employee("Alice", "Engineering", 95000, "123-45-6789");
        emp.printInfo();

        System.out.println();

        // Direct field access — shows visibility levels
        System.out.println("=== Direct access from same package ===");
        System.out.println("emp.name: " + emp.name);             // OK — public
        System.out.println("emp.department: " + emp.department);  // OK — package-private
        System.out.println("emp.salary: " + emp.salary);          // OK — protected (same package)
        // System.out.println(emp.ssn);                           // ERROR — private

        System.out.println();

        Manager mgr = new Manager("Bob", "Sales", 120000, "987-65-4321", "VP");
        mgr.showAccess();
    }
}
