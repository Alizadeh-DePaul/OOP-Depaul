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

package DesignPatterns.ChainOfResponsibility.EmailForwardingExample;

public class ComplaintHandler implements Handler {

    private Handler chain;
    @Override

    public void setNextChain(Handler nextChain) {
        this.chain=nextChain;
    }
    @Override
    public void forwardMail(Mail mailObj) {
        /*
         * Checking a mail subject and forwarding to next Chain Handler.
         */
        if(mailObj.getSubject().equalsIgnoreCase(Handler.COMPLAINT_MAIL)){
            System.out.println("Forwarding Mail to Legal Department.");
        }else{
            this.chain.forwardMail(mailObj);
        }

    }

}