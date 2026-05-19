package interactiveOopCodes.designPatterns.builder.httpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Real-World demo — Custom HttpRequest with a Fluent Builder.
 *
 * Modeled directly on java.net.http.HttpRequest (Java 11+) and
 * OkHttp's Request.Builder. Production HTTP libraries use this
 * exact shape:
 *
 *   • Required:  URL (constructor)
 *   • Optional:  method (default GET), headers, body, timeout
 *
 * The Builder is obtained via a static factory method
 * `HttpRequest.newBuilder(url)` — matching the JDK convention.
 */
public class HttpRequest {

    public static void main(String[] args) {
        // GET request with headers + timeout
        HttpRequest get = HttpRequest.newBuilder("https://api.example.com/users/42")
                .header("Accept", "application/json")
                .header("Authorization", "Bearer xyz")
                .timeout(5_000)
                .build();
        get.send();

        // POST request with body
        HttpRequest post = HttpRequest.newBuilder("https://api.example.com/orders")
                .method("POST")
                .header("Content-Type", "application/json")
                .body("{\"item\":\"coffee\",\"qty\":2}")
                .timeout(10_000)
                .build();
        post.send();

        // Minimal — only the required URL, all other fields default
        HttpRequest minimal = HttpRequest.newBuilder("https://example.com").build();
        minimal.send();
    }

    private final String url;
    private final String method;
    private final Map<String, String> headers;
    private final String body;
    private final int    timeoutMs;

    private HttpRequest(Builder b) {
        this.url       = b.url;
        this.method    = b.method;
        this.headers   = Map.copyOf(b.headers);  // defensive immutable copy
        this.body      = b.body;
        this.timeoutMs = b.timeoutMs;
    }

    void send() {
        System.out.printf("%n→ %s %s  (timeout=%dms)%n", method, url, timeoutMs);
        headers.forEach((k, v) -> System.out.printf("    %s: %s%n", k, v));
        if (body != null) {
            System.out.println("    BODY: " + body);
        }
    }

    /* ─── Static factory matching the JDK / OkHttp idiom ─── */
    public static Builder newBuilder(String url) {
        return new Builder(url);
    }

    /* ─── Static nested Builder ───────────────────────── */
    public static class Builder {
        // required
        private final String url;
        // optional — sensible defaults
        private String method    = "GET";
        private final Map<String, String> headers = new HashMap<>();
        private String body      = null;
        private int    timeoutMs = 30_000;

        private Builder(String url) {
            this.url = url;
        }

        public Builder method(String m)              { this.method = m;       return this; }
        public Builder header(String k, String v)    { this.headers.put(k, v); return this; }
        public Builder body(String b)                { this.body = b;         return this; }
        public Builder timeout(int ms)               { this.timeoutMs = ms;   return this; }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }
}
