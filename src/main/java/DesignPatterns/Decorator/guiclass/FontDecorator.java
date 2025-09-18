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

public class FontDecorator extends Decorator {
    public FontDecorator(GUIElement element) {
        super(element);
    }

    @Override
    public void render() {
        super.render();
        System.out.println("Rendering a Bold/Italic element");

    }
}
