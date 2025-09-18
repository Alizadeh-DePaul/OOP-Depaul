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

public class Button implements GuiComponent {
    @Override
    public void render() {
        System.out.println("Render a basic button");
    }
}
