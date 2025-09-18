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

import java.util.ArrayList;
import java.util.List;

public class CompositeAccount extends AccountComponent
{
    private float totalBalance;
    private AccountStatement compositeStmt, individualStmt;

    protected List<AccountComponent> list = new ArrayList<>();

    public float getBalance() {
        totalBalance = 0;
        for (AccountComponent f : list) {
            totalBalance = totalBalance + f.getBalance();
        }
        return totalBalance;
    }

    public AccountStatement getStatement() {
        for (AccountComponent f : list) {
            individualStmt = f.getStatement();
            compositeStmt.merge(individualStmt);
        }
        return compositeStmt;
    }
    public void add(AccountComponent g) {
        list.add(g);
    }

    public void remove(AccountComponent g) {
        list.remove(g);
    }

    public AccountComponent getChild(int i) {
        return (AccountComponent) list.get(i);
    }
}
