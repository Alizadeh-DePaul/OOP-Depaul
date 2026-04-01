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

public class WindowsDialog extends DialogWindow {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
