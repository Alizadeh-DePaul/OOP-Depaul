/*
 *
 *  * Copyright (c) 2023.
 *  * Vahid Alizadeh
 *  * Object-oriented Software Development
 *  * DePaul University
 *
 */

package DesignPatterns.ChainOfResponsibility.ATMExample;

public class Dollar20Dispenser extends DispenseChain{


    @Override
    public void dispense(Currency cur) {
        if(cur.getAmount() >= 20){
            int num = cur.getAmount()/20;
            int remainder = cur.getAmount() % 20;
            System.out.println("Dispensing "+num+" 20$ note");
            if(remainder !=0) this.chain.dispense(new Currency(remainder));
        }else{
            this.chain.dispense(cur);
        }
    }

}
