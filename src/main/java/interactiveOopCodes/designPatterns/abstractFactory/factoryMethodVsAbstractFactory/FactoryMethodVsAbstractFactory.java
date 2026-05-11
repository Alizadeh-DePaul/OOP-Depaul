package interactiveOopCodes.designPatterns.abstractFactory.factoryMethodVsAbstractFactory;

/**
 * Side-by-side comparison of the two sibling creational patterns.
 *
 *   FACTORY METHOD:   ONE product type, decided by ONE polymorphic
 *                     method on a Creator that subclasses override.
 *                     -> uses INHERITANCE on the Creator side.
 *
 *   ABSTRACT FACTORY: A FAMILY of products, created by MULTIPLE methods
 *                     on an AbstractFactory interface. Each concrete
 *                     factory produces one complete family.
 *                     -> uses COMPOSITION (factory is injected); often
 *                        internally USES Factory Method to implement
 *                        each create*() method.
 *
 * Run main() to see both in action and contrast their output structure.
 */
public class FactoryMethodVsAbstractFactory {

    public static void main(String[] args) {
        System.out.println("==== Factory Method (one product per Creator) ====");
        // Creator is the variation point. Choose Creator subclass -> product type.
        Logistics road = new RoadLogistics();
        Logistics sea  = new SeaLogistics();
        road.planDelivery();   // dispatches Truck via createTransport()
        sea.planDelivery();    // dispatches Ship  via createTransport()

        System.out.println();
        System.out.println("==== Abstract Factory (family per Factory) ====");
        // Factory is the variation point. Inject factory -> whole family.
        Mechanic m = new Mechanic();
        m.workshift(new GasFactory());      // Gas mower + gas trimmer
        m.workshift(new ElectricFactory()); // Electric mower + electric trimmer
    }
}

/* ============================ FACTORY METHOD ============================ */

interface Transport { void deliver(); }
class Truck implements Transport { public void deliver() { System.out.println("  [FM] truck rolling on road"); } }
class Ship  implements Transport { public void deliver() { System.out.println("  [FM] ship sailing on sea");   } }

abstract class Logistics {
    public final void planDelivery() {
        Transport t = createTransport();   // <-- ONE factory method
        t.deliver();
    }
    protected abstract Transport createTransport();
}
class RoadLogistics extends Logistics { protected Transport createTransport() { return new Truck(); } }
class SeaLogistics  extends Logistics { protected Transport createTransport() { return new Ship();  } }

/* ============================ ABSTRACT FACTORY ========================== */

interface Mower   { void cut();  }
interface Trimmer { void trim(); }
class GasMower        implements Mower   { public void cut()  { System.out.println("  [AF] gas mower    - vroom");   } }
class GasTrimmer      implements Trimmer { public void trim() { System.out.println("  [AF] gas trimmer  - vrrr");    } }
class ElectricMower   implements Mower   { public void cut()  { System.out.println("  [AF] elec mower   - whirr");   } }
class ElectricTrimmer implements Trimmer { public void trim() { System.out.println("  [AF] elec trimmer - zzzz");    } }

interface LandscapingFactory {                    // <-- MULTIPLE create methods
    Mower   createMower();
    Trimmer createTrimmer();
}
class GasFactory implements LandscapingFactory {
    public Mower   createMower()   { return new GasMower();   }
    public Trimmer createTrimmer() { return new GasTrimmer(); }
}
class ElectricFactory implements LandscapingFactory {
    public Mower   createMower()   { return new ElectricMower();   }
    public Trimmer createTrimmer() { return new ElectricTrimmer(); }
}

class Mechanic {
    void workshift(LandscapingFactory shop) {
        shop.createMower().cut();
        shop.createTrimmer().trim();
    }
}
