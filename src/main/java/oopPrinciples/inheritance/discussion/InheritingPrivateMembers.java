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

//Demonstration of inheriting private members
class InheritingPrivateMembers{}

class A {
    private int a;
}
class B extends A {
}
class Demo {
    public static void main(String[] args) {
        System.out.println("Demo Private members are also inherited***");
        B obB = new B();
        A obA = new A();
// This is a proof that a is also inherited. See the error message.
//        System.out.println(obB.a);// Error:(16, 31) java: a has private access in oopPrinciples.inheritance.discussion.A
//        System.out.println(obB.b);// Error:(17, 31) java: cannot find symbol
//is not a field
//        System.out.println(obA.a);// Error:(19, 31) java: a has private access in oopPrinciples.inheritance.discussion.A
//        System.out.println(obA.b);// Error:(20, 31) java: cannot find symbol
    }
}
