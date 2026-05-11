package interactiveOopCodes.designPatterns.abstractFactory.dependencyInjectionFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Modern, inheritance-free alternative to a classic Abstract Factory.
 * Instead of subclassing AbstractFactory once per variant, we register
 * (variant, productType) -> Supplier<Product> pairs in a registry map.
 *
 *   Pros over classical AF: zero subclasses, runtime-registerable
 *                           variants, friendly to lambdas / DI containers.
 *   Cons over classical AF: the "family compatibility" invariant is no
 *                           longer encoded in the type system - you must
 *                           enforce it by convention or via a builder.
 */
public class DependencyInjectionFactory {

    public static void main(String[] args) {
        FurnitureRegistry registry = new FurnitureRegistry();

        // Register Modern family - three product types under one variant key.
        registry.register("modern", Chair.class,       ModernChair::new);
        registry.register("modern", Sofa.class,        ModernSofa::new);
        registry.register("modern", CoffeeTable.class, ModernCoffeeTable::new);

        // Register Victorian family.
        registry.register("victorian", Chair.class,       VictorianChair::new);
        registry.register("victorian", Sofa.class,        VictorianSofa::new);
        registry.register("victorian", CoffeeTable.class, VictorianCoffeeTable::new);

        Showroom showroom = new Showroom(registry);
        System.out.println("--- Modern (via DI registry) ---");
        showroom.furnish("modern");

        System.out.println();
        System.out.println("--- Victorian (via DI registry) ---");
        showroom.furnish("victorian");

        // Bonus: register a brand-new variant AT RUNTIME - no subclassing!
        System.out.println();
        System.out.println("--- Industrial (registered at runtime, no subclasses) ---");
        registry.register("industrial", Chair.class,       () -> () -> "Industrial Chair       - riveted steel frame");
        registry.register("industrial", Sofa.class,        () -> () -> "Industrial Sofa        - leather + iron rivets");
        registry.register("industrial", CoffeeTable.class, () -> () -> "Industrial CoffeeTable - reclaimed wood, pipe legs");
        showroom.furnish("industrial");
    }
}

interface Chair       { String describe(); }
interface Sofa        { String describe(); }
interface CoffeeTable { String describe(); }

class ModernChair          implements Chair       { public String describe() { return "Modern Chair       - minimal steel & beige fabric"; } }
class ModernSofa           implements Sofa        { public String describe() { return "Modern Sofa        - 3-seat low profile, oak legs"; } }
class ModernCoffeeTable    implements CoffeeTable { public String describe() { return "Modern CoffeeTable - round glass top, chrome base"; } }
class VictorianChair       implements Chair       { public String describe() { return "Victorian Chair       - carved mahogany, velvet upholstery"; } }
class VictorianSofa        implements Sofa        { public String describe() { return "Victorian Sofa        - tufted, button-back, claw feet"; } }
class VictorianCoffeeTable implements CoffeeTable { public String describe() { return "Victorian CoffeeTable - ornate walnut, marble inlay"; } }

class FurnitureRegistry {
    private final Map<String, Map<Class<?>, Supplier<?>>> store = new HashMap<>();

    <T> void register(String variant, Class<T> type, Supplier<T> supplier) {
        store.computeIfAbsent(variant, k -> new HashMap<>()).put(type, supplier);
    }

    @SuppressWarnings("unchecked")
    <T> T create(String variant, Class<T> type) {
        Map<Class<?>, Supplier<?>> family = store.get(variant);
        if (family == null) {
            throw new IllegalArgumentException("Unknown variant: " + variant);
        }
        Supplier<?> supplier = family.get(type);
        if (supplier == null) {
            throw new IllegalArgumentException("No " + type.getSimpleName() + " registered for variant " + variant);
        }
        return (T) supplier.get();
    }
}

class Showroom {
    private final FurnitureRegistry registry;
    Showroom(FurnitureRegistry registry) { this.registry = registry; }

    void furnish(String variant) {
        Chair       c = registry.create(variant, Chair.class);
        Sofa        s = registry.create(variant, Sofa.class);
        CoffeeTable t = registry.create(variant, CoffeeTable.class);
        System.out.println("  * " + c.describe());
        System.out.println("  * " + s.describe());
        System.out.println("  * " + t.describe());
    }
}
