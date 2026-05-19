package interactiveOopCodes.designPatterns.builder.houseBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Intent demo — "Building a House" example.
 *
 * Same construction process (Director recipe) drives two different
 * Concrete Builders → two completely different House products.
 *
 *   foundation → walls → roof → windows → garage
 *
 * StoneHouseBuilder produces a brick-and-mortar suburban home.
 * WoodHouseBuilder  produces a cedar cabin with no garage.
 */
public class HouseBuilder {

    public static void main(String[] args) {
        ConstructionDirector director = new ConstructionDirector();

        // Same Director, two different Builders → two different Houses
        Builder stoneBuilder = new StoneHouseBuilder();
        director.constructHouse(stoneBuilder);
        House stoneHouse = stoneBuilder.getHouse();
        stoneHouse.show("STONE HOUSE");

        Builder woodBuilder = new WoodHouseBuilder();
        director.constructHouse(woodBuilder);
        House woodHouse = woodBuilder.getHouse();
        woodHouse.show("WOOD CABIN");
    }
}

/* ─── Product ─────────────────────────────────────────────── */
class House {
    private final List<String> parts = new ArrayList<>();

    void addPart(String part) {
        parts.add(part);
    }

    void show(String label) {
        System.out.println("\n=== " + label + " ===");
        for (String p : parts) {
            System.out.println("  • " + p);
        }
    }
}

/* ─── Builder interface — declares the construction steps ── */
interface Builder {
    void buildFoundation();
    void buildWalls();
    void buildRoof();
    void buildWindows();
    void buildGarage();
    House getHouse();
}

/* ─── ConcreteBuilder A — Stone house ────────────────────── */
class StoneHouseBuilder implements Builder {
    private final House house = new House();

    public void buildFoundation() { house.addPart("Concrete foundation, 2ft deep"); }
    public void buildWalls()      { house.addPart("Stone walls, 12in thick"); }
    public void buildRoof()       { house.addPart("Slate roof"); }
    public void buildWindows()    { house.addPart("Double-pane wood-frame windows"); }
    public void buildGarage()     { house.addPart("Two-car attached garage"); }
    public House getHouse()       { return house; }
}

/* ─── ConcreteBuilder B — Wood cabin ─────────────────────── */
class WoodHouseBuilder implements Builder {
    private final House house = new House();

    public void buildFoundation() { house.addPart("Stilts on bedrock"); }
    public void buildWalls()      { house.addPart("Cedar log walls"); }
    public void buildRoof()       { house.addPart("Cedar shake roof"); }
    public void buildWindows()    { house.addPart("Single-pane wood windows"); }
    public void buildGarage()     { /* cabin has no garage — step skipped */ }
    public House getHouse()       { return house; }
}

/* ─── Director — knows the recipe, not the materials ─────── */
class ConstructionDirector {
    void constructHouse(Builder builder) {
        builder.buildFoundation();
        builder.buildWalls();
        builder.buildRoof();
        builder.buildWindows();
        builder.buildGarage();
    }
}
