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

package oopPrinciples.polymorphism;

//Method overriding example
public class RuntimePolymorphism {
}
class ParentClass {
    // Overridden method
    public void showMe() {
        System.out.println("Inside ParentClass.showMe()");
    }
    public void doNotChangeMe() {
        System.out.println("Inside ParentClass.doNotChangeMe().");
    }
}
class ChildClass extends ParentClass {
    // Overriding method
    public void showMe() {
        System.out.println("Inside ChildClass.showMe().");
    }
}
class DemoRuntimePolymorphism {
    public static void main(String[] args) {
        System.out.println("*** Method overriding example.***");
        ChildClass childOb = new ChildClass();
        childOb.doNotChangeMe();
        childOb.showMe();//Will display the overridden method
    }
}