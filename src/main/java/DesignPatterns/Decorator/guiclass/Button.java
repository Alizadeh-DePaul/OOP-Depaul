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

package DesignPatterns.Decorator.guiclass;

public class Button implements GUIElement {
    @Override
    public void render() {
        System.out.println("Rendering a simple Button");
    }
}
