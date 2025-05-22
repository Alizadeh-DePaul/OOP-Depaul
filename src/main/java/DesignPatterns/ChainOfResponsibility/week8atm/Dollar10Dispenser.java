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

public class Dollar10Dispenser extends DispenseChain {
    @Override
    void dispense(Currency currency) {

        if(currency.getAmount() >= 10){
            int num = currency.getAmount() / 10;
            int rem = currency.getAmount() % 10;
            System.out.println("\n ATM is dispensing " + num + " 10$ bills.");

            if(rem != 0) this.next.dispense(new Currency(rem));
        }else {
            this.next.dispense(currency);
        }
    }
}
