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

package DesignPatterns.Composite.FinancialAppExample;

public class FinancialAppClient {
    public static void main(String[] args)
    {
        // Creating a component tree
        CompositeAccount compositeAccount = new CompositeAccount();

        // Adding all accounts of a customer to component
        compositeAccount.add(new DepositAccount("DA450", 100));
        compositeAccount.add(new DepositAccount("DA451", 150));
        compositeAccount.add(new SavingsAccount("SA452", 200));

        // getting composite balance for the customer
        float totalBalance = compositeAccount.getBalance();
        System.out.println("Total Balance : " + totalBalance);

        CompositeAccount compositeAccount2 = new CompositeAccount();
        compositeAccount2.add(new DepositAccount("SE350", 1000));
        compositeAccount2.add(compositeAccount);

        float totalBalance2 = compositeAccount2.getBalance();
        System.out.println("Total Balance : " + totalBalance2);

        //AccountStatement mergedStatement = component.getStatement();
        //System.out.println("Merged Statement : " + mergedStatement);
    }
}
