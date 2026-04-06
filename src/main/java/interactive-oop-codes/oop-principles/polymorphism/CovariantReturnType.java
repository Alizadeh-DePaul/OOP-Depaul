/**
 * Covariant Return Types in Method Overriding
 *
 * When overriding a method, the return type of the child's method
 * must be compatible with the parent's return type:
 * - Same type: always valid
 * - Subtype (covariant): valid since Java 5
 * - Unrelated type: compilation error
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Shape {
    double area() {
        return 0.0;
    }

    // Returns Shape — child can narrow this to a subtype
    Shape duplicate() {
        System.out.println("Shape.duplicate()");
        return new Shape();
    }
}

class Circle extends Shape {
    private double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * radius * radius;
    }

    // Covariant return: Circle is a subtype of Shape — VALID
    @Override
    Circle duplicate() {
        System.out.println("Circle.duplicate() -> covariant return");
        return new Circle(this.radius);
    }
}

class Rectangle extends Shape {
    private double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    double area() {
        return width * height;
    }

    // Covariant return: Rectangle is a subtype of Shape — VALID
    @Override
    Rectangle duplicate() {
        System.out.println("Rectangle.duplicate() -> covariant return");
        return new Rectangle(this.width, this.height);
    }

    // INVALID: String is not a subtype of Shape
    // @Override
    // String duplicate() { return "nope"; }  // Compilation Error!
}

class CovariantReturnType {
    public static void main(String[] args) {
        System.out.println("=== Covariant Return Types ===\n");

        Shape s = new Circle(5.0);
        Shape copy = s.duplicate();  // Returns Circle, stored as Shape
        System.out.println("Original area: " + s.area());
        System.out.println("Copy area:     " + copy.area());

        System.out.println();

        // Direct reference — no cast needed thanks to covariance
        Circle c = new Circle(3.0);
        Circle cCopy = c.duplicate();  // Returns Circle directly!
        System.out.println("Circle copy area: " + cCopy.area());
    }
}
