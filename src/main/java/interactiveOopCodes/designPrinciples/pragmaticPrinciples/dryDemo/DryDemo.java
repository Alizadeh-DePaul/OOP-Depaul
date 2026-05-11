package interactiveOopCodes.designPrinciples.pragmaticPrinciples.dryDemo;

/**
 * DRY — Don't Repeat Yourself.
 *
 * Bad : three methods each re-implement the same "non-blank string" check.
 * Good: one helper holds the rule; the three callers stay distinct.
 *
 * The trap: only DRY when the duplicated lines encode the SAME RULE.
 * Two methods that look identical but evolve for different reasons
 * should stay separate — premature abstraction is worse than duplication.
 */
public class DryDemo {

    public static void main(String[] args) {
        System.out.println("=== Before: same rule copied three times ===");
        BadValidator bv = new BadValidator();
        System.out.println("  username ''     : " + bv.validateUsername(""));
        System.out.println("  email   'a@b'   : " + bv.validateEmail("a@b"));
        System.out.println("  city    'Chi'   : " + bv.validateCity("Chi"));

        System.out.println("\n=== After: one rule, one place ===");
        GoodValidator gv = new GoodValidator();
        System.out.println("  username ''     : " + gv.validateUsername(""));
        System.out.println("  email   'a@b'   : " + gv.validateEmail("a@b"));
        System.out.println("  city    'Chi'   : " + gv.validateCity("Chi"));
    }
}

class BadValidator {
    boolean validateUsername(String s) {
        if (s == null) return false;
        if (s.isEmpty()) return false;
        if (s.trim().length() == 0) return false;
        return true;
    }
    boolean validateEmail(String s) {
        if (s == null) return false;
        if (s.isEmpty()) return false;
        if (s.trim().length() == 0) return false;
        return s.contains("@");
    }
    boolean validateCity(String s) {
        if (s == null) return false;
        if (s.isEmpty()) return false;
        if (s.trim().length() == 0) return false;
        return true;
    }
}

class GoodValidator {
    private static boolean isPresent(String s) {          // ONE rule, ONE place
        return s != null && !s.trim().isEmpty();
    }
    boolean validateUsername(String s) { return isPresent(s); }
    boolean validateEmail(String s)    { return isPresent(s) && s.contains("@"); }
    boolean validateCity(String s)     { return isPresent(s); }
}
