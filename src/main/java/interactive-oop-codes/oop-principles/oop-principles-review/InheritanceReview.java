// Inheritance Review — IS-A, extends, super, constructor chaining
// Shows how inheritance ENABLES polymorphism through shared type hierarchy

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        System.out.println("Employee constructor: " + name);
    }

    public String getName() { return name; }
    public double getSalary() { return salary; }

    public String role() { return "Employee"; }

    public String toString() {
        return role() + ": " + name + " ($" + salary + ")";
    }
}

class Manager extends Employee {
    private int teamSize;

    public Manager(String name, double salary, int teamSize) {
        super(name, salary);  // Constructor chaining — must call super first
        this.teamSize = teamSize;
        System.out.println("Manager constructor: team of " + teamSize);
    }

    public int getTeamSize() { return teamSize; }

    @Override
    public String role() { return "Manager"; }
}

class Director extends Manager {
    private String department;

    public Director(String name, double salary, int teamSize, String department) {
        super(name, salary, teamSize);  // Chains through Manager → Employee
        this.department = department;
        System.out.println("Director constructor: " + department);
    }

    @Override
    public String role() { return "Director"; }
}

public class InheritanceReview {
    public static void main(String[] args) {
        System.out.println("=== Constructor Chaining (3 levels) ===");
        Director dir = new Director("Alice", 150000, 25, "Engineering");
        // Output order: Employee → Manager → Director (top-down)

        System.out.println("\n=== IS-A Relationship ===");
        System.out.println(dir instanceof Director);   // true
        System.out.println(dir instanceof Manager);    // true — Director IS-A Manager
        System.out.println(dir instanceof Employee);   // true — Director IS-A Employee

        System.out.println("\n=== Inheritance Enables Polymorphism ===");
        Employee emp = dir;  // Upcasting — Director treated as Employee
        System.out.println(emp.toString());  // Calls Director.role() — dynamic dispatch!
        // toString() is inherited from Employee, but role() is overridden in Director
    }
}
