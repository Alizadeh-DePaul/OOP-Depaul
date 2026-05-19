package interactiveOopCodes.designPatterns.builder.fluentBuilder;

/**
 * Implementation demo — Fluent Builder variant (static nested class).
 *
 * No Director needed. Modern Effective-Java style:
 *
 *   • The Builder is a static nested class inside the Product.
 *   • Public Builder constructor takes ALL REQUIRED parameters.
 *   • Setter-style methods for OPTIONAL parameters return `this`,
 *     enabling fluent method chaining.
 *   • Product has a PRIVATE constructor that takes the Builder and
 *     copies its fields — guarantees immutability and one-shot
 *     construction.
 *
 * Same skeleton powers java.lang.StringBuilder, Stream.builder(),
 * java.net.http.HttpRequest.newBuilder(), and OkHttp's Request.Builder.
 */
public class FluentBuilder {

    public static void main(String[] args) {
        // Required (HDD, RAM) via constructor; optional via fluent setters
        Computer dev = new Computer.Builder("2 TB", "64 GB")
                .graphicsCard(true)
                .bluetooth(true)
                .build();
        dev.displaySpec("Dev workstation");

        // Different recipe — no Director coordinating, the client decides
        Computer office = new Computer.Builder("500 GB", "16 GB")
                .bluetooth(true)
                .build();
        office.displaySpec("Office machine");

        // Minimal — only the required fields, all optionals default
        Computer kiosk = new Computer.Builder("128 GB", "4 GB").build();
        kiosk.displaySpec("Kiosk");
    }
}

class Computer {
    // Required (final → immutable)
    private final String hdd;
    private final String ram;
    // Optional (also final, default false in the Builder)
    private final boolean graphicsCard;
    private final boolean bluetooth;

    // PRIVATE — clients can only obtain a Computer through the Builder
    private Computer(Builder b) {
        this.hdd          = b.hdd;
        this.ram          = b.ram;
        this.graphicsCard = b.graphicsCard;
        this.bluetooth    = b.bluetooth;
    }

    void displaySpec(String label) {
        System.out.printf("[%s] HDD=%s, RAM=%s, GPU=%s, BT=%s%n",
                label, hdd, ram, graphicsCard, bluetooth);
    }

    /* ─── Static nested Builder ─────────────────────────── */
    public static class Builder {
        // required
        private final String hdd;
        private final String ram;
        // optional — defaults
        private boolean graphicsCard = false;
        private boolean bluetooth    = false;

        public Builder(String hdd, String ram) {
            this.hdd = hdd;
            this.ram = ram;
        }

        public Builder graphicsCard(boolean v) { this.graphicsCard = v; return this; }
        public Builder bluetooth(boolean v)    { this.bluetooth    = v; return this; }

        public Computer build() {
            return new Computer(this);
        }
    }
}
