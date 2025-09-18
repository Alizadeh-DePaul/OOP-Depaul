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

public abstract class PrintTextDecorator implements PrintText {
    protected PrintText inner;
    public PrintTextDecorator(PrintText inner)
    {
        this.inner = inner;
    }
    public abstract void print(String text);
}
