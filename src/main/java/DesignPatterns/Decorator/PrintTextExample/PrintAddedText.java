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

public class PrintAddedText extends PrintTextDecorator{

    public PrintAddedText(PrintText inner){
        super(inner);
    }

    @Override
    public void print(String text){

        inner.print("Testing Decorator Pattern \n" + text);
    }
}
