package interactiveOopCodes.designPatterns.introDesignPatterns.choosingPatternDemo;

/**
 * Walking the selection questions live.
 *
 *   Q1: Static code, or will it change?     -> CHANGES (new gaits keep being added)
 *   Q2: Is our code used by others?         -> YES (game engine plugins)
 *   Q3: Any agreed interface?               -> we need one
 *   Q4: Which libraries?                    -> plain Java, no constraint
 *   Q5: Performance requirements?           -> normal
 *
 * Answers point to "variability in behavior, kept behind a stable
 * interface" — that's the Strategy pattern. Refactor accordingly.
 *
 * Cashes out as the lecture's two payoffs:
 *   • Code reuse:   the Character class never changes when we add gaits.
 *   • Extensibility: a new Swim class drops in without touching old code.
 */
public class ChoosingPatternDemo {

    public static void main(String[] args) {
        System.out.println("[Before] hardcoded if-chain:");
        BadCharacter b = new BadCharacter();
        b.move("walk");
        b.move("run");
        b.move("fly");

        System.out.println("\n[After] Strategy plug-in:");
        Character hero = new Character(new Walk());
        hero.act();
        hero.setMovement(new Run());
        hero.act();
        hero.setMovement(new Fly());
        hero.act();
    }
}

/* ─── BEFORE: closed for extension, hard to test, ugly to read ───── */
class BadCharacter {
    void move(String mode) {
        if (mode.equals("walk"))      System.out.println("  walking at 4 mph");
        else if (mode.equals("run"))  System.out.println("  running at 12 mph");
        else if (mode.equals("fly"))  System.out.println("  flying at 60 mph");
        else throw new IllegalArgumentException(mode);
    }
}

/* ─── AFTER: variability isolated behind Movement; open for new gaits ─ */
interface Movement { void execute(); }

class Walk implements Movement { public void execute() { System.out.println("  walking at 4 mph"); } }
class Run  implements Movement { public void execute() { System.out.println("  running at 12 mph"); } }
class Fly  implements Movement { public void execute() { System.out.println("  flying at 60 mph"); } }

class Character {
    private Movement movement;
    Character(Movement m) { this.movement = m; }
    void setMovement(Movement m) { this.movement = m; }
    void act() { movement.execute(); }
}
