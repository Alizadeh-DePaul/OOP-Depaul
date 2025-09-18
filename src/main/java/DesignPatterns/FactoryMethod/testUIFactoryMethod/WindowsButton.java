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

package DesignPatterns.FactoryMethod.testUIFactoryMethod;

public class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering button in Windows");
    }
}
