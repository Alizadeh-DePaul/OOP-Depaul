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

public class Push implements Mower {
    @Override
    public void mow() {
        System.out.println("Push mowers are good for small yards.");
    }
}