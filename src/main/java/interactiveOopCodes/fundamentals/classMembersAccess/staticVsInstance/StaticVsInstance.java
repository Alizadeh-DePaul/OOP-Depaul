package interactiveOopCodes.fundamentals.classMembersAccess.staticVsInstance;

public class StaticVsInstance {
    // Static (class) variable — shared by ALL instances
    static int studentCount = 0;

    // Instance variables — each object gets its own copy
    String name;
    int id;

    StaticVsInstance(String name) {
        this.name = name;
        studentCount++;         // increments the SHARED counter
        this.id = studentCount; // assigns unique ID from shared counter
    }

    void display() {
        System.out.println("Student #" + id + ": " + name);
    }

    // Static method — belongs to the class, not any instance
    static int getCount() {
        return studentCount;
    }

    public static void main(String[] args) {
        System.out.println("*** Static vs Instance Members ***\n");

        System.out.println("Students before creation: " + StaticVsInstance.getCount());

        StaticVsInstance s1 = new StaticVsInstance("Alice");
        StaticVsInstance s2 = new StaticVsInstance("Bob");
        StaticVsInstance s3 = new StaticVsInstance("Charlie");

        s1.display();
        s2.display();
        s3.display();

        System.out.println("\nTotal students (via class): " + StaticVsInstance.getCount());
        System.out.println("Total students (via s1):    " + s1.studentCount);
        System.out.println("Total students (via s2):    " + s2.studentCount);
        // All three print 3 — same static field!
    }
}
