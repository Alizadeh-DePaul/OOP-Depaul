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

package oopBasics2;

public class OuterClass { //this is Outer class
    static int staticInt=1;
    int nonStaticInt=2;

    class InnerClass { // this is Inner class
        void showInnerMethod() {
            System.out.println("Inside InnerClass.");
            System.out.println("The staticInt ="+staticInt );
            System.out.println("The nonStaticInt ="+nonStaticInt +"\n");
        }
    }

    // An outer class method that can invoke an inner class method
    void invokeInner() {
        InnerClass innerOb = new InnerClass();
        System.out.println("**Invoking an inner class method from an outer class method.**");
        //Calling the inner class method
        innerOb.showInnerMethod();
    }
}

class DemoNestedClass {
    public static void main(String[] args) {
        System.out.println("***Inner class demonstration.***\n");
        OuterClass outer = new OuterClass();// Ok
        //Calling the inner class method through an outer class method
        System.out.println("**Approach 1: Calling the inner class method through an outer class object.**");
        outer.invokeInner();
//         InnerClass inner=new InnerClass();//Error
        OuterClass.InnerClass inner = outer.new InnerClass();// Ok
        //Invoking the inner class method through an inner class object.
        System.out.println("Approach 2: Invoking the inner class method through an inner class object.");
        inner.showInnerMethod();
    }
}
