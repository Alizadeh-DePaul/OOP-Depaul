package interactiveOopCodes.designPatterns.abstractFactory.newKeywordPain;

/**
 * Anti-pattern: hand-rolled `new` calls scatter family decisions
 * throughout the code, making family-mismatches trivially possible
 * AND impossible to catch at compile time.
 *
 * The compiler happily accepts `new ModernChair()` next to
 * `new VictorianSofa()` because both implement the abstract
 * Chair/Sofa interfaces. The mismatch only manifests at runtime
 * as a style clash.
 *
 * Abstract Factory's fundamental win is forcing every product in
 * one client transaction to come from the SAME concrete factory,
 * which makes mismatches structurally impossible.
 */
public class NewKeywordPain {

    public static void main(String[] args) {
        Showroom showroom = new Showroom();

        System.out.println("--- Attempt 1: developer Alice picks 'modern' everywhere ---");
        Chair       c1 = new ModernChair();
        Sofa        s1 = new ModernSofa();
        CoffeeTable t1 = new ModernCoffeeTable();
        showroom.assertConsistent(c1, s1, t1);

        System.out.println();
        System.out.println("--- Attempt 2: developer Bob picks pieces 'he likes' ---");
        // Bob mixes families. Compiler is FINE with this; runtime is not.
        Chair       c2 = new ModernChair();        // <-- Modern
        Sofa        s2 = new VictorianSofa();      // <-- Victorian (oops!)
        CoffeeTable t2 = new ArtDecoCoffeeTable(); // <-- ArtDeco   (oops!)
        showroom.assertConsistent(c2, s2, t2);
    }
}

interface Chair       { String variant(); }
interface Sofa        { String variant(); }
interface CoffeeTable { String variant(); }

class ModernChair        implements Chair       { public String variant() { return "modern";    } }
class VictorianChair     implements Chair       { public String variant() { return "victorian"; } }
class ModernSofa         implements Sofa        { public String variant() { return "modern";    } }
class VictorianSofa      implements Sofa        { public String variant() { return "victorian"; } }
class ModernCoffeeTable  implements CoffeeTable { public String variant() { return "modern";    } }
class ArtDecoCoffeeTable implements CoffeeTable { public String variant() { return "artdeco";   } }

class Showroom {
    /** Family check happens at RUNTIME, not at compile time. */
    void assertConsistent(Chair c, Sofa s, CoffeeTable t) {
        String cv = c.variant(), sv = s.variant(), tv = t.variant();
        System.out.println("  chair=" + cv + "  sofa=" + sv + "  table=" + tv);
        if (cv.equals(sv) && sv.equals(tv)) {
            System.out.println("  OK - all three products share variant '" + cv + "'");
        } else {
            System.out.println("  FAMILY MISMATCH - compiler was no help, runtime only catches it.");
        }
    }
}
