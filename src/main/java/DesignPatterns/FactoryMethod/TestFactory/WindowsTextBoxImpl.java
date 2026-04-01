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

public class WindowsTextBoxImpl implements TextBox {
    @Override
    public void render() {
        System.out.println("Windows text box is rendered!");
    }
}
