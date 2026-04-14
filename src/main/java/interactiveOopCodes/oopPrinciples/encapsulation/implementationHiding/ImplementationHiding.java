package interactiveOopCodes.oopPrinciples.encapsulation.implementationHiding;

// Implementation Hiding — separating interface from implementation
// The internal data structure can change without affecting client code

import java.util.*;

// The public interface — this is the CONTRACT
interface ContactBook {
    void addContact(String name, String phone);
    String findPhone(String name);
    int size();
}

// Implementation A: backed by HashMap (fast lookup, unordered)
class HashContactBook implements ContactBook {
    private Map<String, String> contacts = new HashMap<>();

    public void addContact(String name, String phone) {
        contacts.put(name, phone);
    }

    public String findPhone(String name) {
        return contacts.getOrDefault(name, "Not found");
    }

    public int size() { return contacts.size(); }

    public String toString() { return "HashContactBook (HashMap-backed)"; }
}

// Implementation B: backed by TreeMap (sorted, slightly slower)
class SortedContactBook implements ContactBook {
    private TreeMap<String, String> contacts = new TreeMap<>();

    public void addContact(String name, String phone) {
        contacts.put(name, phone);
    }

    public String findPhone(String name) {
        return contacts.getOrDefault(name, "Not found");
    }

    public int size() { return contacts.size(); }

    // Bonus: sorted implementation can offer alphabetical listing
    public String getFirstContact() {
        return contacts.isEmpty() ? "Empty" : contacts.firstKey();
    }

    public String toString() { return "SortedContactBook (TreeMap-backed)"; }
}

public class ImplementationHiding {
    // Client code depends on the INTERFACE, not the implementation
    static void demonstrateUsage(ContactBook book) {
        System.out.println("Using: " + book);
        book.addContact("Alice", "555-0101");
        book.addContact("Charlie", "555-0303");
        book.addContact("Bob", "555-0202");

        System.out.println("Find Alice: " + book.findPhone("Alice"));
        System.out.println("Find Dave:  " + book.findPhone("Dave"));
        System.out.println("Total contacts: " + book.size());
        System.out.println();
    }

    public static void main(String[] args) {
        // SAME client code works with EITHER implementation
        // This is implementation hiding in action

        System.out.println("=== Swap implementations with ZERO code changes ===\n");

        ContactBook bookA = new HashContactBook();
        demonstrateUsage(bookA);

        ContactBook bookB = new SortedContactBook();
        demonstrateUsage(bookB);

        // The client (demonstrateUsage) never knew — or cared — which
        // data structure was used internally. That's the power of
        // separating interface from implementation.
    }
}
