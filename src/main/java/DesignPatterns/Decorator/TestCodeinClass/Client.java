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

public class Client {

    public static void main(String[] args){

        GuiComponent basicButton = new Button();
        basicButton.render();

        System.out.println("================");
//        Decorate - Color
        GuiComponent coloredButton = new ColorDecorator(basicButton);
        coloredButton.render();
        System.out.println("================");
//        Decorate X2 - Colored and bordered button
        GuiComponent borderColorButton =
                new BorderDecorator(new ColorDecorator(basicButton));
        borderColorButton.render();

    }
}
