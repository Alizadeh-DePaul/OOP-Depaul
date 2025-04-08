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

package DesignPatterns.Decorator.TestCodeinClass;

public class Button implements GuiComponent {
    @Override
    public void render() {
        System.out.println("The button is rendered!");
    }
}
