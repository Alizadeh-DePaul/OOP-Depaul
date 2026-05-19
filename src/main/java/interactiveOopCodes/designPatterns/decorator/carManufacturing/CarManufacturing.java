package interactiveOopCodes.designPatterns.decorator.carManufacturing;

/**
 * Decorator Pattern - Car Manufacturing example (Intent).
 *
 *   A BasicCar rolls off the assembly line. The customer then chooses
 *   to upgrade it to Sports trim or Luxury trim - or BOTH, in any order.
 *   Each upgrade WRAPS the underlying car: it calls the wrapped car's
 *   assemble() first, then adds its own assembly steps on top.
 *
 *   This shows the pattern's INTENT: add responsibilities to a single
 *   object dynamically, without affecting other objects of the same type.
 *   No subclassing combinatorial explosion - just wrap.
 */
public class CarManufacturing {

    public static void main(String[] args) {
        System.out.println("-- a plain BasicCar --");
        Car basic = new BasicCar();
        basic.assemble();

        System.out.println("\n-- BasicCar with the Sports upgrade --");
        Car sports = new SportsCar(new BasicCar());
        sports.assemble();

        System.out.println("\n-- BasicCar with Sports AND Luxury upgrades --");
        Car sportsLuxury = new LuxuryCar(new SportsCar(new BasicCar()));
        sportsLuxury.assemble();

        System.out.println("\n-- same upgrades, different order: Luxury inside, Sports outside --");
        Car luxurySports = new SportsCar(new LuxuryCar(new BasicCar()));
        luxurySports.assemble();
    }
}

/* Component - the shared interface */
interface Car {
    void assemble();
}

/* ConcreteComponent - the no-frills base car */
class BasicCar implements Car {
    @Override
    public void assemble() {
        System.out.println("  Assembling BasicCar: chassis, engine, wheels");
    }
}

/* Decorator - IS-A Car AND HAS-A Car */
abstract class CarDecorator implements Car {
    protected final Car wrapped;

    protected CarDecorator(Car wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void assemble() {
        wrapped.assemble();
    }
}

/* ConcreteDecorator - the Sports trim upgrade */
class SportsCar extends CarDecorator {
    public SportsCar(Car wrapped) {
        super(wrapped);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("  Adding Sports trim: spoiler, racing seats, sport exhaust");
    }
}

/* ConcreteDecorator - the Luxury trim upgrade */
class LuxuryCar extends CarDecorator {
    public LuxuryCar(Car wrapped) {
        super(wrapped);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("  Adding Luxury trim: leather seats, premium audio, sunroof");
    }
}
