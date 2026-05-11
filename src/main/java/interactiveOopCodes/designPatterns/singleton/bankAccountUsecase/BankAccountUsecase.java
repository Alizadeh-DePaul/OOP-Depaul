package interactiveOopCodes.designPatterns.singleton.bankAccountUsecase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Real-world use case: BankAccountNumberGenerator.
 *
 * A bank MUST produce unique account numbers. If two parts of the
 * application each create their own generator, the counters drift
 * apart — and two real customers get account "ACC-00001". That's a
 * data-integrity bug with legal consequences.
 *
 * Singleton is the right tool: one shared counter, accessible from
 * anywhere, lazily initialized when the first account is opened.
 *
 * Note: this is a LAZY singleton — illustrative for the use-case lecture.
 * Production code would use the BillPugh variant for thread safety.
 */
public class BankAccountUsecase {

    public static void main(String[] args) throws InterruptedException {
        log("App start — no generator built yet");
        Thread.sleep(1500);    // simulate the app warming up

        log("First user signs up — open account");
        BankAccount a1 = NumberGenerator.getInstance().openAccount("Alice");
        log("Second user signs up — open account");
        BankAccount a2 = NumberGenerator.getInstance().openAccount("Bob");
        log("Third user signs up — open account");
        BankAccount a3 = NumberGenerator.getInstance().openAccount("Carol");

        System.out.println();
        System.out.println("Issued:");
        System.out.println("  " + a1);
        System.out.println("  " + a2);
        System.out.println("  " + a3);

        System.out.println();
        System.out.println("Same generator instance reused? "
                + (NumberGenerator.getInstance() == NumberGenerator.getInstance()));
    }

    private static void log(String msg) {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
                + "  " + msg);
    }
}

/* ── The Singleton: one counter for the whole bank ───────────────────── */
class NumberGenerator {

    private static NumberGenerator instance;     // lazy variant
    private final AtomicInteger counter = new AtomicInteger(0);

    private NumberGenerator() {
        System.out.println("  [NumberGenerator] CREATED at " + LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    public static NumberGenerator getInstance() {
        if (instance == null) instance = new NumberGenerator();
        return instance;
    }

    public BankAccount openAccount(String holder) {
        int number = counter.incrementAndGet();
        return new BankAccount(String.format("ACC-%05d", number), holder);
    }
}

class BankAccount {
    private final String number;
    private final String holder;

    BankAccount(String number, String holder) {
        this.number = number;
        this.holder = holder;
    }

    @Override
    public String toString() {
        return number + "  (" + holder + ")";
    }
}
