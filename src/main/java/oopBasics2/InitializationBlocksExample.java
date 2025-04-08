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

public class InitializationBlocksExample {

    int a, b, c;
    // Initialization block-1
    {
        System.out.println("Initialization block-1 is executed. Setting a=1.");
        a = 1;
    }
    
    // Constructor
    InitializationBlocksExample() {
        System.out.println("User-defined parameterless constructor is executed.Setting c=3.");
        c = 3;
    }

    // Initialization block-2
    {
        System.out.println("Initialization block-2 is executed. Setting b=2;");
        b = 2;
    }
}

class DemoInitBlock {
    public static void main(String[] args) {
        System.out.println("***Using instance Initialization blocks.***\n");
        InitializationBlocksExample sampleObject = new InitializationBlocksExample();
        System.out.println("The sampleObject.a=" + sampleObject.a);// 1
        System.out.println("The sampleObject.b=" + sampleObject.b);// 2
        System.out.println("The sampleObject.c=" + sampleObject.c);// 3
    }
}
