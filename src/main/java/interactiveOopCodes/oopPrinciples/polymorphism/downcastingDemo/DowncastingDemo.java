package interactiveOopCodes.oopPrinciples.polymorphism.downcastingDemo;

/**
 * Downcasting — Risky Explicit Casting to Subtype
 *
 * Downcasting narrows a parent reference back to a child type.
 * It requires an explicit cast and can fail at runtime with
 * ClassCastException if the actual object isn't the target type.
 * Always use instanceof before downcasting for safety.
 *
 * Vahid Alizadeh — DePaul University — SE 450/350
 */
class Shape {
    void draw() {
        System.out.println("Drawing a shape");
    }
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a circle");
    }

    void getRadius() {
        System.out.println("Radius: 5.0");
    }
}

class Square extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a square");
    }

    void getSide() {
        System.out.println("Side: 4.0");
    }
}

class DowncastingDemo {
    public static void main(String[] args) {
        System.out.println("=== Downcasting Demo ===\n");

        // Step 1: Upcast Circle to Shape
        Shape shape = new Circle();
        shape.draw();  // Circle's draw()
        // shape.getRadius();  // ERROR: Shape doesn't define getRadius()

        // Step 2: Downcast back to Circle — SAFE (actual object IS Circle)
        System.out.println("\n--- Safe downcast ---");
        Circle circle = (Circle) shape;  // Explicit cast required
        circle.draw();
        circle.getRadius();  // Now accessible!

        // Step 3: UNSAFE downcast — actual object is Circle, not Square!
        System.out.println("\n--- Unsafe downcast ---");
        try {
            Square sq = (Square) shape;  // Compiles, but RUNTIME ERROR!
            sq.getSide();
        } catch (ClassCastException e) {
            System.out.println("ClassCastException: " + e.getMessage());
        }

        // Step 4: Safe pattern — always check with instanceof first
        System.out.println("\n--- Safe pattern with instanceof ---");
        Shape[] shapes = { new Circle(), new Square(), new Shape() };
        for (Shape s : shapes) {
            s.draw();
            if (s instanceof Circle) {
                Circle c = (Circle) s;
                c.getRadius();
            } else if (s instanceof Square) {
                Square sq = (Square) s;
                sq.getSide();
            }
        }

        // Step 5: Java 16+ pattern matching (instanceof with binding)
        System.out.println("\n--- Pattern matching (Java 16+) ---");
        for (Shape s : shapes) {
            if (s instanceof Circle c) {       // Cast + assign in one step
                c.getRadius();
            } else if (s instanceof Square sq) {
                sq.getSide();
            } else {
                System.out.println("Plain shape — no special methods");
            }
        }
    }
}
