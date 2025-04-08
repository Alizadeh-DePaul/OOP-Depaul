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

package DesignPatterns.Builder.ComputerExample;

public class TestFluentBuilderPattern {

    public static void main(String[] args) {
        //Using builder to get the object in a single line of code and
        //without any inconsistent state or arguments management issues
        Computer comp = new Computer.ComputerBuilder("2 TB", "64 GB")
                                    .setBluetoothEnabled(true)
                                    .setGraphicsCardEnabled(true)
                                    .build();

        comp.displySpec();
    }
}
