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

package oopPrinciples.abstraction;

//A simple abstraction example
public class AbstractionExample {
}
 abstract class AbstractClass {
    protected int myInt = 450;
    public abstract void showMe();
    public void completeMethod1() {
        System.out.println("I am from completeMethod1 in MyAbstractClass and I am complete.");
    }
    public void completeMethod2() {
        System.out.println("I'm the initial version of completeMethod2() in MyAbstractClass.I am complete.");
    }
}
class CompleteClass extends AbstractClass {
    @Override
    public void showMe() {
        System.out.println("I'm 'completed/implemented version of showMe() in CompleteClass.");
        System.out.println("The value of myInt is:" + myInt);
    }
    @Override
//  It wants to override completeMethod1() in MyAbstractClass
    public void completeMethod1() {
        System.out.println("I'm the 'overrided' version of completeMethod1() in CompleteClass.");
    }
}
class DemoSimpleAbstractClass {
    public static void main(String Args[]) {
        System.out.println("***Demo Abstract classes.***\n");
        CompleteClass completeObj = new CompleteClass();
        completeObj.showMe();
        completeObj.completeMethod1(); // This will show that completeMethod1 is redefined in CompleteClass.
        completeObj.completeMethod2(); // This will show the details of completeMethod2 defined in AbstractClass.

        System.out.println("\n**Invoking methods through parent class reference now.**");
        AbstractClass abstractRef = new CompleteClass();
        abstractRef.showMe();
        abstractRef.completeMethod1();
        abstractRef.completeMethod2();
    }
}