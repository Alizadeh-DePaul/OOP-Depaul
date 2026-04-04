/**
 * Package & Import Naming Conventions
 *
 * Package names:
 * - All lowercase letters
 * - Reverse domain name (com.company.project.module)
 * - No underscores or hyphens
 *
 * Import conventions:
 * - Oracle: wildcard imports (java.util.*) are acceptable
 * - Google: explicit imports only (java.util.List)
 * - Group: java.*, javax.*, third-party, project
 * - No unused imports!
 */

// GOOD package names:
// package java.lang;
// package com.depaul.se450;
// package org.apache.commons.io;

// BAD package names:
// package Java.Lang;           // uppercase!
// package com.DePaul.SE_450;   // mixed case, underscore!
// package myPackage;            // no domain prefix

public class PackageConventions {

    public static void main(String[] args) {
        System.out.println("=== Package Naming Rules ===");

        String[][] examples = {
            {"java.lang",              "GOOD - JDK core package"},
            {"java.util",              "GOOD - JDK utilities"},
            {"com.depaul.se450",       "GOOD - reverse domain, all lowercase"},
            {"org.apache.commons.io",  "GOOD - third-party library"},
            {"Java.Lang",              "BAD  - uppercase letters!"},
            {"com.DePaul.SE_450",      "BAD  - mixed case, underscore!"},
            {"my_package",             "BAD  - underscore, no domain!"},
        };

        for (String[] ex : examples) {
            System.out.printf("  %-28s  %s%n", ex[0], ex[1]);
        }

        System.out.println("\n=== Import Conventions ===");
        System.out.println("  Oracle style (wildcards OK):");
        System.out.println("    import java.util.*;");
        System.out.println("    import java.io.*;");

        System.out.println("\n  Google style (explicit only):");
        System.out.println("    import java.util.ArrayList;");
        System.out.println("    import java.util.HashMap;");
        System.out.println("    import java.util.List;");

        System.out.println("\n  Import order:");
        System.out.println("    1. java.* imports");
        System.out.println("    2. javax.* imports");
        System.out.println("    3. Third-party (org.*, com.*)");
        System.out.println("    4. Project-specific imports");
    }
}
/*
  Output:
  === Package Naming Rules ===
    java.lang                     GOOD - JDK core package
    java.util                     GOOD - JDK utilities
    com.depaul.se450              GOOD - reverse domain, all lowercase
    org.apache.commons.io         GOOD - third-party library
    Java.Lang                     BAD  - uppercase letters!
    com.DePaul.SE_450             BAD  - mixed case, underscore!
    my_package                    BAD  - underscore, no domain!

  === Import Conventions ===
    Oracle style (wildcards OK):
      import java.util.*;
      import java.io.*;

    Google style (explicit only):
      import java.util.ArrayList;
      import java.util.HashMap;
      import java.util.List;

    Import order:
      1. java.* imports
      2. javax.* imports
      3. Third-party (org.*, com.*)
      4. Project-specific imports
*/
