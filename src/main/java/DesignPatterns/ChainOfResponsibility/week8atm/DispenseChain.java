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

package DesignPatterns.ChainOfResponsibility.week8atm;

public abstract class DispenseChain {

    public DispenseChain next;

    public void setNext(DispenseChain next) {
        this.next = next;
    }

    abstract void dispense(Currency currency);
}
