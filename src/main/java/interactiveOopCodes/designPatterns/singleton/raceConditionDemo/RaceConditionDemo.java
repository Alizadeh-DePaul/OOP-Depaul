package interactiveOopCodes.designPatterns.singleton.raceConditionDemo;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * Demonstrates why "naive" lazy initialization is NOT thread-safe.
 *
 * Two singletons under stress test:
 *   - LazyUnsafe (the textbook lazy idiom — instance == null check + new)
 *   - BillPugh   (holder-class idiom — JVM-guaranteed lazy and thread-safe)
 *
 * For each, we spawn 8 threads that all call getInstance() at roughly the
 * same instant. We collect the returned references into a Set keyed by
 * System.identityHashCode — if more than one distinct identity appears,
 * the singleton was broken.
 *
 * Typical observed output (varies run-to-run, but the contrast holds):
 *   LazyUnsafe  → 2..8 distinct instances   (BUG)
 *   BillPugh    → 1   distinct instance     (correct)
 */
public class RaceConditionDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Stress-testing 8 concurrent first-time getInstance() calls\n");

        int distinctLazy = stressTest("LazyUnsafe", LazyUnsafe::getInstance);
        int distinctPugh = stressTest("BillPugh",   BillPugh::getInstance);

        System.out.println();
        System.out.println("LazyUnsafe distinct instances: " + distinctLazy
                + (distinctLazy > 1 ? "   ← BUG: singleton broken" : "   ← (got lucky this run)"));
        System.out.println("BillPugh   distinct instances: " + distinctPugh
                + (distinctPugh == 1 ? "   ← correct" : "   ← UNEXPECTED"));
    }

    private static int stressTest(String name, Supplier<Object> get) throws InterruptedException {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        int threads = 8;
        CountDownLatch ready = new CountDownLatch(threads);
        CountDownLatch start = new CountDownLatch(1);
        ExecutorService pool = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            pool.submit(() -> {
                ready.countDown();
                try {
                    start.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                seen.add(get.get());
            });
        }
        ready.await();      // all threads at the gate
        start.countDown();  // open the gate — race begins
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        Set<Integer> identities = new HashSet<>();
        for (Object o : seen) identities.add(System.identityHashCode(o));
        System.out.println(name + " collected references with " + identities.size() + " distinct identities");
        return identities.size();
    }
}

/* ── Textbook lazy idiom — NOT thread-safe ───────────────────────────── */
class LazyUnsafe {
    private static LazyUnsafe instance;

    private LazyUnsafe() {
        try { Thread.sleep(5); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public static LazyUnsafe getInstance() {
        if (instance == null) instance = new LazyUnsafe();
        return instance;
    }
}

/* ── Bill Pugh idiom — thread-safe by JVM guarantee ──────────────────── */
class BillPugh {
    private BillPugh() {
        try { Thread.sleep(5); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    private static class Holder {
        static final BillPugh INSTANCE = new BillPugh();
    }

    public static BillPugh getInstance() {
        return Holder.INSTANCE;
    }
}
