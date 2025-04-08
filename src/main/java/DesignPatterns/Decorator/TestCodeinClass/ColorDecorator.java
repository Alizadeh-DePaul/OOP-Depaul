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

public class ColorDecorator extends Decorator {

    public ColorDecorator(GuiComponent component) {
        super(component);
    }

    @Override
    public void render(){
//        super.render();
        System.out.println("\n A random color is added to the GUI element background!");
    }
}
