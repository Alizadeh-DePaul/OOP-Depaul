package interactiveOopCodes.uml.umlClassDiagramRelationships.associationDemo;

/**
 * UML Association Relationship Demo
 *
 * Association is a permanent, structural "has-a" relationship.
 * One class stores a reference to another as a field (state).
 *
 * UML Notation: solid line (arrowhead optional for navigation)
 *   - Bidirectional:  Car ———— Engine   (both know each other)
 *   - Unidirectional: GasPedal ————> Engine  (one-way navigation)
 *
 * Multiplicity examples:
 *   1       exactly one
 *   0..1    zero or one
 *   *       zero or more
 *   1..*    one or more
 *   3..8    between 3 and 8
 */

import java.util.ArrayList;
import java.util.List;

// ═══════════════════════════════════════════════════════════
// Example 1: Unidirectional Association
// GasPedal ————> Engine  (GasPedal knows Engine; Engine doesn't know GasPedal)
// ═══════════════════════════════════════════════════════════

class Engine {
    private int horsepower;

    public Engine(int horsepower) {
        this.horsepower = horsepower;
    }

    public void accelerate() {
        System.out.println("Engine (" + horsepower + " HP) accelerating...");
    }

    public int getHorsepower() { return horsepower; }
}

// GasPedal HAS-A Engine (unidirectional — Engine has no reference back)
class GasPedal {
    private Engine engine;   // association: stored as field

    public GasPedal(Engine engine) {
        this.engine = engine;
    }

    public void press() {
        System.out.println("Gas pedal pressed.");
        engine.accelerate();  // navigates to Engine
    }
}

// ═══════════════════════════════════════════════════════════
// Example 2: Bidirectional Association with Multiplicity
// Bank 1 ———— * ATM  (one Bank has many ATMs; each ATM knows one Bank)
// ═══════════════════════════════════════════════════════════

class ATM {
    private String location;
    private Bank bank;       // multiplicity: 1 (each ATM knows exactly 1 Bank)

    public ATM(String location) {
        this.location = location;
    }

    public void setBank(Bank bank) { this.bank = bank; }

    public void withdraw(double amount) {
        System.out.println("ATM [" + location + "] at "
            + bank.getName() + ": withdrawing $" + amount);
    }
}

class Bank {
    private String name;
    private List<ATM> atms;  // multiplicity: * (Bank has 0 or more ATMs)

    public Bank(String name) {
        this.name = name;
        this.atms = new ArrayList<>();
    }

    public String getName() { return name; }

    // Maintains both sides of the bidirectional association
    public void addATM(ATM atm) {
        atms.add(atm);
        atm.setBank(this);   // keep both sides consistent
    }

    public void listATMs() {
        System.out.println(name + " has " + atms.size() + " ATMs.");
    }
}

// ── Main ─────────────────────────────────────────────────
public class AssociationDemo {
    public static void main(String[] args) {
        System.out.println("=== Unidirectional Association ===");
        Engine v8 = new Engine(450);
        GasPedal pedal = new GasPedal(v8);
        pedal.press();
        // v8 has no reference to pedal — one-way navigation

        System.out.println("\n=== Bidirectional Association (1 to *) ===");
        Bank chase = new Bank("Chase");
        ATM atm1 = new ATM("Main St");
        ATM atm2 = new ATM("Oak Ave");
        ATM atm3 = new ATM("Campus Center");

        chase.addATM(atm1);
        chase.addATM(atm2);
        chase.addATM(atm3);

        chase.listATMs();          // Bank navigates to ATMs
        atm1.withdraw(200.00);     // ATM navigates back to Bank
        atm2.withdraw(50.00);
    }
}
