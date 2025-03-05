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

import java.util.Scanner;

public class Client {

    public static void main(String[] arg){

//        Step 1: Instantiate all handlers
        DispenserHandler h1 = new DispenserHandler100Dollar();
        DispenserHandler h2 = new DispenserHandler20Dollar();
        DispenserHandler h3 = new DispenserHandler10Dollar();

//        Step 2: Creating the CHAIN
        h1.setNextDispenser(h2);
        h2.setNextDispenser(h3);

//        Step 3: Pass the request to the first handler object in the chain

        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter your amount: \n >_");

            int amount = input.nextInt();

            if (amount % 10 != 0) {
                System.out.println(" Wrong input - mult 10");
                return;
            }

            h1.dispense(new Dollar(amount));
        }
    }
}
