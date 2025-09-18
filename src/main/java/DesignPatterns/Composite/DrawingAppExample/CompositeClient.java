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

package DesignPatterns.Composite.DrawingAppExample;

public class CompositeClient {

    public static void main(String[] args) {
        Shape tri1 = new Triangle();
        Shape tri2 = new Triangle();
        Shape cir = new Circle();


        Drawing drawing = new Drawing();
        drawing.add(tri1);
        drawing.add(tri2);
        drawing.add(cir);

        cir.draw("red");
        drawing.draw("Red");

        drawing.clear();

        drawing.add(tri1);
        drawing.add(cir);
        drawing.draw("Green");
    }

}