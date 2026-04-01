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

package DesignPatterns.Decorator.classcode;

public class TextBox implements GuiComponent {
    @Override
    public void render() {
        System.out.println("Render a basic text box");
    }
}
