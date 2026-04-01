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

public abstract class MainEmailHandler implements UniversityEmailHandler {

    private UniversityEmailHandler theNextHandlerInTheChain;

    protected abstract String[] keyWords();
    protected abstract void processEmailFinal(String emailText);

    public void setNextEmailHandler(UniversityEmailHandler emailHandler) {
        theNextHandlerInTheChain = emailHandler;
    }

    public void processEmailHandler(String emailText) {

        // starting value
        boolean keyWordFound = false;

        // check for a matching keyword in emailText
        if (keyWords().length == 0) {
            keyWordFound = true;
        } else {
            for (String oneKeyWord : keyWords()) {
                if (emailText.indexOf(oneKeyWord) >= 0) {
                    keyWordFound = true;  // change value if match is found
                    break; // leave loop if match is found
                }
            }
        }

        // check to see if email can be processed by the current
        // email handler based on keyword match
        if (keyWordFound) {
            processEmailFinal(emailText);
        } else {
            // pass along the chain if the email is not processed
            // by the current email handler
            theNextHandlerInTheChain.processEmailHandler(emailText);
        }
    }
}
