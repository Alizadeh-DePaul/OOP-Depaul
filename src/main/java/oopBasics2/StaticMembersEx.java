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

package oopBasics2;

public class StaticMembersEx {
    //static variables
    static double length=25.5, breadth=10.0;

    int i;
    //static method
    public static double area() {
        return length * breadth;
    }

    public static void main(String[] args) {
        StaticMembersEx obj = new StaticMembersEx();
        System.out.println("***Static members example: Exploring class variables and class methods.***\n");
        System.out.println("Length of the Rectangle is :" + StaticMembersEx.length + " unit");
        System.out.println("Breadth of the Rectangle is :" + StaticMembersEx.breadth + " unit");
        System.out.println("Area of Rectangle is " + StaticMembersEx.area() + " sq.unit");
    }
}
