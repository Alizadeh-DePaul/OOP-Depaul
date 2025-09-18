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

package DesignPatterns.FactoryMethod.mower;

public class Riding implements Mower {
    @Override
    public void mow() {
        System.out.println("Riding mowers provide safety and comfort.");
    }
}