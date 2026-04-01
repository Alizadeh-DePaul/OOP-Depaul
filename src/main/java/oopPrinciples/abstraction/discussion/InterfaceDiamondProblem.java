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

public class InterfaceDiamondProblem {
}
interface DefaultInterfaceA {
    void show();
    default void myDefaultMethod() {
        System.out.println("Default implementation for DefaultInterfaceA is called.");
    }
}
interface DefaultInterfaceB {
    void show();
    default void myDefaultMethod() {
        System.out.println("Default implementation for DefaultInterfaceB is called.");
    }
    default void myDefaultMethodB() {
        System.out.println("Default implementation for DefaultInterfaceB is called.");
    }
}
class MyNewClass implements DefaultInterfaceA, DefaultInterfaceB {
    public void show( ) {
        System.out.println("MyNewClass is implementing the Interface method-show().");
    }

    @Override
    public void myDefaultMethod() {
        System.out.println("MyNewClass must implement this method.");
        // How to call default method in our interfaces:
        // Calling default method of DefaultInterface11A and DefaultInterface11B
        DefaultInterfaceA.super.myDefaultMethod();
        DefaultInterfaceB.super.myDefaultMethod();
    }
}
class DemoAvoidDiamondProblem {
    public static void main(String[] args) {
        System.out.println("***Demo Avoiding diamond problem***\n");
        System.out.println("Using DefaultInterfaceA reference:");
        DefaultInterfaceA interfaceObA = new MyNewClass();
        interfaceObA.show();
        interfaceObA.myDefaultMethod();
        System.out.println("----------------------");
        System.out.println("Using DefaultInterfaceB reference:");
        DefaultInterfaceB interfaceObB = new MyNewClass();
        interfaceObB.show();
        interfaceObB.myDefaultMethod();
    }
}