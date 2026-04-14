package interactiveOopCodes.oopPrinciples.abstraction.abstractClassRules;

// Abstract Class Rules — Fields, access modifiers, constructors, and restrictions

abstract class Shape {
    // Abstract classes CAN have fields (any access modifier)
    private String name;
    protected double scale = 1.0;
    public static int shapeCount = 0;

    // Abstract classes CAN have constructors
    Shape(String name) {
        this.name = name;
        shapeCount++;
        System.out.println("Shape constructor called for: " + name);
    }

    // Abstract method — subclass MUST implement
    abstract double area();

    // Concrete methods — subclass may or may not override
    public void describe() {
        System.out.println(name + " with area = " + area());
    }

    // Any access modifier works in abstract class
    protected String getName() {
        return name;
    }

    private void internalLog() {
        System.out.println("[LOG] Shape created: " + name);
    }

    void logCreation() {
        internalLog();  // Private method accessible within the class
    }
}

class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double width, height;

    Rectangle(double width, double height) {
        super("Rectangle");
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }

    // Override concrete method — must provide at least same access
    @Override
    public void describe() {
        System.out.println("Rectangle " + width + "x" + height + " = " + area());
    }
}

public class AbstractClassRules {
    public static void main(String[] args) {
        System.out.println("=== Abstract Class Rules ===\n");

        Circle c = new Circle(5);
        c.describe();
        c.logCreation();

        System.out.println();

        Rectangle r = new Rectangle(4, 6);
        r.describe();

        System.out.println("\nTotal shapes created: " + Shape.shapeCount);

        // Cannot do: Shape s = new Shape("test"); // Compile error!
        // Cannot do: abstract + final on method  // Compile error!
        // Cannot do: constructor as abstract      // Compile error!

        System.out.println("\n--- Visibility Widening (package -> public) ---");
        // Rectangle.describe() widened from package-private to public
        Shape ref = new Rectangle(3, 7);
        ref.describe();
    }
}
