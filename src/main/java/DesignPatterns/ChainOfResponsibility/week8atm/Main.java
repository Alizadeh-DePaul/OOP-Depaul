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

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

//        Step 1: instantiating all handlers
        DispenseChain h1 = new Dollar50Dispenser();
        DispenseChain h2 = new Dollar20Dispenser();
        DispenseChain h3 = new Dollar10Dispenser();

//        Step2: creating the chain
        h1.setNext(h2);
        h2.setNext(h3);
//        Step3: processing the request
        while (true){
            int amount = 0;
            System.out.println("How much bruh? \n");

            Scanner input = new Scanner(System.in);
            amount = input.nextInt();

            if (amount % 10 != 0 || amount <= 0) {
                System.out.println("Dude give me an amount that I can handle Jeez!!");
                return;
            }
//            processing the request
            h1.dispense(new Currency(amount));

        }


    }
}
