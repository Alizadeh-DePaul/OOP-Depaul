package interactiveOopCodes.designPatterns.chainOfResponsibility.universityEmail;

/**
 * University Email Router via Chain of Responsibility.
 *
 * Each handler inspects the subject keyword. If it matches, the handler
 * ROUTES the mail and stops the chain (pure CoR semantics - exactly one
 * handler wins). Otherwise it forwards to the next handler.
 *
 * Adding a new category (e.g. ALUMNI_DONATION) means writing one new
 * handler class and adding one setNext() call - no edits to existing
 * handlers. That is the Open/Closed Principle for free.
 *
 * The DefaultHandler at the end is a "common handler" / catch-all so
 * the chain never silently drops a mail.
 */
public class UniversityEmail {

    public static void main(String[] args) {
        EmailHandler chain = new SpamHandler();
        chain.setNext(new FanHandler())
             .setNext(new ComplaintHandler())
             .setNext(new NewLocationHandler())
             .setNext(new DefaultHandler());

        String[] subjects = {
            "SPAM_MAIL",
            "FAN_MAIL",
            "COMPLAINT_MAIL",
            "NEW_LOC_MAIL",
            "RANDOM_INQUIRY"
        };
        for (String subject : subjects) {
            System.out.println("\n>> Incoming mail: " + subject);
            chain.forward(new Mail(subject));
        }
    }
}

class Mail {
    private final String subject;
    public Mail(String subject) { this.subject = subject; }
    public String getSubject() { return subject; }
}

abstract class EmailHandler {
    protected EmailHandler next;

    public EmailHandler setNext(EmailHandler next) {
        this.next = next;
        return next;
    }

    public final void forward(Mail mail) {
        if (canHandle(mail)) {
            route(mail);
        } else if (next != null) {
            next.forward(mail);
        } else {
            System.out.println("   (no handler claimed " + mail.getSubject() + ")");
        }
    }

    protected abstract boolean canHandle(Mail mail);
    protected abstract void route(Mail mail);
}

class SpamHandler extends EmailHandler {
    @Override protected boolean canHandle(Mail m) { return m.getSubject().equals("SPAM_MAIL"); }
    @Override protected void route(Mail m) { System.out.println("   -> /dev/null (spam filter)"); }
}

class FanHandler extends EmailHandler {
    @Override protected boolean canHandle(Mail m) { return m.getSubject().equals("FAN_MAIL"); }
    @Override protected void route(Mail m) { System.out.println("   -> Public Relations team"); }
}

class ComplaintHandler extends EmailHandler {
    @Override protected boolean canHandle(Mail m) { return m.getSubject().equals("COMPLAINT_MAIL"); }
    @Override protected void route(Mail m) { System.out.println("   -> Ombudsperson office"); }
}

class NewLocationHandler extends EmailHandler {
    @Override protected boolean canHandle(Mail m) { return m.getSubject().equals("NEW_LOC_MAIL"); }
    @Override protected void route(Mail m) { System.out.println("   -> Facilities + HR (relocation)"); }
}

class DefaultHandler extends EmailHandler {
    @Override protected boolean canHandle(Mail m) { return true; }
    @Override protected void route(Mail m) { System.out.println("   -> general@university.edu (catch-all)"); }
}
