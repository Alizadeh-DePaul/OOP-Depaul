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

package DesignPatterns.Decorator.ClassTestCode;

import java.util.stream.Collectors;

public class PrintTextHexDecorator extends PrintTextDecorator {

    public PrintTextHexDecorator(PrintText txt) {
        super(txt);
    }

    public void print(String text){
        String hex = text.chars()
                .boxed()
                .map(x -> "0x" + Integer.toHexString(x))
                .collect(Collectors.joining(" "));

        printText.print(text + "====> HEX:" + hex);

    }
}
