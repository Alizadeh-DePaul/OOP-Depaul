// Class declaration — the blueprint
public class Student {

    // === ATTRIBUTES (State) ===
    private String name;
    private int age;
    private double gpa;

    // === CONSTRUCTOR (Initialization) ===
    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    // === METHODS (Behavior) ===
    public void study(String subject) {
        System.out.println(name + " is studying " + subject);
    }

    public boolean isHonorRoll() {
        return gpa >= 3.5;
    }

    public String getInfo() {
        return name + " (Age: " + age + ", GPA: " + gpa + ")";
    }
}
