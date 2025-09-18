/*
 *
 *  * Copyright (c) 2023.
 *  * Vahid Alizadeh
 *  * Object-oriented Software Development
 *  * DePaul University
 *
 */

package DesignPatterns.ChainOfResponsibility.ATMExample;

public abstract class DispenseChain {

    public DispenseChain chain;

    public void setNextChain(DispenseChain nextChain) {
        this.chain=nextChain;
    }

   abstract void dispense(Currency cur);
}
