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

package oopBasics1;

//Constructor overloading example
public class ClassEx3 {
    int i;

    ClassEx3() {
//        this.i = 5; // instance variable
//
        this(5);
        //In Java, we could use this (5); instead of this.i=5; but other
        //languages may not support this kind of construct
    }
    
    public ClassEx3(int i) {

        this.i = i;
    }

//    ClassEx3(int myInteger)// myInteger is a local variable
//    {
//        i = myInteger;
//    }



    public static void main(String[] args) {
        System.out.println("*** A simple class with 2 different constructors ***");
        System.out.println("*** This is also an example of constructor overloading ***");
        ClassEx3 obA = new ClassEx3();
        ClassEx3 obB = new ClassEx3(75);
        System.out.println("obA.i =" + obA.i);
        System.out.println("obB.i =" + obB.i);
    }
}

