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

abstract class AbstractDecorator extends Printer {

    protected Printer printer;

    public void selectPrinterToFlush(Printer ptr) {
        printer = ptr;
    }

    public void flushBuffer() {
        if (printer != null) {
            printer.flushBuffer();
        }
    }
}
