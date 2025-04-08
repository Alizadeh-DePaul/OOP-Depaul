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

package DesignPatterns.Singleton.BankAccountUsecase;

public class AccountDriver {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("App start \t " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
//        String logPattern = CreateBankAccount.logPattern();

//        Thread.sleep(5000);


        System.out.println("#######################");



        // create new account
        CreateBankAccount account1 = CreateBankAccount.getNewAccount();
        // create second account
        CreateBankAccount account2 = CreateBankAccount.getNewAccount();

        System.out.println("#######################");
    }
}