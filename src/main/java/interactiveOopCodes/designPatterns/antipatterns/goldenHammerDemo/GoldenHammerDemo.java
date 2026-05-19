package interactiveOopCodes.designPatterns.antipatterns.goldenHammerDemo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * GoldenHammerDemo - "If all you have is a hammer, everything looks
 * like a nail." (Abraham Maslow, 1966)
 *
 *   BAD: A developer who loves HashMap reaches for it for every
 *        collection problem - including a uniqueness check (where a Set
 *        fits) and an ordered list of steps (where a List fits). The
 *        right tool is just one method call away in the standard library.
 *
 *   GOOD: Match the data structure to the problem.
 *            Set  -> uniqueness
 *            List -> ordered sequence
 *            Map  -> key -> value lookup
 *
 *   Cousin antipattern - Reinventing the Wheel: building a custom hash
 *   table because "Java's HashMap might be slow" before profiling.
 *   The JDK already solved most of these problems, faster, with tests.
 */
public class GoldenHammerDemo {

    /* ----------------- BAD: HashMap for everything ----------------- */

    static boolean hasDuplicateBad(String[] words) {
        // Misusing HashMap as a set: pays for a value column it ignores
        Map<String, Boolean> seen = new HashMap<>();
        for (String w : words) {
            if (seen.containsKey(w)) return true;
            seen.put(w, true);
        }
        return false;
    }

    static String firstStepBad(String[] steps) {
        // Misusing HashMap to fake a list - keys are just indexes
        Map<Integer, String> bag = new HashMap<>();
        for (int i = 0; i < steps.length; i++) bag.put(i, steps[i]);
        return bag.get(0);
    }

    /* ----------------- GOOD: Right tool per case ----------------- */

    static boolean hasDuplicate(String[] words) {
        Set<String> seen = new HashSet<>();
        for (String w : words) if (!seen.add(w)) return true;
        return false;
    }

    static String firstStep(List<String> steps) {
        return steps.get(0);
    }

    public static void main(String[] args) {
        String[] words = {"alpha", "beta", "alpha"};
        System.out.println("hasDuplicateBad : " + hasDuplicateBad(words));
        System.out.println("hasDuplicate    : " + hasDuplicate(words));

        String[] steps = {"login", "load", "render"};
        System.out.println("firstStepBad : " + firstStepBad(steps));
        System.out.println("firstStep    : " + firstStep(List.of(steps)));

        // BAD cousin: Reinventing the Wheel
        HomegrownMap rolled = new HomegrownMap();
        rolled.put("theme", "dark");
        System.out.println("HomegrownMap.get(theme): " + rolled.get("theme"));

        // GOOD: the standard library already shipped a better one
        Map<String, String> stdLib = Map.of("theme", "dark");
        System.out.println("Map.of.get(theme)      : " + stdLib.get("theme"));
    }
}

/** Wrong: hand-rolled custom map with linear lookup - slower AND buggier. */
class HomegrownMap {
    String[] keys = new String[16];
    String[] vals = new String[16];
    int size = 0;

    void put(String k, String v) { keys[size] = k; vals[size] = v; size++; }
    String get(String k) {
        for (int i = 0; i < size; i++) if (keys[i].equals(k)) return vals[i];
        return null;
    }
}
