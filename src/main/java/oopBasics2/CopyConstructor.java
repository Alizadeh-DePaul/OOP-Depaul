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

//demonstration of copy constructor
public class CopyConstructor {
}

class Student
{
    int studentID;
    String name;
    //Instance Constructor
    public Student(int studentID, String name)
    {
        this.studentID = studentID;
        this.name = name;
    }
    //Copy Constructor
    public Student( Student student)
    {
        this.name = student.name;
        this.studentID = student.studentID;
    }
    public void displayDetails()
    {
        System.out.println(" Student name: " + name + ",Student ID: "+ studentID);
    }
}

class DemoCopyConstructor {
    public static void main(String[] args) {
        System.out.println("***User-defined copy constructor***\n");
        Student student1 = new Student(123456, "Vahid");
        System.out.println(" The Student1 details:");
        student1.displayDetails();

        System.out.println("\n Copying student1 to student2 >>>");
        //Invoking the user-defined copy constructor
        Student student2 = new Student (student1);
        System.out.println(" The Student2 details:");
        student2.displayDetails();
    }
}
