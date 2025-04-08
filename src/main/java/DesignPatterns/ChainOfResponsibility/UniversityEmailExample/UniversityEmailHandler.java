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

public interface UniversityEmailHandler {

    public void setNextEmailHandler(UniversityEmailHandler emailHandler);

    public void processEmailHandler(String emailText);

}
