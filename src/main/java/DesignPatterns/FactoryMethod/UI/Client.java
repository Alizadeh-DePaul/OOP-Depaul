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

package DesignPatterns.FactoryMethod.UI;

import java.util.Scanner;

public class Client {

    public static void main(String[] arg){
        Scanner input = new Scanner(System.in);

        // Ask the user to choose a platform
        System.out.println("Choose a platform (1=Windows, 2=Web): ");
        int platform = input.nextInt();

        // Create a factory object based on the user's choice of platform
        Dialog dialog;
        switch (platform) {
            case 1:
                dialog = new WindowsDialog();
                break;
            case 2:
                dialog = new WebDialog();
                break;
            default:
                throw new IllegalArgumentException("Invalid platform choice.");
        }

        Button button = dialog.createButton();
        button.render();
    }
}
