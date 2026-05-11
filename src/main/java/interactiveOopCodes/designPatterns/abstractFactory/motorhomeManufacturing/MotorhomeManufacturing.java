package interactiveOopCodes.designPatterns.abstractFactory.motorhomeManufacturing;

/**
 * Abstract Factory - Motor-home Manufacturing use case.
 *
 * Matrix:  3 types (TypeA / TypeB / TypeC)
 *        x 4 parts (Frame / Style / Engine / Kitchen)
 *        = 12 concrete products in 3 compatible motor-home families.
 *
 * Manufacturer takes a MotorhomeFactory and assembles a coherent
 * motor-home from 4 compatible parts. Swap the factory -> swap the
 * whole spec, with zero changes to assembly logic.
 *
 * This is the lecture's signature Abstract Factory use case.
 */
public class MotorhomeManufacturing {

    public static void main(String[] args) {
        Manufacturer mfr = new Manufacturer();

        System.out.println("--- Type-A Motorhome (luxury full-bus) ---");
        mfr.assemble(new TypeAFactory());

        System.out.println();
        System.out.println("--- Type-B Motorhome (camper van) ---");
        mfr.assemble(new TypeBFactory());

        System.out.println();
        System.out.println("--- Type-C Motorhome (cab-over truck) ---");
        mfr.assemble(new TypeCFactory());
    }
}

/* =====================  ABSTRACT PRODUCTS  ===================== */

interface Frame   { String describe(); }
interface Style   { String describe(); }
interface Engine  { String describe(); }
interface Kitchen { String describe(); }

/* =====================  ABSTRACT FACTORY  ===================== */

interface MotorhomeFactory {
    Frame   createFrame();
    Style   createStyle();
    Engine  createEngine();
    Kitchen createKitchen();
}

/* =====================  TYPE-A FAMILY  ===================== */

class TypeAFrame   implements Frame   { public String describe() { return "Type-A Frame   - full bus chassis (12m)"; } }
class TypeAStyle   implements Style   { public String describe() { return "Type-A Style   - luxury exterior, two side slide-outs"; } }
class TypeAEngine  implements Engine  { public String describe() { return "Type-A Engine  - diesel 8.9L, 360 hp"; } }
class TypeAKitchen implements Kitchen { public String describe() { return "Type-A Kitchen - full residential, island counter"; } }

class TypeAFactory implements MotorhomeFactory {
    public Frame   createFrame()   { return new TypeAFrame();   }
    public Style   createStyle()   { return new TypeAStyle();   }
    public Engine  createEngine()  { return new TypeAEngine();  }
    public Kitchen createKitchen() { return new TypeAKitchen(); }
}

/* =====================  TYPE-B FAMILY  ===================== */

class TypeBFrame   implements Frame   { public String describe() { return "Type-B Frame   - van chassis (6.4m)"; } }
class TypeBStyle   implements Style   { public String describe() { return "Type-B Style   - stealth camper, no slide-outs"; } }
class TypeBEngine  implements Engine  { public String describe() { return "Type-B Engine  - diesel 3.0L, 188 hp"; } }
class TypeBKitchen implements Kitchen { public String describe() { return "Type-B Kitchen - galley, induction, fold-down"; } }

class TypeBFactory implements MotorhomeFactory {
    public Frame   createFrame()   { return new TypeBFrame();   }
    public Style   createStyle()   { return new TypeBStyle();   }
    public Engine  createEngine()  { return new TypeBEngine();  }
    public Kitchen createKitchen() { return new TypeBKitchen(); }
}

/* =====================  TYPE-C FAMILY  ===================== */

class TypeCFrame   implements Frame   { public String describe() { return "Type-C Frame   - cab-over truck chassis (8.5m)"; } }
class TypeCStyle   implements Style   { public String describe() { return "Type-C Style   - over-cab bunk, one slide-out"; } }
class TypeCEngine  implements Engine  { public String describe() { return "Type-C Engine  - V8 6.8L gas, 305 hp"; } }
class TypeCKitchen implements Kitchen { public String describe() { return "Type-C Kitchen - compact L-shape, 3-burner"; } }

class TypeCFactory implements MotorhomeFactory {
    public Frame   createFrame()   { return new TypeCFrame();   }
    public Style   createStyle()   { return new TypeCStyle();   }
    public Engine  createEngine()  { return new TypeCEngine();  }
    public Kitchen createKitchen() { return new TypeCKitchen(); }
}

/* =====================  CLIENT  ===================== */

class Manufacturer {
    void assemble(MotorhomeFactory factory) {
        Frame   f = factory.createFrame();
        Style   s = factory.createStyle();
        Engine  e = factory.createEngine();
        Kitchen k = factory.createKitchen();

        System.out.println("  * " + f.describe());
        System.out.println("  * " + s.describe());
        System.out.println("  * " + e.describe());
        System.out.println("  * " + k.describe());
    }
}
