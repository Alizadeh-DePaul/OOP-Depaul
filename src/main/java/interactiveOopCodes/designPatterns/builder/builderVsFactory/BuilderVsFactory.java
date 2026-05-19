package interactiveOopCodes.designPatterns.builder.builderVsFactory;

/**
 * Pros & Cons demo — Builder vs Abstract Factory side-by-side.
 *
 * Same goal: produce a configured Car. Two very different shapes:
 *
 *   FACTORY (one-shot, all-args)
 *     - Every parameter must be passed at once.
 *     - Optionals become a sea of booleans whose position is
 *       impossible to read at the call site.
 *     - Adding a new optional changes EVERY call site.
 *     - Object is created in ONE step → "WHAT do you want".
 *
 *   BUILDER (step-by-step, named optionals)
 *     - Required fields via constructor; optionals via setters.
 *     - Each fluent call self-documents what it sets.
 *     - Adding a new optional adds a new Builder method —
 *       existing call sites compile unchanged.
 *     - Object is constructed step-by-step → "HOW to build it".
 */
public class BuilderVsFactory {

    public static void main(String[] args) {
        System.out.println("=== Abstract Factory style — one-shot, 8 positional args ===");
        FactoryCar fc1 = CarFactory.create("V6", "Automatic", true,  false, true,  false, false, "blue");
        fc1.show("Factory Sedan");
        // What does the 4th `false` mean? cloth? sunroof? Easy to misread.

        FactoryCar fc2 = CarFactory.create("V8", "Automatic", false, true,  true,  true,  true,  "white");
        fc2.show("Factory SUV");

        System.out.println("\n=== Builder style — step-by-step, named optionals ===");
        BuilderCar bc1 = new BuilderCar.Builder("V6", "Automatic")
                .leather(true)
                .sunroof(true)
                .color("blue")
                .build();
        bc1.show("Builder Sedan");

        BuilderCar bc2 = new BuilderCar.Builder("V8", "Automatic")
                .cloth(true)
                .sunroof(true)
                .navigation(true)
                .allWheelDrive(true)
                .color("white")
                .build();
        bc2.show("Builder SUV");
        // Adding a new optional (e.g., heatedSeats) requires only a new
        // Builder.heatedSeats() method — existing call sites unchanged.
    }
}

/* ─── Factory style: one-shot creation, all args ──────── */
class FactoryCar {
    private final String engine, transmission, color;
    private final boolean leather, cloth, sunroof, navigation, allWheelDrive;

    FactoryCar(String engine, String transmission,
               boolean leather, boolean cloth, boolean sunroof,
               boolean navigation, boolean allWheelDrive,
               String color) {
        this.engine = engine; this.transmission = transmission;
        this.leather = leather; this.cloth = cloth; this.sunroof = sunroof;
        this.navigation = navigation; this.allWheelDrive = allWheelDrive;
        this.color = color;
    }

    void show(String label) {
        System.out.printf("[%s] %s/%s, leather=%b, cloth=%b, sunroof=%b, nav=%b, AWD=%b, color=%s%n",
                label, engine, transmission, leather, cloth, sunroof, navigation, allWheelDrive, color);
    }
}

class CarFactory {
    static FactoryCar create(String engine, String transmission,
                             boolean leather, boolean cloth, boolean sunroof,
                             boolean navigation, boolean allWheelDrive,
                             String color) {
        return new FactoryCar(engine, transmission, leather, cloth, sunroof, navigation, allWheelDrive, color);
    }
}

/* ─── Builder style: step-by-step with named optionals ── */
class BuilderCar {
    private final String engine, transmission, color;
    private final boolean leather, cloth, sunroof, navigation, allWheelDrive;

    private BuilderCar(Builder b) {
        this.engine = b.engine; this.transmission = b.transmission;
        this.leather = b.leather; this.cloth = b.cloth; this.sunroof = b.sunroof;
        this.navigation = b.navigation; this.allWheelDrive = b.allWheelDrive;
        this.color = b.color;
    }

    void show(String label) {
        System.out.printf("[%s] %s/%s, leather=%b, cloth=%b, sunroof=%b, nav=%b, AWD=%b, color=%s%n",
                label, engine, transmission, leather, cloth, sunroof, navigation, allWheelDrive, color);
    }

    public static class Builder {
        private final String engine, transmission;
        private boolean leather, cloth, sunroof, navigation, allWheelDrive;
        private String color = "white";

        public Builder(String engine, String transmission) {
            this.engine = engine;
            this.transmission = transmission;
        }

        public Builder leather(boolean v)        { this.leather = v;        return this; }
        public Builder cloth(boolean v)          { this.cloth = v;          return this; }
        public Builder sunroof(boolean v)        { this.sunroof = v;        return this; }
        public Builder navigation(boolean v)     { this.navigation = v;     return this; }
        public Builder allWheelDrive(boolean v)  { this.allWheelDrive = v;  return this; }
        public Builder color(String c)           { this.color = c;          return this; }

        public BuilderCar build() {
            return new BuilderCar(this);
        }
    }
}
