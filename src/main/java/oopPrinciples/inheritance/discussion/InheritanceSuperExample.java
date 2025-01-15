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

//A full demo of using SUPER keyword
public class InheritanceSuperExample {}

class ParentCls {
    private int a;
    private int b;
    ParentCls(int a, int b) {
        System.out.println("I am in parent constructor.");
        System.out.println("Before setting,a="+ this.a);
        System.out.println("Before setting, b="+ this.b);
        System.out.println("Setting the values for instance variables a and b.");
        this.a = a;
        this.b = b;
        System.out.println("Now a="+ this.a);
        System.out.println("Now b="+ this.b);
    }
    void parentClsMethod()
    {
        System.out.println("I am a parent method.");
    }
}
class ChildCls extends ParentCls {
    private int c;
    ChildCls(int a, int b, int c) {
//        System.out.println("Before setting,c="+ this.c);
//      Error:Constructor call must be the first statement in a constructor
        super(a, b);
        System.out.println("I am in child constructor.");
        System.out.println("Before setting,c="+ this.c);
        this.c = c;
        System.out.println("Now c="+ this.c);
    }
    void childClsMethod() {
        System.out.println("I am a child method.");
        System.out.println("I am calling the parent method.");
        super.parentClsMethod();
    }
}

class DemoInheritanceSuperExample {
    public static void main(String[] args) {
        System.out.println("***DEMO: The uses of the 'super' keyword***");
        ChildCls sampleObj = new ChildCls(1,2,3);
        sampleObj.childClsMethod();
    }
}
