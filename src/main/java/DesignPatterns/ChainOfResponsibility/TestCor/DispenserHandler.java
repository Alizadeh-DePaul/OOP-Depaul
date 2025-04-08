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

package DesignPatterns.ChainOfResponsibility.TestCor;

public abstract class DispenserHandler {

    public DispenserHandler nextDispenser;

    public void setNextDispenser(DispenserHandler nextDispenser) {
        this.nextDispenser = nextDispenser;
    }

    abstract void dispense(Dollar dollar);
}
