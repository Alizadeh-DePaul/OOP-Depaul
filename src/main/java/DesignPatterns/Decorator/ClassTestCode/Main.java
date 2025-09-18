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

public class Main {

    public static void main (String[] args){
        String inputText = "Happy Halloween!";

        PrintText basicPrint = new PrintAsciiText();

        basicPrint.print(inputText);

        PrintText hexDecorator = new PrintTextHexDecorator(basicPrint);

        hexDecorator.print(inputText);

        PrintText hexDecDec = new PrintTextHexDecorator(new PrintTextHexDecorator(new PrintAsciiText()));

        hexDecDec.print(inputText);
    }
}
