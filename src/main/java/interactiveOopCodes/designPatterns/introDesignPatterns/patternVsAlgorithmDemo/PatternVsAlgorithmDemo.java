package interactiveOopCodes.designPatterns.introDesignPatterns.patternVsAlgorithmDemo;

import java.util.Arrays;

/**
 * Algorithm vs Pattern — the same goal, two very different stances.
 *
 *   ALGORITHM (a recipe):
 *     BubbleSort.sort(int[]) — exact, ordered steps.
 *     Change anything and the answer changes.
 *
 *   PATTERN (a blueprint):
 *     Sorter is a Strategy hot-spot. The shape is fixed
 *     (compare and swap), but WHICH compare is variable.
 *     Two concrete comparators plug into the same shape.
 *
 * Run main() to see both produce sorted output — but the pattern
 * version lets us swap ordering at runtime without touching the loop.
 */
public class PatternVsAlgorithmDemo {

    public static void main(String[] args) {
        int[] data1 = {5, 2, 9, 1, 7};
        int[] data2 = {5, 2, 9, 1, 7};
        int[] data3 = {5, 2, 9, 1, 7};

        // Algorithm: one fixed recipe.
        BubbleSort.sort(data1);
        System.out.println("Algorithm     -> " + Arrays.toString(data1));

        // Pattern: one shape, swap-able comparator.
        Sorter ascending  = (a, b) -> a - b;
        Sorter descending = (a, b) -> b - a;
        StrategySort.sort(data2, ascending);
        System.out.println("Pattern asc   -> " + Arrays.toString(data2));
        StrategySort.sort(data3, descending);
        System.out.println("Pattern desc  -> " + Arrays.toString(data3));
    }
}

/* ─── Algorithm side: clear steps, fixed result ──────────────────── */
class BubbleSort {
    static void sort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (a[j] > a[j + 1]) {              // step 1: compare adjacent
                    int tmp = a[j];                 // step 2: swap if out-of-order
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }
}

/* ─── Pattern side: same shape, variable compare hot-spot ────────── */
interface Sorter { int compare(int a, int b); }     // the hot-spot

class StrategySort {
    static void sort(int[] a, Sorter how) {
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - 1 - i; j++) {
                if (how.compare(a[j], a[j + 1]) > 0) {   // pluggable comparison
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }
}
