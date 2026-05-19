package interactiveOopCodes.designPatterns.builder.genericBuilder;

/**
 * Structure demo — pure GoF Builder skeleton with all 5 roles.
 *
 *   1) Builder         — interface declaring the build* steps
 *   2) ConcreteBuilder — implements the steps for one Product variant
 *   3) Product         — the resulting object
 *   4) Director        — knows the ORDER of the build* calls
 *   5) Client          — main(); picks a Builder and hands it to the
 *                        Director, then collects the finished Product.
 *
 * Same Director used twice with different Concrete Builders → two
 * different Products. Memorize this shape; every real-world Builder
 * (Roaster, Computer, HttpRequest, StringBuilder) is a variation on
 * these five classes.
 */
public class GenericBuilder {

    public static void main(String[] args) {
        Director director = new Director();

        // Same Director, two Builders → two Products
        ConcreteBuilderA a = new ConcreteBuilderA();
        director.construct(a);
        a.getResult().show("Product A");

        ConcreteBuilderB b = new ConcreteBuilderB();
        director.construct(b);
        b.getResult().show("Product B");
    }
}

/* ─── 1) Product ─────────────────────────────────────────── */
class Product {
    private String partOne;
    private String partTwo;
    private String partThree;

    void setPartOne(String v)   { this.partOne   = v; }
    void setPartTwo(String v)   { this.partTwo   = v; }
    void setPartThree(String v) { this.partThree = v; }

    void show(String label) {
        System.out.printf("%s — { partOne=%s, partTwo=%s, partThree=%s }%n",
                label, partOne, partTwo, partThree);
    }
}

/* ─── 2) Builder interface ──────────────────────────────── */
interface Builder {
    void buildPartOne();
    void buildPartTwo();
    void buildPartThree();
    Product getResult();
}

/* ─── 3) ConcreteBuilder A ─────────────────────────────── */
class ConcreteBuilderA implements Builder {
    private final Product product = new Product();
    public void buildPartOne()   { product.setPartOne("A1"); }
    public void buildPartTwo()   { product.setPartTwo("A2"); }
    public void buildPartThree() { product.setPartThree("A3"); }
    public Product getResult()   { return product; }
}

/* ─── 3) ConcreteBuilder B ─────────────────────────────── */
class ConcreteBuilderB implements Builder {
    private final Product product = new Product();
    public void buildPartOne()   { product.setPartOne("B-alpha"); }
    public void buildPartTwo()   { product.setPartTwo("B-beta"); }
    public void buildPartThree() { product.setPartThree("B-gamma"); }
    public Product getResult()   { return product; }
}

/* ─── 4) Director — knows the ORDER ────────────────────── */
class Director {
    void construct(Builder b) {
        b.buildPartOne();
        b.buildPartTwo();
        b.buildPartThree();
    }
}

/* ─── 5) Client = main() in GenericBuilder ─────────────── */
