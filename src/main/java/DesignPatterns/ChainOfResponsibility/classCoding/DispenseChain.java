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

package DesignPatterns.ChainOfResponsibility.classCoding;

public interface DispenseChain {

    void setNextChain(DispenseChain next);

    void dispense(Currency currency);
}
