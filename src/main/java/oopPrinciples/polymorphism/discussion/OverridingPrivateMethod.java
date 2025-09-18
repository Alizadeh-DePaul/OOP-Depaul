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

package oopPrinciples.polymorphism.discussion;

public class OverridingPrivateMethod {
}
//It is not possible to override a private methods.
class Parent {
    private void printSomething() {
        System.out.println("Parent Method");
    }
}
class Child extends Parent {
    private void PrintSomething() {
        System.out.println("Child Method");
    }
}
class DemoOverridingPrivateMethod{
    public static void main(String[] args) {
        Parent obj = new Child();
        //Error: PrintSomething() has private access in parent (not the child)
//        obj.printSomething();
    }
}