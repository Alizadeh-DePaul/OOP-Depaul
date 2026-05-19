package interactiveOopCodes.designPatterns.decorator.genericDecorator;

/**
 * Decorator Pattern - the canonical 4-role skeleton.
 *
 *   1) Component (interface)        : the contract everyone speaks
 *   2) ConcreteComponent (class)    : the default, no-frills object
 *   3) Decorator (abstract class)   : IS-A Component AND HAS-A Component
 *   4) ConcreteDecorator (class)    : adds behavior before/after delegating
 *
 * The defining mechanic: a decorator wraps another Component
 * (which itself can be a decorator), forming an onion at runtime.
 * Every call walks INward through the wraps via super.method(),
 * then unwinds OUTward as each layer contributes its work.
 */
public class GenericDecorator {

    public static void main(String[] args) {
        // The base object - no decoration
        Component plain = new ConcreteComponent();

        System.out.println("-- plain --");
        plain.operation();

        // One layer: A wraps plain
        Component a = new ConcreteDecoratorA(plain);
        System.out.println("\n-- A(plain) --");
        a.operation();

        // Two layers: B wraps A wraps plain
        Component b = new ConcreteDecoratorB(a);
        System.out.println("\n-- B(A(plain)) --");
        b.operation();

        // Swap the order: A wraps B wraps plain (NOT the same!)
        Component swapped = new ConcreteDecoratorA(new ConcreteDecoratorB(plain));
        System.out.println("\n-- A(B(plain)) -- order matters!");
        swapped.operation();
    }
}

/* 1) Component - the shared contract */
interface Component {
    void operation();
}

/* 2) ConcreteComponent - the no-frills default */
class ConcreteComponent implements Component {
    @Override
    public void operation() {
        System.out.println("  ConcreteComponent.operation()");
    }
}

/* 3) Decorator - IS-A Component AND HAS-A Component */
abstract class Decorator implements Component {
    protected final Component inner;

    protected Decorator(Component inner) {
        this.inner = inner;
    }

    @Override
    public void operation() {
        inner.operation();   // delegate - the propagation idiom
    }
}

/* 4a) ConcreteDecoratorA - adds behavior AFTER the inner call */
class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component inner) {
        super(inner);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("  [A] extra behavior after");
    }
}

/* 4b) ConcreteDecoratorB - wraps the inner call on both sides */
class ConcreteDecoratorB extends Decorator {
    public ConcreteDecoratorB(Component inner) {
        super(inner);
    }

    @Override
    public void operation() {
        System.out.println("  [B] before");
        super.operation();
        System.out.println("  [B] after");
    }
}
