package interactiveOopCodes.designPatterns.chainOfResponsibility.loggingChain;

/**
 * Severity-thresholded logger chain - a classic CoR application.
 *
 *   ConsoleLogger(INFO)  -> writes if level >= INFO  -> forward
 *   FileLogger(WARN)     -> writes if level >= WARN  -> forward
 *   EmailLogger(ERROR)   -> writes if level >= ERROR -> end
 *
 * Notice that each handler decides INDEPENDENTLY whether to act AND
 * whether to forward - so a single ERROR message lights up all three
 * destinations. This is how java.util.logging.Logger#log actually
 * walks its parent chain.
 *
 *   App started   -> Console only
 *   Cache miss    -> Console + File
 *   OOM on shard  -> Console + File + Email
 */
public class LoggingChain {

    static final int INFO = 1, WARN = 2, ERROR = 3;

    public static void main(String[] args) {
        AbstractLogger console = new ConsoleLogger(INFO);
        AbstractLogger file    = new FileLogger(WARN);
        AbstractLogger email   = new EmailLogger(ERROR);

        console.setNext(file).setNext(email);

        System.out.println("\nlog(INFO, App started):");
        console.log(INFO,  "App started");

        System.out.println("\nlog(WARN, Cache miss spike):");
        console.log(WARN,  "Cache miss spike");

        System.out.println("\nlog(ERROR, OOM on shard 3):");
        console.log(ERROR, "OOM on shard 3");
    }
}

abstract class AbstractLogger {
    protected final int level;
    protected AbstractLogger next;

    AbstractLogger(int level) { this.level = level; }

    public AbstractLogger setNext(AbstractLogger next) {
        this.next = next;
        return next;
    }

    public void log(int level, String msg) {
        if (level >= this.level) write(msg);
        if (next != null) next.log(level, msg);
    }

    protected abstract void write(String msg);
}

class ConsoleLogger extends AbstractLogger {
    ConsoleLogger(int level) { super(level); }
    @Override protected void write(String msg) {
        System.out.println("  [CONSOLE] " + msg);
    }
}

class FileLogger extends AbstractLogger {
    FileLogger(int level) { super(level); }
    @Override protected void write(String msg) {
        System.out.println("  [FILE]    " + msg);
    }
}

class EmailLogger extends AbstractLogger {
    EmailLogger(int level) { super(level); }
    @Override protected void write(String msg) {
        System.out.println("  [EMAIL]   " + msg);
    }
}
