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

public class AcademicEmailHandler extends MainEmailHandler {

    protected String[] keyWords() {
        // setup keywords for the receiver team
        return new String[] {"academic"};
    }

    protected void processEmailFinal(String emailText) {
        System.out.println("The Academic Team processed the email.");
    }
}
