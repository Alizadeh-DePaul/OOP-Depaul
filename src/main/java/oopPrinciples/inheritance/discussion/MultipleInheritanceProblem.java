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

package oopPrinciples.inheritance.discussion;

public class MultipleInheritanceProblem {}

class Parent {
    public void show() {
        System.out.println("I am in Parent");
    }
}
class Child1 extends Parent {
    public void show() {
        System.out.println("I am in Child1");
    }
}
class Child2 extends Parent {
    public void show() {
        System.out.println("I am in Child2");
    }
}
//class GrandChild extends Child1,Child2// Error: Class can't extend multiple classes
//{
//    public void show() {
//        System.out.println("I am in Grandchild");
//    }
//}