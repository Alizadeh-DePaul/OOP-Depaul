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

import oopPrinciples.inheritance.Employee;

public class ClassEx4 {

    // The following method supports variable-length arguments

    public int sum(int... vararg) {
        System.out.println("You have passed " + vararg.length + "arguments now.");

//        formatf() and printf() are two examples of vararg methods in java library
//        System.out.print(String.format("%s, you have passed %d arguments now.", "Dear reader",vararg.length));
        int total = 0;
        for (int i : vararg) {
            total = total + i;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("***Example 4. Methods with variablelength argument ***\n");
        ClassEx4 ob = new ClassEx4();
        int resultOfSummation = ob.sum(57, 63);
        System.out.println("Sum of 57 and 63 is : " + resultOfSummation);
        resultOfSummation = ob.sum(57, 63, 50);
        System.out.println("Sum of 57, 63 and 50 is : " + resultOfSummation);
    }
}
