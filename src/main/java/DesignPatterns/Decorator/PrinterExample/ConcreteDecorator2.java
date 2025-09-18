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

package DesignPatterns.Decorator.PrinterExample;

public class ConcreteDecorator2 extends AbstractDecorator {

    public void flushBuffer() {

        System.out.println("\n[ START ] Concrete Decorator 2 Wrapper [ START ]");
        super.flushBuffer();

        System.out.println("Message from Concrete Decorator 2: " +
                        "Printer Buffer Flushed");
        System.out.println("[ END ] Concrete Decorator 2 Wrapper [ END ]");
    }
}
