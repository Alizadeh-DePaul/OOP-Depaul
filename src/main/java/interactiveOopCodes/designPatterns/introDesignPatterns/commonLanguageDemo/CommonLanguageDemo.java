package interactiveOopCodes.designPatterns.introDesignPatterns.commonLanguageDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * The "common language" benefit, dramatized.
 *
 *   DevAlice (no pattern vocabulary) needs three sentences to
 *   describe what DevBob says in one word: "Observer".
 *
 * Same code on both sides — the difference is communication cost.
 * Pattern names let teams compress design discussions enormously.
 */
public class CommonLanguageDemo {

    public static void main(String[] args) {
        System.out.println("DevAlice: I need a thing that holds a list of");
        System.out.println("          listeners. When the value changes, the");
        System.out.println("          thing calls each listener with the new value.");
        System.out.println("DevBob:   Oh, just use an Observer for that.");
        System.out.println();

        // The thing they're describing — same code, named once instead of paraphrased.
        Price price = new Price();
        price.subscribe(p -> System.out.println("[listener A] new price = " + p));
        price.subscribe(p -> System.out.println("[listener B] new price = " + p));

        price.set(99);
        price.set(149);
    }
}

interface PriceListener { void onChange(int newPrice); }

class Price {
    private final List<PriceListener> listeners = new ArrayList<>();
    void subscribe(PriceListener l) { listeners.add(l); }
    void set(int p) { for (PriceListener l : listeners) l.onChange(p); }
}
