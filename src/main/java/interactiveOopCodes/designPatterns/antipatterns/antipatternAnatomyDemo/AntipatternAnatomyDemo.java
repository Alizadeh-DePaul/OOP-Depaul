package interactiveOopCodes.designPatterns.antipatterns.antipatternAnatomyDemo;

import java.util.List;

/**
 * AntipatternAnatomyDemo - shows the structure used to DESCRIBE an antipattern.
 *
 *   Brown et al. (1998) define a six-part anatomy for documenting any
 *   antipattern. It mirrors the GoF design-pattern record, but where a pattern
 *   catalogs a *good* recurring solution, an antipattern catalogs a *recurring
 *   mistake* and the way out:
 *
 *     1. Name          - memorable label  (e.g. "God Class")
 *     2. Type          - Development / Architecture / Project Management
 *     3. Problem       - what's broken in the current solution
 *     4. Symptoms      - observable signs (LOC, coupling, test pain, ...)
 *     5. Consequences  - what goes wrong if left untreated
 *     6. Refactoring   - recommended path to a positive pattern/principle
 *
 *   This demo defines that anatomy as a small Java record and uses it to
 *   catalog three classic antipatterns across all three categories.
 */
public class AntipatternAnatomyDemo {

    public static void main(String[] args) {
        Antipattern godClass = new Antipattern(
            "God Class",
            Category.DEVELOPMENT,
            "One class accumulates unrelated responsibilities.",
            List.of("500+ LOC", "20+ fields", "low cohesion", "10+ direct dependents"),
            List.of("hard to test", "merge conflicts", "ripple-edits", "SRP/ISP violation"),
            "Extract Class + Introduce Interface (apply SRP, ISP)"
        );

        Antipattern goldenHammer = new Antipattern(
            "Golden Hammer",
            Category.DEVELOPMENT,
            "One favourite tool is forced onto every problem.",
            List.of("inheritance everywhere", "single-language solutions", "framework lock"),
            List.of("poor performance", "complex code", "redundant abstractions"),
            "Match tool to problem; widen the toolbox (right pattern, right collection)"
        );

        Antipattern deathMarch = new Antipattern(
            "Death March",
            Category.PROJECT_MANAGEMENT,
            "Schedule is known to be impossible from day one.",
            List.of("60+ hour weeks", "scope keeps changing", "morale collapse"),
            List.of("attrition", "quality collapse", "project failure"),
            "Renegotiate scope or timeline; staff sustainably"
        );

        // Three different categories show how the anatomy is reused.
        System.out.println("Antipattern Catalog (6-part anatomy):");
        System.out.println(godClass);
        System.out.println(goldenHammer);
        System.out.println(deathMarch);

        // The same anatomy mirrors the GoF pattern record. Where a Design
        // Pattern lists "Known Uses", an Antipattern lists "Refactoring" -
        // i.e. the way back to a clean solution.
        System.out.println("Same shape as a GoF pattern record, with Refactoring replacing Known Uses.");
    }
}

/** The 6-part record an antipattern catalog populates for each entry. */
record Antipattern(
        String name,
        Category category,
        String problem,
        List<String> symptoms,
        List<String> consequences,
        String refactoring
) {
    @Override public String toString() {
        return """

            -- %s ------------------------------------------------
            Category    : %s
            Problem     : %s
            Symptoms    : %s
            Consequences: %s
            Refactoring : %s
            """.formatted(
                name, category, problem,
                String.join("; ", symptoms),
                String.join("; ", consequences),
                refactoring);
    }
}

enum Category { DEVELOPMENT, ARCHITECTURE, PROJECT_MANAGEMENT }
