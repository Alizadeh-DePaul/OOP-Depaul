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

package DesignPatterns.FactoryMethod.TestFactory;

import java.util.Scanner;

public class Client {

    public static void main(String[] arg){
        Scanner in = new Scanner(System.in);

        System.out.println("Hey, what is your OS? [Windows = 1 , Linux = 2] \n ->");
        int os = in.nextInt();

        Component componentFactory;

        switch (os) {

            case 1:
                componentFactory = new WindowsComponentImpl();
                break;
            case 2:
                componentFactory = new LinuxComponentImpl();
                break;
            default:
                throw new IllegalArgumentException("wrong input");
        }

        TextBox textBox = componentFactory.renderTextbox();
        textBox.render();
    }
}
