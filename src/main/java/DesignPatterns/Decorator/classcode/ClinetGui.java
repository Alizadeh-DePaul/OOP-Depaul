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

public class ClinetGui {

    public static void main(String[] args) {

        GuiComponent button = new Button();
        button.render();

        GuiComponent buttonWithColor = new ColorDecorator(new Button());
        buttonWithColor.render();

        GuiComponent textboxWithColorAndBorder = new FontDecorator(new ColorDecorator(new TextBox()));
        textboxWithColorAndBorder.render();
    }
}
