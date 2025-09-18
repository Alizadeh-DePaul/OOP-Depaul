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

package DesignPatterns.Decorator.ClassTestCode;

public class PrintAsciiText implements PrintText {
    public void print(String text)
    {
        System.out.println("Print ASCII: " + text);
    }
}