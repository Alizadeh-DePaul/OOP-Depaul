package interactiveOopCodes.designPatterns.adapter.roundSquarePegObjectAdapter;

/**
 * Adapter Pattern - Round Hole / Square Peg (OBJECT ADAPTER variant).
 *
 *   A RoundHole only accepts things that look like an IRoundPeg (it cares about
 *   the peg's radius). A SquarePeg has no notion of "radius" - it has a width.
 *   The adapter wraps a SquarePeg and computes the effective radius (half the
 *   square's diagonal = w * sqrt(2) / 2) so the SquarePeg can be tried in the
 *   RoundHole.
 *
 *   OBJECT ADAPTER = implements Target + HAS-A Adaptee (composition).
 *
 *   This is the canonical Adapter example used in Refactoring.Guru. Notice the
 *   adapter does REAL WORK - it does not just rename a method, it performs a
 *   geometric translation.
 */
public class RoundSquarePegObjectAdapter {

    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5.0);

        // ── Native: a real round peg, no adapter needed ──
        IRoundPeg rPeg = new RoundPeg(5.0);
        System.out.printf("Round peg (r=5) fits hole (r=5)?         %s%n", hole.fits(rPeg));

        // ── Square pegs (Adaptees) - the wrong shape for the Client ──
        SquarePeg smallSquare = new SquarePeg(2.0);
        SquarePeg bigSquare   = new SquarePeg(20.0);

        //   hole.fits(smallSquare);   // X compile error - IRoundPeg expected

        // ── OBJECT ADAPTER: implements IRoundPeg + HAS-A SquarePeg ──
        IRoundPeg smallAdapted = new SquarePegAdapter(smallSquare);
        IRoundPeg bigAdapted   = new SquarePegAdapter(bigSquare);

        System.out.printf("Small square (w=2) -> hole (r=5)?         %s   (effective radius=%.2f)%n",
            hole.fits(smallAdapted), smallAdapted.getRadius());
        System.out.printf("Big square   (w=20) -> hole (r=5)?         %s   (effective radius=%.2f)%n",
            hole.fits(bigAdapted), bigAdapted.getRadius());
    }
}

/** TARGET - the interface RoundHole was written against. */
interface IRoundPeg {
    double getRadius();
}

/** A native peg that already speaks the Target language. */
class RoundPeg implements IRoundPeg {
    private final double radius;
    public RoundPeg(double radius) { this.radius = radius; }
    @Override
    public double getRadius() { return radius; }
}

/** ADAPTEE - knows only width, not radius. Cannot be modified (legacy class). */
class SquarePeg {
    private final double width;
    public SquarePeg(double width) { this.width = width; }
    public double getWidth() { return width; }
}

/**
 * OBJECT ADAPTER - IS-A IRoundPeg, HAS-A SquarePeg.
 * The math: the smallest enclosing circle around a square of side w has
 * radius = w * sqrt(2) / 2  (half the square's diagonal).
 */
class SquarePegAdapter implements IRoundPeg {

    private final SquarePeg square;   // HAS-A

    public SquarePegAdapter(SquarePeg square) {
        this.square = square;
    }

    @Override
    public double getRadius() {
        return square.getWidth() * Math.sqrt(2) / 2;   // adapt: width -> radius
    }
}

/** CLIENT - sees only IRoundPeg. Knows nothing about SquarePeg or its width. */
class RoundHole {
    private final double radius;
    public RoundHole(double radius) { this.radius = radius; }

    public boolean fits(IRoundPeg peg) {
        return peg.getRadius() <= this.radius;
    }
}
