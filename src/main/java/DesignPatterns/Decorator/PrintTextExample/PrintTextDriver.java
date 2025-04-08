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

            final PrintText concreteComponentObj = new PrintAsciiText();

            final PrintText decoratedObj = new PrintTextHexDecorator(concreteComponentObj);

//            concreteComponentObj.print(text);
//            decoratedObj.print(text);

            PrintText newDecoratedObj = new PrintAddedText(concreteComponentObj);
            PrintText doubleDecorated = new PrintTextHexDecorator(new PrintAddedText(concreteComponentObj));

            newDecoratedObj.print(text);
            doubleDecorated.print(text);


        final PrintText doubledecorate = new PrintTextHexDecorator(decoratedObj);
            doubledecorate.print(text);
    }
}
