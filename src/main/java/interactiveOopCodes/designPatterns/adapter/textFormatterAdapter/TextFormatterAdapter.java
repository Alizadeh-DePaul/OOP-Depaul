package interactiveOopCodes.designPatterns.adapter.textFormatterAdapter;

/**
 * Adapter Pattern - Text Formatter (OBJECT ADAPTER variant).
 *
 *   A DocumentRenderer (Client) was written against an HtmlProvider interface:
 *   give me HTML, I render it on screen. But the document content lives in a
 *   legacy MarkdownSource that only emits Markdown - it has no idea what HTML
 *   is. We cannot modify either side (Renderer is shared infra, MarkdownSource
 *   is a 3rd-party library).
 *
 *   The MarkdownToHtmlAdapter wraps a MarkdownSource and implements HtmlProvider,
 *   doing a small Markdown -> HTML transformation on the way through.
 *
 *   OBJECT ADAPTER = implements Target + HAS-A Adaptee.
 */
public class TextFormatterAdapter {

    public static void main(String[] args) {
        // ── Adaptee: a 3rd-party Markdown source ──
        MarkdownSource md = new MarkdownSource(
            "# Adapter Pattern\nWraps an **incompatible** interface."
        );

        System.out.println("--- Raw Markdown straight from the Adaptee ---");
        System.out.println(md.getMarkdown());
        System.out.println();

        // ── Client: only knows how to render HtmlProvider ──
        DocumentRenderer renderer = new DocumentRenderer();

        //   renderer.render(md);   // X compile error - HtmlProvider expected

        // ── OBJECT ADAPTER: implements HtmlProvider, HAS-A MarkdownSource ──
        HtmlProvider adapter = new MarkdownToHtmlAdapter(md);
        System.out.println("--- Rendered through the Object Adapter ---");
        renderer.render(adapter);
    }
}

/** TARGET - the interface the Client (DocumentRenderer) expects. */
interface HtmlProvider {
    String getHtml();
}

/** ADAPTEE - a legacy / 3rd-party Markdown source. */
class MarkdownSource {
    private final String markdown;
    public MarkdownSource(String markdown) { this.markdown = markdown; }
    public String getMarkdown() { return markdown; }
}

/** OBJECT ADAPTER - wraps a MarkdownSource, exposes HTML. */
class MarkdownToHtmlAdapter implements HtmlProvider {

    private final MarkdownSource source;   // HAS-A

    public MarkdownToHtmlAdapter(MarkdownSource source) {
        this.source = source;
    }

    @Override
    public String getHtml() {
        String md = source.getMarkdown();
        // small illustrative conversion (not a full Markdown parser)
        return md
            .replaceAll("(?m)^# (.+)$", "<h1>$1</h1>")
            .replaceAll("\\*\\*(.+?)\\*\\*", "<strong>$1</strong>")
            .replace("\n", "<br>");
    }
}

/** CLIENT - sees only the Target interface. */
class DocumentRenderer {
    public void render(HtmlProvider provider) {
        System.out.println(provider.getHtml());
    }
}
