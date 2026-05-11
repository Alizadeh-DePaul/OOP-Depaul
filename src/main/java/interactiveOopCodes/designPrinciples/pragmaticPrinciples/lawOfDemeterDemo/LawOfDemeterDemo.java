package interactiveOopCodes.designPrinciples.pragmaticPrinciples.lawOfDemeterDemo;

/**
 * Law of Demeter — Talk only to your friends.
 *
 * Bad : order.getCustomer().getAddress().getCity() — a three-link
 *       "train wreck". The caller now knows the entire object graph;
 *       a rename anywhere along the path breaks it.
 * Good: order.shipsToChicago() — the order is asked once and answers.
 *       Tell, don't ask.
 *
 * Important caveat: stream chains s.filter().map().collect() are NOT
 * LoD violations because each call returns the same Stream&lt;T&gt;.
 * LoD is about navigating *different* types in sequence, not about
 * chained builders or fluent APIs.
 */
public class LawOfDemeterDemo {

    public static void main(String[] args) {
        Order o = new Order(new Customer(new Address("Chicago", "60601")));

        System.out.println("=== Bad: train wreck ===");
        System.out.println("  ships to Chicago? " + BadShipping.shipsToChicago(o));

        System.out.println("\n=== Good: tell, don't ask ===");
        System.out.println("  ships to Chicago? " + o.shipsToChicago());
    }
}

class Address {
    private final String city;
    private final String zip;
    Address(String city, String zip) { this.city = city; this.zip = zip; }
    String getCity()    { return city; }
    String getZip()     { return zip; }
    boolean isChicago() { return "Chicago".equalsIgnoreCase(city); }
}

class Customer {
    private final Address address;
    Customer(Address a) { address = a; }
    Address getAddress() { return address; }              // kept for the bad demo
    boolean isInChicago() { return address.isChicago(); } // tell, don't ask
}

class Order {
    private final Customer customer;
    Order(Customer c) { customer = c; }
    Customer getCustomer() { return customer; }                // kept for the bad demo
    boolean shipsToChicago() { return customer.isInChicago(); } // tell, don't ask
}

class BadShipping {
    // Three different types accessed in one expression — strangers' strangers.
    static boolean shipsToChicago(Order o) {
        return o.getCustomer().getAddress().getCity().equalsIgnoreCase("Chicago");
    }
}
