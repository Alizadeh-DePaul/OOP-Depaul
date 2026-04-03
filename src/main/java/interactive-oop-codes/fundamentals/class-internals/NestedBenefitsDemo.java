/**
 * Benefits of Nested Classes
 *
 * 1. Encapsulation with better security — hide implementation details
 * 2. Logical grouping — related classes are kept together
 * 3. Improved maintainability — changes are localized
 */
public class NestedBenefitsDemo {

    // === Benefit 1: Encapsulation ===
    // The Node class is an implementation detail of LinkedList.
    // External code cannot see or misuse it.
    static class LinkedList {
        private Node head;

        // Private static nested class — completely hidden
        private static class Node {
            int data;
            Node next;

            Node(int data) {
                this.data = data;
            }
        }

        public void add(int value) {
            Node newNode = new Node(value);
            newNode.next = head;
            head = newNode;
        }

        public void print() {
            Node current = head;
            StringBuilder sb = new StringBuilder("[");
            while (current != null) {
                sb.append(current.data);
                if (current.next != null) sb.append(" -> ");
                current = current.next;
            }
            sb.append("]");
            System.out.println(sb);
        }
    }

    // === Benefit 2: Logical Grouping ===
    // Builder is logically part of Pizza — they belong together
    static class Pizza {
        private final String size;
        private final boolean cheese;
        private final boolean pepperoni;
        private final boolean mushrooms;

        private Pizza(Builder builder) {
            this.size = builder.size;
            this.cheese = builder.cheese;
            this.pepperoni = builder.pepperoni;
            this.mushrooms = builder.mushrooms;
        }

        public String toString() {
            return size + " pizza" +
                    (cheese ? " +cheese" : "") +
                    (pepperoni ? " +pepperoni" : "") +
                    (mushrooms ? " +mushrooms" : "");
        }

        // Static nested Builder — logically part of Pizza
        static class Builder {
            private final String size;
            private boolean cheese;
            private boolean pepperoni;
            private boolean mushrooms;

            public Builder(String size) {
                this.size = size;
            }

            public Builder cheese()    { this.cheese = true;    return this; }
            public Builder pepperoni() { this.pepperoni = true; return this; }
            public Builder mushrooms() { this.mushrooms = true; return this; }

            public Pizza build() {
                return new Pizza(this);
            }
        }
    }

    public static void main(String[] args) {
        // Benefit 1: Node is hidden — we only see LinkedList's public API
        System.out.println("=== Encapsulation (Hidden Node) ===");
        LinkedList list = new LinkedList();
        list.add(3);
        list.add(2);
        list.add(1);
        list.print();
        // LinkedList.Node node = ...  // Compile error! Node is private

        // Benefit 2: Builder is grouped with Pizza
        System.out.println("\n=== Logical Grouping (Builder) ===");
        Pizza pizza = new Pizza.Builder("Large")
                .cheese()
                .pepperoni()
                .mushrooms()
                .build();
        System.out.println(pizza);
    }
}
/*
  Output:
  === Encapsulation (Hidden Node) ===
  [1 -> 2 -> 3]

  === Logical Grouping (Builder) ===
  Large pizza +cheese +pepperoni +mushrooms
*/
