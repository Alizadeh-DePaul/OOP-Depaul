package interactiveOopCodes.fundamentals.oopBuildingBlocks.constructorDemo;

public class ConstructorDemo {
    public static void main(String[] args) {
        // Default constructor (no arguments)
        Book book1 = new Book();
        System.out.println(book1.getInfo());
        // Unknown Title by Unknown Author

        // Parameterized constructor
        Book book2 = new Book("Clean Code", "Robert Martin");
        System.out.println(book2.getInfo());
        // Clean Code by Robert Martin

        // Overloaded constructor with all fields
        Book book3 = new Book("Design Patterns", "GoF", 395);
        System.out.println(book3.getInfo() + " (" + book3.getPages() + " pages)");
        // Design Patterns by GoF (395 pages)
    }
}

class Book {
    private String title;
    private String author;
    private int pages;

    // Default constructor — no parameters
    Book() {
        this.title = "Unknown Title";
        this.author = "Unknown Author";
        this.pages = 0;
    }

    // Parameterized constructor — two parameters
    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.pages = 0;
    }

    // Overloaded constructor — three parameters
    Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    String getInfo() { return title + " by " + author; }
    int getPages() { return pages; }
}
