package interactiveOopCodes.fundamentals.codingConventions.fileOrganization;

/**
 * Java Source File Organization
 *
 * A well-organized Java source file follows this structure:
 * 1. Package declaration
 * 2. Import statements (no wildcards in Google style)
 * 3. Exactly one top-level class per file
 * 4. Class members ordered logically
 *
 * Best practice: Keep files under 2000 lines of code!
 *
 * @see <a href="https://www.oracle.com/java/technologies/javase/codeconventions-fileorganization.html">
 *      Oracle File Organization</a>
 */

// 1. Package declaration (first non-comment line)
// package com.depaul.examples;

// 2. Import statements (grouped and ordered)
// -- java.* imports first
// -- javax.* imports second
// -- third-party imports third
// -- project imports last
import java.util.ArrayList;
import java.util.List;

// 3. Class declaration (one per file, name matches filename)
public class FileOrganization {

    // === Class members order (Oracle recommendation) ===

    // (a) Static constants
    public static final int MAX_FILE_LINES = 2000;
    public static final String DEFAULT_ENCODING = "UTF-8";

    // (b) Static fields
    private static int fileCount = 0;

    // (c) Instance fields
    private String fileName;
    private int lineCount;
    private List<String> sections;

    // (d) Constructors
    public FileOrganization(String fileName) {
        this.fileName = fileName;
        this.lineCount = 0;
        this.sections = new ArrayList<>();
        fileCount++;
    }

    // (e) Public methods
    public void addSection(String section) {
        sections.add(section);
    }

    public boolean isWithinLimit() {
        return lineCount < MAX_FILE_LINES;
    }

    // (f) Private/helper methods
    private String formatSummary() {
        return fileName + " (" + lineCount + " lines, "
                + sections.size() + " sections)";
    }

    // (g) toString, equals, hashCode
    @Override
    public String toString() {
        return formatSummary();
    }

    public static void main(String[] args) {
        FileOrganization file = new FileOrganization("Student.java");
        file.lineCount = 150;
        file.addSection("Fields");
        file.addSection("Constructors");
        file.addSection("Methods");

        System.out.println("=== File Organization Demo ===");
        System.out.println("File: " + file);
        System.out.println("Within 2000-line limit? " + file.isWithinLimit());
        System.out.println("Total files created: " + fileCount);

        System.out.println("\n=== Recommended Order ===");
        String[] order = {
            "1. Package declaration",
            "2. Import statements",
            "3. Class/interface declaration",
            "   a. Static constants",
            "   b. Static fields",
            "   c. Instance fields",
            "   d. Constructors",
            "   e. Public methods",
            "   f. Private methods",
            "   g. toString/equals/hashCode"
        };
        for (String item : order) {
            System.out.println("  " + item);
        }
    }
}
/*
  Output:
  === File Organization Demo ===
  File: Student.java (150 lines, 3 sections)
  Within 2000-line limit? true
  Total files created: 1

  === Recommended Order ===
    1. Package declaration
    2. Import statements
    3. Class/interface declaration
       a. Static constants
       b. Static fields
       c. Instance fields
       d. Constructors
       e. Public methods
       f. Private methods
       g. toString/equals/hashCode
*/
