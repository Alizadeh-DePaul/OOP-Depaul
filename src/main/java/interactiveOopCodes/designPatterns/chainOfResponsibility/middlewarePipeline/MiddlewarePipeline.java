package interactiveOopCodes.designPatterns.chainOfResponsibility.middlewarePipeline;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Modern, functional Chain of Responsibility - no Handler subclasses.
 *
 * Each middleware is a Function<Optional<Request>, Optional<Request>>:
 *   - return Optional.of(req) to forward (possibly modified)
 *   - return Optional.empty()  to short-circuit (reject / unhandled)
 *
 * Compose with Function.andThen() - zero subclasses, ordering is just
 * the call order, and the pipeline is fully runtime-reconfigurable.
 *
 * Trade-off (and why classical CoR is still relevant):
 *   * Pro: 1 file instead of 1 file per handler; lambdas all the way down.
 *   * Con: NO type-level enforcement of "must forward" - a buggy lambda
 *          can silently swallow a request without anyone noticing.
 *   * Con: stack trace shows "lambda$0$..." not a meaningful class name.
 */
public class MiddlewarePipeline {

    public static void main(String[] args) {
        Function<Optional<Request>, Optional<Request>> pipeline =
              authMiddleware()
                .andThen(loggingMiddleware())
                .andThen(rateLimitMiddleware())
                .andThen(cacheMiddleware());

        List<Request> requests = List.of(
            new Request("GET /users",  "alice",  1),
            new Request("GET /admin",  null,     1),
            new Request("GET /search", "bob",   25)
        );

        for (Request req : requests) {
            System.out.println("\n>> " + req);
            Optional<Request> result = pipeline.apply(Optional.of(req));
            System.out.println("   final: " + result.map(Object::toString).orElse("REJECTED"));
        }
    }

    static Function<Optional<Request>, Optional<Request>> authMiddleware() {
        return opt -> opt.flatMap(r -> {
            if (r.user == null) {
                System.out.println("   [auth]   reject (anonymous)");
                return Optional.empty();
            }
            System.out.println("   [auth]   pass for " + r.user);
            return Optional.of(r);
        });
    }

    static Function<Optional<Request>, Optional<Request>> loggingMiddleware() {
        return opt -> opt.map(r -> {
            System.out.println("   [log]    " + r.path);
            return r;
        });
    }

    static Function<Optional<Request>, Optional<Request>> rateLimitMiddleware() {
        return opt -> opt.flatMap(r -> {
            if (r.reqPerSec > 20) {
                System.out.println("   [rate]   reject (" + r.reqPerSec + " req/s)");
                return Optional.empty();
            }
            System.out.println("   [rate]   pass (" + r.reqPerSec + " req/s)");
            return Optional.of(r);
        });
    }

    static Function<Optional<Request>, Optional<Request>> cacheMiddleware() {
        return opt -> opt.map(r -> {
            System.out.println("   [cache]  serve " + r.path);
            return r;
        });
    }
}

class Request {
    final String path;
    final String user;
    final int reqPerSec;
    Request(String path, String user, int reqPerSec) {
        this.path = path;
        this.user = user;
        this.reqPerSec = reqPerSec;
    }
    @Override public String toString() {
        return path + " user=" + user + " rate=" + reqPerSec;
    }
}
