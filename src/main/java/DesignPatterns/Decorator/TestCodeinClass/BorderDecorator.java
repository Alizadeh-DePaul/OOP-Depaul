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

public class BorderDecorator extends Decorator {

    public BorderDecorator(GuiComponent component) {
        super(component);
    }

    @Override
    public void render(){
        super.render();
        System.out.println("\n Rendering border around the GUI element.");
    }
}
