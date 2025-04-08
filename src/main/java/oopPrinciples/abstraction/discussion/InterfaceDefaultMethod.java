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

package oopPrinciples.abstraction.discussion;

public class InterfaceDefaultMethod {
}
interface InterfaceWithDefault {
    // Traditional interface method without a body.
    void traditionalInterfaceMethod();

    // A default method in the interface.
    default void defaultMethod() {
        System.out.println("Default implementation in the interface.");
    }
}
class MyClassWithDefault implements InterfaceWithDefault {
    @Override
    public void traditionalInterfaceMethod() {
        System.out.println("MyClassWithDefault is implementing the interface method");
    }

    public void sampleMethod(){
        System.out.println("test");
    }

////    Overriding the default method in an interface
    @Override
    public void defaultMethod() {
    System.out.println("MyClassWithDefault is overriding the default interface method.");
    }
}
class DemoInterfaceDefaultMethod {
    public static void main(String[] args) {
        System.out.println("***Demo using default methods in an interface***\n");
        InterfaceWithDefault interfaceOb = new MyClassWithDefault();
        interfaceOb.traditionalInterfaceMethod();
        interfaceOb.defaultMethod();
    }
}