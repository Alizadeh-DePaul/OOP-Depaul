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

public class AdvisingEmailHandler extends MainEmailHandler {

    protected String[] keyWords() {
        // setup keywords for the receiver team
        return new String[] {"advising", "schedule", "course"};
    }

    protected void processEmailFinal(String emailText) {
        System.out.println("The Advising Team processed the email.");
    }

}