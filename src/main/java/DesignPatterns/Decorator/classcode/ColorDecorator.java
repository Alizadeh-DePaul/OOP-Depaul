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

public class ColorDecorator extends Decorator {
    public ColorDecorator(GuiComponent component) {
        super(component);
    }

    @Override
    public void render() {
        super.render();
        System.out.println("Adding color to the component");
    }
}
