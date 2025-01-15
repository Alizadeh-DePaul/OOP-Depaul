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

public abstract class Decorator implements GuiComponent {

    public GuiComponent component;

    public Decorator(GuiComponent component) {
        this.component = component;
    }
    @Override
    public void render() {
        this.component.render();
    }
}
