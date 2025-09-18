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

package DesignPatterns.FactoryMethod.TestFactory;

public class LinuxTextBoxImpl implements TextBox {
    @Override
    public void render() {
        System.out.println("Linux text box is rendered!");
    }
}
