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

package DesignPatterns.FactoryMethod.UI;

public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Windows button is created!");
    }
}
