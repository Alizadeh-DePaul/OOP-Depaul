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

package DesignPatterns.Decorator.PrintTextExample;

public class PrintTextDriver {
    public static void main (String[] args) {
            final String text = "OOP - Decorator Pattern Example";
            final PrintText object = new PrintAsciiText();
            final PrintText printer = new PrintTextHexDecorator(object);
            object.print(text);
            printer.print(text);
        final PrintText doubledecorate = new PrintTextHexDecorator(printer);
            doubledecorate.print(text);
    }
}
