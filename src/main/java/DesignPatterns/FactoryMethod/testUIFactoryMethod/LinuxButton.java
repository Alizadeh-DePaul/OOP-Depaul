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

public class LinuxButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Linux Button");
    }
}
