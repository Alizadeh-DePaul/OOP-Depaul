package interactiveOopCodes.designPatterns.abstractFactory.furnitureShopApp;

/**
 * Abstract Factory — canonical Furniture Shop example.
 *
 * Matrix:  3 variants  (Modern / Victorian / ArtDeco)
 *        x 3 products  (Chair  / Sofa     / CoffeeTable)
 *        = 9 concrete products in 3 compatible FAMILIES.
 *
 * Key insight: client code (Showroom.furnishLivingRoom) NEVER mentions
 * a concrete product class or `new`. It only knows the abstract
 * FurnitureFactory interface and the 3 abstract product interfaces.
 * Swapping the factory swaps the whole family at once.
 */
public class FurnitureShopApp {

    public static void main(String[] args) {
        Showroom showroom = new Showroom();

        System.out.println("--- Modern family ---");
        showroom.furnishLivingRoom(new ModernFactory());

        System.out.println();
        System.out.println("--- Victorian family ---");
        showroom.furnishLivingRoom(new VictorianFactory());

        System.out.println();
        System.out.println("--- ArtDeco family ---");
        showroom.furnishLivingRoom(new ArtDecoFactory());
    }
}

/* =====================  ABSTRACT PRODUCTS  ===================== */

interface Chair       { String describe(); }
interface Sofa        { String describe(); }
interface CoffeeTable { String describe(); }

/* =====================  ABSTRACT FACTORY  ===================== */

interface FurnitureFactory {
    Chair       createChair();
    Sofa        createSofa();
    CoffeeTable createCoffeeTable();
}

/* =====================  MODERN FAMILY  ===================== */

class ModernChair       implements Chair       { public String describe() { return "Modern Chair       - minimal steel & beige fabric"; } }
class ModernSofa        implements Sofa        { public String describe() { return "Modern Sofa        - 3-seat low profile, oak legs"; } }
class ModernCoffeeTable implements CoffeeTable { public String describe() { return "Modern CoffeeTable - round glass top, slim chrome base"; } }

class ModernFactory implements FurnitureFactory {
    public Chair       createChair()       { return new ModernChair();       }
    public Sofa        createSofa()        { return new ModernSofa();        }
    public CoffeeTable createCoffeeTable() { return new ModernCoffeeTable(); }
}

/* =====================  VICTORIAN FAMILY  ===================== */

class VictorianChair       implements Chair       { public String describe() { return "Victorian Chair       - carved mahogany, velvet upholstery"; } }
class VictorianSofa        implements Sofa        { public String describe() { return "Victorian Sofa        - tufted, button-back, claw feet"; } }
class VictorianCoffeeTable implements CoffeeTable { public String describe() { return "Victorian CoffeeTable - ornate walnut, marble inlay"; } }

class VictorianFactory implements FurnitureFactory {
    public Chair       createChair()       { return new VictorianChair();       }
    public Sofa        createSofa()        { return new VictorianSofa();        }
    public CoffeeTable createCoffeeTable() { return new VictorianCoffeeTable(); }
}

/* =====================  ART DECO FAMILY  ===================== */

class ArtDecoChair       implements Chair       { public String describe() { return "ArtDeco Chair       - geometric brass frame, lacquered seat"; } }
class ArtDecoSofa        implements Sofa        { public String describe() { return "ArtDeco Sofa        - bold curves, gold accents, velvet"; } }
class ArtDecoCoffeeTable implements CoffeeTable { public String describe() { return "ArtDeco CoffeeTable - stepped marble, gilded edges"; } }

class ArtDecoFactory implements FurnitureFactory {
    public Chair       createChair()       { return new ArtDecoChair();       }
    public Sofa        createSofa()        { return new ArtDecoSofa();        }
    public CoffeeTable createCoffeeTable() { return new ArtDecoCoffeeTable(); }
}

/* =====================  CLIENT  ===================== */

class Showroom {
    /** ONE method body, ANY factory. This is the whole payoff. */
    void furnishLivingRoom(FurnitureFactory factory) {
        Chair       chair = factory.createChair();
        Sofa        sofa  = factory.createSofa();
        CoffeeTable table = factory.createCoffeeTable();

        System.out.println("  * " + chair.describe());
        System.out.println("  * " + sofa.describe());
        System.out.println("  * " + table.describe());
    }
}
