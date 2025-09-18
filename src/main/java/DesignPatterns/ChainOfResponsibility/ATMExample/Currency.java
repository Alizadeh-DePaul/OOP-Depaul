/*
 *
 *  * Copyright (c) 2023.
 *  * Vahid Alizadeh
 *  * Object-oriented Software Development
 *  * DePaul University
 *
 */

package DesignPatterns.ChainOfResponsibility.ATMExample;

public class Currency {

    private int amount;

    public Currency(int amt){
        this.amount=amt;
    }

    public int getAmount(){
        return this.amount;
    }
}