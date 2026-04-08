// Encapsulation vs Abstraction — complementary but distinct principles
// Abstraction = WHAT (interface design)   Encapsulation = HOW (data protection)

// ABSTRACTION: defines WHAT a shape can do (the contract)
interface Shape {
    double area();
    String describe();
}

// ENCAPSULATION: protects HOW Circle stores and manages its data
class Circle implements Shape {
    private double radius;  // Encapsulated — cannot set to negative

    public Circle(double radius) {
        if (radius < 0) throw new IllegalArgumentException("Radius cannot be negative");
        this.radius = radius;
    }

    // Abstraction: client calls area() without knowing the formula
    // Encapsulation: radius is private, formula is internal
    public double area() {
        return Math.PI * radius * radius;
    }

    public String describe() {
        return "Circle (r=" + radius + ")";
    }

    public double getRadius() { return radius; }
}

class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width < 0 || height < 0) throw new IllegalArgumentException("Dimensions cannot be negative");
        this.width = width;
        this.height = height;
    }

    public double area() {
        return width * height;
    }

    public String describe() {
        return "Rectangle (" + width + " x " + height + ")";
    }
}

public class EncapsulationVsAbstraction {
    // This method uses ABSTRACTION — it knows WHAT Shape can do
    // It does NOT know HOW each shape calculates area (ENCAPSULATION hides that)
    static void printShapeInfo(Shape shape) {
        System.out.println(shape.describe());
        System.out.printf("  Area: %.2f%n", shape.area());
    }

    public static void main(String[] args) {
        System.out.println("=== Abstraction: WHAT (the interface) ===");
        System.out.println("Shape interface defines: area(), describe()");
        System.out.println("Client code works with ANY Shape — doesn't care which\n");

        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);

        printShapeInfo(circle);     // Abstraction at work
        printShapeInfo(rectangle);  // Same interface, different implementation

        System.out.println("\n=== Encapsulation: HOW (the protection) ===");
        System.out.println("Circle's radius is private — cannot be set to -1");
        System.out.println("Rectangle's dimensions are private — enforced via constructor\n");

        // Encapsulation in action: invalid state prevented
        try {
            Circle bad = new Circle(-3);
        } catch (IllegalArgumentException e) {
            System.out.println("Encapsulation blocked: " + e.getMessage());
        }

        // Summary
        System.out.println("\n=== Key Distinction ===");
        System.out.println("Abstraction → steering wheel (simple interface for complex system)");
        System.out.println("Encapsulation → sealed engine (protects internals from tampering)");
        System.out.println("They work TOGETHER: abstraction decides what to show,");
        System.out.println("encapsulation enforces what to hide.");
    }
}
