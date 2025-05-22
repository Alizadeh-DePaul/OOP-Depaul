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

package DesignPatterns.FactoryMethod.testUIFactoryMethod;

public class cClient {

    public static void main(String[] args){
        DialogWindow dialogWindow;

        String os = "Windows";

        if (os == "Windows") {
            dialogWindow = new WindowsDialog();
            dialogWindow.createButton();
        } else {
            dialogWindow = new WebDialog();
            dialogWindow.createButton();

        }
    }
}
