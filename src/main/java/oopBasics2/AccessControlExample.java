/*
 *
 *  *
 *  *  * Copyright (c) 2024.
 *  *  * Vahid Alizadeh
 *  *  * Object-oriented Software Development
 *  *  * DePaul University
 *  *
 *
 */

package oopBasics2;

public class AccessControlExample {
    // Public field
    public int publicInt = 1;

    // Public method
    public void showPublicMethod() {
        System.out.println("The showPublicMethod() is a public method.");
    }

    // Private field
    private int privateInt = 2;

    // Private method
    private void showPrivateMethod() {
        System.out.println("The showPrivateMethod() is a private method.");
    }
}

class DemoAccessControl {
    public static void main(String[] args) {
        System.out.println("***Access control example. using private and public modifiers.***\n");
        AccessControlExample sampleOb = new AccessControlExample();
        System.out.println("The pubInt=" + sampleOb.publicInt);// 1
        sampleOb.showPublicMethod();
//      Compile-time error
//      System.out.println(" The priInt="+ sampleOb.privateInt);
//      Compile-time error
//      sampleOb.showPrivateMethod() ;
    }
}
