package interactiveOopCodes.designPrinciples.pragmaticPrinciples.kissDemo;

/**
 * KISS — Keep It Simple.
 *
 * Bad : a single expression nests four ternaries to compute a letter grade.
 *       It is "clever" — and unreadable.
 * Good: a guard-clause chain reads like the spec. One thought per line.
 *
 * Rule of thumb: if you can't read the code aloud and follow it,
 * the code is too clever for its own good.
 */
public class KissDemo {

    public static void main(String[] args) {
        System.out.println("score   bad   good");
        for (int score : new int[]{95, 82, 71, 65, 40}) {
            System.out.printf("  %2d     %s     %s%n", score, bad(score), good(score));
        }
    }

    // Nested ternaries: clever but unreadable.
    static char bad(int s) {
        return s >= 90 ? 'A' : s >= 80 ? 'B' : s >= 70 ? 'C' : s >= 60 ? 'D' : 'F';
    }

    // Guard-clause chain: reads like the spec.
    static char good(int score) {
        if (score >= 90) return 'A';
        if (score >= 80) return 'B';
        if (score >= 70) return 'C';
        if (score >= 60) return 'D';
        return 'F';
    }
}
