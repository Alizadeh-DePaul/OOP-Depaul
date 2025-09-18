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

public class CreateBankAccount {
    private static CreateBankAccount newAccount;
    // constructor
    private CreateBankAccount() {
//        print time in nice format
        System.out.println("Account created at \t" + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));

    }
    public static CreateBankAccount getNewAccount() {
        if (newAccount == null) {
            newAccount = new CreateBankAccount();
            System.out.println("New Account created.");
        } else {
            System.out.println("Account already opened.");
        }
        return newAccount;
    }

    public static String logPattern() {
        return null;
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("App start \t " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
//        String logPattern = CreateBankAccount.logPattern();

        Thread.sleep(5000);


        System.out.println("#######################");



        // create new account
        CreateBankAccount account1 = CreateBankAccount.getNewAccount();
        // create second account
        CreateBankAccount account2 = CreateBankAccount.getNewAccount();

        System.out.println("#######################");
    }
}