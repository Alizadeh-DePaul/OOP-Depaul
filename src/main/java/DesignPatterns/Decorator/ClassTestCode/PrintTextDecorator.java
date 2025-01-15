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

public abstract class PrintTextDecorator implements PrintText{

    public  PrintText printText;

    public PrintTextDecorator(PrintText txt){
        this.printText = txt;
    }
}
