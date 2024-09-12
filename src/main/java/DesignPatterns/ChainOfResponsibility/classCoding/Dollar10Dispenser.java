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

public class Dollar10Dispenser implements DispenseChain {

    private DispenseChain nextHandler;
    @Override
    public void setNextChain(DispenseChain next) {
        this.nextHandler = next;

    }

    @Override
    public void dispense(Currency currency) {
        if(currency.getAmount() >= 10) {
            int number = currency.getAmount()/10;
            int remainder = currency.getAmount() % 10;

            System.out.println("I am dispensing " + number + " 10$ bills!");
            if(remainder!=0) nextHandler.dispense(new Currency(remainder));
        } else {
            nextHandler.dispense(currency);
        }

    }
}
