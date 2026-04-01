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

//Demo of using this() and super() and constructor chaining
public class ConstructorChainingExample {
}

class ParentClsChain {
    int i;
    ParentClsChain() {
        System.out.println("Invoking parameterless constructor of Parent class.");
    }
}
class ChildClsChain extends ParentClsChain {
    int b;
    ChildClsChain() {
//      both this() and super() cannot be used together
        super();
//        this(450);
        System.out.println("Invoking parameterless constructor of Child.");
    }
    ChildClsChain(int b) {
        this.b = b;
        System.out.println("Inside Child constructor with one parameter where b= " + b);
    }
}
class DemoConstructorChaining {
    public static void main(String[] args) {
        System.out.println("***Analysis of this() and super() keyword.***\n");
        ChildClsChain sampleObj = new ChildClsChain();
    }
}