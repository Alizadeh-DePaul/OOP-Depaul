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

package DesignPatterns.ChainOfResponsibility.UniversityEmailExample;

public class AdminEmailHandler extends MainEmailHandler {

    protected String[] keyWords() {
        // Here it does not matter what the keywords are
        return new String[0];
    }

    protected void processEmailFinal(String emailText) {
        System.out.println("The Admin Team processed the email.");
    }

}