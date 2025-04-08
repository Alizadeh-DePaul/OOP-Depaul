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

//First step: Implementing/Extending the common Component we want to decorator
public class Decorator implements GuiComponent {

//    Second Step: Storing an object of the component
    public GuiComponent component;

//    Third: A constructor OR a method that receives the object that we want to decorate (wrapping the object)
    public Decorator(GuiComponent component){
        this.component = component;
    }

//    Fourth Step: Overriding the behavior, first invoke the original behavior
    @Override
    public void render() {
        this.component.render();
    }
}
