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

public class Decorator implements GUIElement {

    public GUIElement element;

    public Decorator(GUIElement element) {
        this.element = element;
    }
    @Override
    public void render() {
        this.element.render();
    }
}
