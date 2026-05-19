package interactiveOopCodes.designPatterns.adapter.roundSquarePegClassAdapter;

/**
 * Adapter Pattern - Round Hole / Square Peg (CLASS ADAPTER variant).
 *
 *   Same scenario as the Object Adapter version, but the Adapter EXTENDS
 *   SquarePeg (inheriting getWidth() for free) AND implements IRoundPeg. No
 *   composition field; the adaptee's data lives directly inside the adapter.
 *
 *   CLASS ADAPTER = extends Adaptee + implements Target.
 *
 *   Works in Java because IRoundPeg is an INTERFACE. If IRoundPeg were a class,
 *   you could not extend both SquarePeg and IRoundPeg - Java forbids multiple
 *   class inheritance. That is the central restriction on Class Adapters in
 *   single-inheritance languages.
 */
public class RoundSquarePegClassAdapter {

    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5.0);

        // ── CLASS ADAPTER: a single object that IS-A IRoundPeg AND IS-A SquarePeg ──
        SquarePegClassAdapter smallAdapted = new SquarePegClassAdapter(2.0);
        SquarePegClassAdapter bigAdapted   = new SquarePegClassAdapter(20.0);

        System.out.printf("Small square-via-class-adapter (w=2)  -> hole (r=5)?  %s   (radius=%.2f)%n",
            hole.fits(smallAdapted), smallAdapted.getRadius());
        System.out.printf("Big square-via-class-adapter   (w=20) -> hole (r=5)?  %s   (radius=%.2f)%n",
            hole.fits(bigAdapted),   bigAdapted.getRadius());

        // Side effect of class adapters: the adapter IS-A SquarePeg too.
        SquarePeg asSquare = smallAdapted;
        System.out.println("Same adapter object as a SquarePeg, width = " + asSquare.getWidth());
    }
}

/** TARGET - must be an interface for Class Adapter to be possible in Java. */
interface IRoundPeg {
    double getRadius();
}

/** ADAPTEE - the legacy class with the wrong-shape interface. */
class SquarePeg {
    private final double width;
    public SquarePeg(double width) { this.width = width; }
    public double getWidth() { return width; }
}

/**
 * CLASS ADAPTER - extends SquarePeg (gets getWidth() for free)
 *                 implements IRoundPeg (so RoundHole accepts it).
 */
class SquarePegClassAdapter extends SquarePeg implements IRoundPeg {

    public SquarePegClassAdapter(double width) {
        super(width);
    }

    @Override
    public double getRadius() {
        return getWidth() * Math.sqrt(2) / 2;     // inherited method, then adapt
    }
}

/** CLIENT - sees only IRoundPeg. */
class RoundHole {
    private final double radius;
    public RoundHole(double radius) { this.radius = radius; }
    public boolean fits(IRoundPeg peg) { return peg.getRadius() <= this.radius; }
}
