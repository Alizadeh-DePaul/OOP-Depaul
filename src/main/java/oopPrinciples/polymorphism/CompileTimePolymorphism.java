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

//Method overloading example
public class CompileTimePolymorphism {
}

class Addition {
    int sum(int x, int y) {
        return x + y;
    }
    double sum(double x, double y) {
        return x + y;
    }
    String sum(String s1, String s2) {
        return s1.concat(s2);
    }
}
class DemoCompileTimePolymorphism {
    public static void main(String[] args) {
        System.out.println("***Method overloading example***");
        Addition additionObj = new Addition();
        int sumOfIntergers = additionObj.sum(400, 50);
        System.out.println("400 + 50 is :" + sumOfIntergers);
        double sumOfDoubles = additionObj.sum(399.5, 50.5);
        System.out.println("399.5 + 50.5 is :" + sumOfDoubles);
        String sumOfStrings = additionObj.sum("Vahid", "Alizadeh");
        System.out.println("'Vahid'+ 'Alizadeh' is :" + sumOfStrings);
    }
}