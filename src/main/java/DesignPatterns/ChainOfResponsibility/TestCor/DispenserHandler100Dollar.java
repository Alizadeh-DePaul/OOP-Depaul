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

public class DispenserHandler100Dollar extends DispenserHandler {
    @Override
    void dispense(Dollar dollar) {
        if (dollar.getAmount() >= 100) {
            int num = dollar.getAmount() / 100;
            int remainder = dollar.getAmount() % 100;
            System.out.println("Dispensing " + num + " 100$ bill(s).");
            if(remainder!= 0) {
                this.nextDispenser.dispense(new Dollar(remainder));
            }
        }
        else {
            this.nextDispenser.dispense(dollar);
        }

    }
}
