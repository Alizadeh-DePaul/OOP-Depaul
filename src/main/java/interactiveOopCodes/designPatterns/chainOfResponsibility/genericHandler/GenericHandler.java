package interactiveOopCodes.designPatterns.chainOfResponsibility.genericHandler;

/**
 * GoF textbook structure of Chain of Responsibility - no business logic.
 *
 *   Handler            - abstract class with the link + delegation contract
 *   ConcreteHandlerA/B/C - try to handle, else forward
 *   Client             - holds the head and fires requests
 *
 * Useful as a copy-paste skeleton for any real CoR implementation.
 * Note the fluent setNext() that returns the linked handler so the
 * chain can be assembled in a single expression:
 *     h1.setNext(h2).setNext(h3);
 */
public class GenericHandler {

    public static void main(String[] args) {
        Handler h1 = new ConcreteHandlerA();
        Handler h2 = new ConcreteHandlerB();
        Handler h3 = new ConcreteHandlerC();

        h1.setNext(h2).setNext(h3);

        String[] requests = { "A-task", "B-task", "C-task", "Z-task" };
        for (String r : requests) {
            System.out.println("\nFiring \"" + r + "\":");
            h1.handle(r);
        }
    }
}

abstract class Handler {
    protected Handler next;

    public Handler setNext(Handler next) {
        this.next = next;
        return next;                 // fluent chaining
    }

    public abstract void handle(String request);

    protected void forward(String request) {
        if (next != null) {
            next.handle(request);
        } else {
            System.out.println("  >> unhandled: " + request);
        }
    }
}

class ConcreteHandlerA extends Handler {
    @Override public void handle(String request) {
        if (request.startsWith("A")) {
            System.out.println("  A handled " + request);
        } else {
            forward(request);
        }
    }
}

class ConcreteHandlerB extends Handler {
    @Override public void handle(String request) {
        if (request.startsWith("B")) {
            System.out.println("  B handled " + request);
        } else {
            forward(request);
        }
    }
}

class ConcreteHandlerC extends Handler {
    @Override public void handle(String request) {
        if (request.startsWith("C")) {
            System.out.println("  C handled " + request);
        } else {
            forward(request);
        }
    }
}
