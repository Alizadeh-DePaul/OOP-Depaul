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

//Demo of a simple interface
public class InterfaceExample {
}
interface MyInterface {
    void implementMe();
}
class MyClass implements MyInterface {
    @Override
    public void implementMe() {
        System.out.println("MyClass is implementing the interface method implementMe().");
    }
}

class DemoInterface {
    public static void main(String[] args) {
        System.out.println("***Demo Simple Interfaces.***\n");
        MyClass myClassOb = new MyClass();
        myClassOb.implementMe();
    }
}