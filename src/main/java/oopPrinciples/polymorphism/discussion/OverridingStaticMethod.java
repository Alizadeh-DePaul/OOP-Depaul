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

//Runtime polymorphism doesn't happen for static methods.
public class OverridingStaticMethod {
}

class ParentClass {
    public static void printStatic(){
        System.out.println("static from ParentClass");
    }
    public void printDynamic(){
        System.out.println("dynamic from ParentClass");
    }
}
class ChildClass extends ParentClass {
    public static void printStatic(){
        System.out.println("static from ChildClass");
    }
    public void printDynamic(){
        System.out.println("dynamic from ChildClass");
    }
}
class DemoOverridingStaticMethod{
    public static void main (String args[]) {
        ParentClass parentClass = new ChildClass();
        parentClass.printStatic();
        parentClass.printDynamic();
        ChildClass childClass = new ChildClass();
        childClass.printStatic();
        childClass.printDynamic();
    }
}