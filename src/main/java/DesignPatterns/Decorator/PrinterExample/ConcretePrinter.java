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

public class ConcretePrinter extends Printer {

    @Override
    public void flushBuffer() {

        System.out.println("Message from Concrete Printer: Printer Buffer Flushed");
    }
}
