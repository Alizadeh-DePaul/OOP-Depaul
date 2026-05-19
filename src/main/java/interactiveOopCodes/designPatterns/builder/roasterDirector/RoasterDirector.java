package interactiveOopCodes.designPatterns.builder.roasterDirector;

import java.util.LinkedList;

/**
 * Implementation demo — Classical Director-driven Builder.
 *
 * Coffee Roaster manufacturing system. The Director knows the
 * 8-step build sequence; each Concrete Builder knows the actual
 * parts to install at each step.
 *
 *   PersonalRoasterBuilder   → kitchen-counter unit, no platform
 *   CommercialRoasterBuilder → industrial unit, all 8 components
 *
 * Same Director recipe, two completely different Roasters.
 */
public class RoasterDirector {

    public static void main(String[] args) {
        Director director = new Director();

        RoasterBuilder personal = new PersonalRoasterBuilder();
        director.buildRoaster(personal);
        Roaster unit1 = personal.getRoaster();
        unit1.display("PERSONAL");

        RoasterBuilder commercial = new CommercialRoasterBuilder();
        director.buildRoaster(commercial);
        Roaster unit2 = commercial.getRoaster();
        unit2.display("COMMERCIAL");
    }
}

/* ─── Product ───────────────────────────────────────────── */
class Roaster {
    private final LinkedList<String> components = new LinkedList<>();

    void add(String component) {
        components.addLast(component);
    }

    void display(String label) {
        System.out.println("\n=== " + label + " ROASTER BUILD ===");
        for (String c : components) {
            System.out.println("  • " + c);
        }
    }
}

/* ─── Builder interface — 8 construction steps ─────────── */
interface RoasterBuilder {
    void buildCoolingTray();
    void buildExhaustSystem();
    void buildGasBurner();
    void buildPlatform();
    void buildMotor();
    void buildThermocouples();
    void buildInnerDrum();
    void buildMainBody();
    Roaster getRoaster();
}

/* ─── ConcreteBuilder A — Personal ─────────────────────── */
class PersonalRoasterBuilder implements RoasterBuilder {
    private final Roaster roaster = new Roaster();
    public void buildCoolingTray()   { roaster.add("Personal cooling tray"); }
    public void buildExhaustSystem() { roaster.add("Personal exhaust system"); }
    public void buildGasBurner()     { roaster.add("Personal gas burner"); }
    public void buildPlatform()      { /* not applicable — personal units sit on a counter */ }
    public void buildMotor()         { roaster.add("Standard motor"); }
    public void buildThermocouples() { roaster.add("Standard thermocouples"); }
    public void buildInnerDrum()     { roaster.add("Personal inner drum"); }
    public void buildMainBody()      { roaster.add("Personal main body"); }
    public Roaster getRoaster()      { return roaster; }
}

/* ─── ConcreteBuilder B — Commercial ──────────────────── */
class CommercialRoasterBuilder implements RoasterBuilder {
    private final Roaster roaster = new Roaster();
    public void buildCoolingTray()   { roaster.add("Commercial cooling tray"); }
    public void buildExhaustSystem() { roaster.add("Commercial exhaust system"); }
    public void buildGasBurner()     { roaster.add("Commercial gas burner"); }
    public void buildPlatform()      { roaster.add("Standard platform"); }
    public void buildMotor()         { roaster.add("Standard motor"); }
    public void buildThermocouples() { roaster.add("Standard thermocouples"); }
    public void buildInnerDrum()     { roaster.add("Commercial inner drum"); }
    public void buildMainBody()      { roaster.add("Commercial main body"); }
    public Roaster getRoaster()      { return roaster; }
}

/* ─── Director — orchestrates the build sequence ──────── */
class Director {
    void buildRoaster(RoasterBuilder builder) {
        builder.buildCoolingTray();
        builder.buildExhaustSystem();
        builder.buildGasBurner();
        builder.buildInnerDrum();
        builder.buildMainBody();
        builder.buildMotor();
        builder.buildPlatform();
        builder.buildThermocouples();
    }
}
