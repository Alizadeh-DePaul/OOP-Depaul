package interactiveOopCodes.designPatterns.factoryMethod.mowerSelection;

/**
 * MowerSelection -- domain use case from the SE 450/350 lecture.
 *
 *   Creators:  MowerStore (abstract) <- GasMowerStore,
 *                                        ElectricMowerStore,
 *                                        RoboticMowerStore
 *   Products:  Mower (interface)     <- GasMower, ElectricMower, RoboticMower
 *
 * A store's job is to sell the right mower for the customer's lawn.
 * Each specialty store overrides createMower() with its product type.
 * The orderMower() workflow is identical for every store -- only the
 * factory method differs.
 */
public class MowerSelection {

    public static void main(String[] args) {
        sell(new GasMowerStore(),       "Big Acre",   3500);
        sell(new ElectricMowerStore(),  "Suburban",    600);
        sell(new RoboticMowerStore(),   "Townhouse",   200);
    }

    private static void sell(MowerStore store, String lot, int sqm) {
        System.out.println("------- " + store.getClass().getSimpleName() + " -------");
        store.orderMower(lot, sqm);
        System.out.println();
    }
}

/* PRODUCT */
interface Mower {
    String describe();
    String cut(int sqm);
}

/* CONCRETE PRODUCTS */
class GasMower implements Mower {
    @Override public String describe() { return "Gas mower (6.5 HP, 21-inch deck)"; }
    @Override public String cut(int sqm) {
        return "Vroom! Cut " + sqm + " sqm in one tank.";
    }
}
class ElectricMower implements Mower {
    @Override public String describe() { return "Electric mower (40V battery, 19-inch deck)"; }
    @Override public String cut(int sqm) {
        return "Whirr! Cut " + sqm + " sqm on one charge.";
    }
}
class RoboticMower implements Mower {
    @Override public String describe() { return "Robotic mower (autonomous, scheduled)"; }
    @Override public String cut(int sqm) {
        return "Beep! Schedule set; will cut " + sqm + " sqm tonight.";
    }
}

/* CREATOR */
abstract class MowerStore {

    /** FACTORY METHOD -- each store sells its own kind of mower. */
    abstract Mower createMower();

    /** Template method -- same workflow for every store. */
    public void orderMower(String lot, int sqm) {
        Mower m = createMower();
        System.out.println("  1. customer arrives with lot '" + lot + "' (" + sqm + " sqm)");
        System.out.println("  2. delivered: " + m.describe());
        System.out.println("  3. " + m.cut(sqm));
    }
}

/* CONCRETE CREATORS */
class GasMowerStore extends MowerStore {
    @Override Mower createMower() { return new GasMower(); }
}
class ElectricMowerStore extends MowerStore {
    @Override Mower createMower() { return new ElectricMower(); }
}
class RoboticMowerStore extends MowerStore {
    @Override Mower createMower() { return new RoboticMower(); }
}
