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

package DesignPatterns.AbstractFactory.MotorHome;

public class TypeAFactory extends MotorHomeAbstractFactory {


    public Frame createFrame() {
        return new TypeAFrame();
    }

    public Style createStyle() {
        return new TypeAStyle();
    }

    public Engine createEngine() {
        return new TypeAEngine();
    }

    public Kitchen createKitchen() {
        return new TypeAKitchen();
    }
}
