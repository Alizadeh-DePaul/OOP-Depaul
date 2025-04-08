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

public class WindowsComponentImpl implements Component {
    @Override
    public TextBox renderTextbox() {
        return new WindowsTextBoxImpl();
    }
}
