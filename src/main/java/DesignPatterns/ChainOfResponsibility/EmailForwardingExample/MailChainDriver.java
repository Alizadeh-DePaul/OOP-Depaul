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

public class MailChainDriver {

    private Handler chain;

    /*
     * Forming a chain
     */
    public MailChainDriver(){
        this.chain=new SpamHandler();

        Handler fanHandlerObj=new FanHandler();
        Handler complaingtHandlerObj=new ComplaintHandler();
        Handler newLocngtHandlerObj=new NewLocHandler();

        chain.setNextChain(fanHandlerObj);
        fanHandlerObj.setNextChain(complaingtHandlerObj);
        complaingtHandlerObj.setNextChain(newLocngtHandlerObj);

    }

    public static void main(String s[]) {

        MailChainDriver mailChainObj =new MailChainDriver();

        /*
         * calling a chain with Different Subject mail.
         */
        mailChainObj.chain.forwardMail(new Mail("SPAM_MAIL"));

        mailChainObj.chain.forwardMail(new Mail("FAN_MAIL"));

        mailChainObj.chain.forwardMail(new Mail("COMPLAINT_MAIL"));

        mailChainObj.chain.forwardMail(new Mail("NEW_LOC_MAIL"));

    }
}
