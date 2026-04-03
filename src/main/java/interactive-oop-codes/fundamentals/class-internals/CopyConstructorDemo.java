/**
 * Copy Constructors & Object Copying
 *
 * Creating new instances from scratch is costly and error-prone.
 * Java does NOT support a default copy constructor (unlike C++).
 * You must write your own.
 *
 * This demo shows:
 * 1. Writing a copy constructor
 * 2. Shallow vs deep copy pitfall
 * 3. Proper deep copy
 */
import java.util.ArrayList;
import java.util.List;

public class CopyConstructorDemo {

    // === A class with mutable fields ===
    private String name;
    private int age;
    private List<String> courses;  // mutable reference type!

    // Regular constructor
    public CopyConstructorDemo(String name, int age) {
        this.name = name;
        this.age = age;
        this.courses = new ArrayList<>();
    }

    // SHALLOW copy constructor — copies the reference, not the list!
    public CopyConstructorDemo(CopyConstructorDemo other, boolean shallow) {
        this.name = other.name;      // String is immutable — safe
        this.age = other.age;        // primitive — safe
        this.courses = other.courses; // DANGER: shared reference!
    }

    // DEEP copy constructor — creates independent copies of mutable fields
    public CopyConstructorDemo(CopyConstructorDemo other) {
        this.name = other.name;
        this.age = other.age;
        this.courses = new ArrayList<>(other.courses);  // new list!
    }

    public void addCourse(String course) {
        courses.add(course);
    }

    public String toString() {
        return name + " (age " + age + ") courses=" + courses;
    }

    public static void main(String[] args) {
        // Original
        CopyConstructorDemo alice = new CopyConstructorDemo("Alice", 20);
        alice.addCourse("SE 450");
        alice.addCourse("SE 350");

        System.out.println("=== Original ===");
        System.out.println("Alice: " + alice);

        // Shallow copy — shares the courses list!
        System.out.println("\n=== Shallow Copy (DANGER) ===");
        CopyConstructorDemo shallowCopy = new CopyConstructorDemo(alice, true);
        shallowCopy.addCourse("CSC 301");  // modifies BOTH!
        System.out.println("Alice:  " + alice);
        System.out.println("Shallow: " + shallowCopy);
        System.out.println("Same list? " + (alice.courses == shallowCopy.courses));

        // Deep copy — independent courses list
        System.out.println("\n=== Deep Copy (SAFE) ===");
        CopyConstructorDemo original = new CopyConstructorDemo("Bob", 22);
        original.addCourse("SE 450");

        CopyConstructorDemo deepCopy = new CopyConstructorDemo(original);
        deepCopy.addCourse("CSC 301");  // only affects deepCopy
        System.out.println("Bob:  " + original);
        System.out.println("Deep: " + deepCopy);
        System.out.println("Same list? " + (original.courses == deepCopy.courses));
    }
}
/*
  Output:
  === Original ===
  Alice: Alice (age 20) courses=[SE 450, SE 350]

  === Shallow Copy (DANGER) ===
  Alice:  Alice (age 20) courses=[SE 450, SE 350, CSC 301]
  Shallow: Alice (age 20) courses=[SE 450, SE 350, CSC 301]
  Same list? true

  === Deep Copy (SAFE) ===
  Bob:  Bob (age 22) courses=[SE 450]
  Deep: Bob (age 22) courses=[SE 450, CSC 301]
  Same list? false
*/
