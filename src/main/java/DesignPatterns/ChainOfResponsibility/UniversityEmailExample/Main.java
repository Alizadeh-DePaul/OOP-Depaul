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

public class Main {

    public static void main(String[] args) {
        
        String emailText = "hi send bitcoins to 1234";

        UniversityEmailHandler academic = new AcademicEmailHandler();
        UniversityEmailHandler alumni = new AlumniEmailHandler();
        UniversityEmailHandler advising = new AdvisingEmailHandler();
        UniversityEmailHandler finance = new FinanceEmailHandler();
        UniversityEmailHandler hr = new HREmailHandler();
        UniversityEmailHandler admin = new AdminEmailHandler();

        // setup chain direction
        academic.setNextEmailHandler(alumni);
        alumni.setNextEmailHandler(advising);
        advising.setNextEmailHandler(finance);
        finance.setNextEmailHandler(hr);
        hr.setNextEmailHandler(admin);
        // we do not need to set the next email handler after admin
        // because it is the end of the chain of responsibility

        // this line will start the chain
        academic.processEmailHandler(emailText);

    }
    
}
