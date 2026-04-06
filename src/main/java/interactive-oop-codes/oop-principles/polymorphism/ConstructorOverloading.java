/**
 * Constructor Overloading — Multiple Ways to Create Objects
 *
 * Constructors can be overloaded just like methods.
 * Use this() to chain one constructor to another,
 * avoiding code duplication in initialization logic.
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Student {
    private String name;
    private int age;
    private String major;

    // Constructor 1: all fields
    Student(String name, int age, String major) {
        this.name = name;
        this.age = age;
        this.major = major;
        System.out.println("  -> Full constructor called");
    }

    // Constructor 2: name and age, default major
    Student(String name, int age) {
        this(name, age, "Undeclared");  // chains to Constructor 1
        System.out.println("  -> Two-arg constructor called");
    }

    // Constructor 3: name only, default age and major
    Student(String name) {
        this(name, 18);  // chains to Constructor 2 -> then to Constructor 1
        System.out.println("  -> One-arg constructor called");
    }

    // Constructor 4: no-arg, all defaults
    Student() {
        this("Unknown");  // chains to Constructor 3 -> 2 -> 1
        System.out.println("  -> No-arg constructor called");
    }

    void display() {
        System.out.println("Student{name='" + name
                + "', age=" + age + ", major='" + major + "'}");
    }
}

class ConstructorOverloading {
    public static void main(String[] args) {
        System.out.println("=== Constructor Overloading ===\n");

        System.out.println("1. new Student(\"Alice\", 21, \"CS\"):");
        Student s1 = new Student("Alice", 21, "CS");
        s1.display();

        System.out.println("\n2. new Student(\"Bob\", 20):");
        Student s2 = new Student("Bob", 20);
        s2.display();

        System.out.println("\n3. new Student(\"Carol\"):");
        Student s3 = new Student("Carol");
        s3.display();

        System.out.println("\n4. new Student():");
        Student s4 = new Student();
        s4.display();
    }
}
