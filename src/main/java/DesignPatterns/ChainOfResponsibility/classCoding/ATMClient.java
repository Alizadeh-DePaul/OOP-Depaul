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

import java.util.Scanner;

public class ATMClient {

    public static void main(String[] args){

//        1. instantiate all concrete handlers
        DispenseChain dispense10 = new Dollar10Dispenser();
        DispenseChain dispense20 = new Dollar20Dispenser();
        DispenseChain dispense50 = new Dollar50Dispenser();

//        2. Create chain
        dispense50.setNextChain(dispense20);
        dispense20.setNextChain(dispense10);

//        3. Pass data to the chain. Pass data to the first object in the chain by invoking the process() method

        while (true) {
            int userInput = 0;
            System.out.println("Please enter the amount:");
            Scanner inputScanner = new Scanner(System.in);
            userInput = inputScanner.nextInt();

            if (userInput % 10 != 0) {
                System.out.println("Pls multiple of 10 :* <3");
                return;
            }

            dispense50.dispense(new Currency(userInput));
        }





    }
}
