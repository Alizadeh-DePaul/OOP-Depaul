package interactiveOopCodes.fundamentals.oopBuildingBlocks.objectDemo;

public class ObjectDemo {
    public static void main(String[] args) {
        // Step 1: Declaration — create a reference (no memory yet)
        Student student1;

        // Step 2 & 3: Instantiation + Initialization
        student1 = new Student("Alice", 20, 3.8);

        // Or all in one line:
        Student student2 = new Student("Bob", 22, 3.2);

        // Each object has its own STATE
        System.out.println(student1.getInfo());
        // Output: Alice (Age: 20, GPA: 3.8)

        System.out.println(student2.getInfo());
        // Output: Bob (Age: 22, GPA: 3.2)

        // Each object has its own BEHAVIOR
        student1.study("Data Structures");
        // Output: Alice is studying Data Structures

        // IDENTITY: student1 and student2 are different objects
        System.out.println(student1 == student2);
        // Output: false
    }
}

// Student class — see Student.java for the full annotated version
class Student {
    private String name;
    private int age;
    private double gpa;

    Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    void study(String subject) {
        System.out.println(name + " is studying " + subject);
    }

    String getInfo() {
        return name + " (Age: " + age + ", GPA: " + gpa + ")";
    }
}
