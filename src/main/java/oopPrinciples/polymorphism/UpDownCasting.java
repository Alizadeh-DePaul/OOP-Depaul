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

//Example of Upcasting and Downcasting
public class UpDownCasting {
}
class Vehicle {
    public void showMe() {
        System.out.println("Inside Vehicle.showMe()");
    }
}
class Bus extends Vehicle {
    public void showMe() {
        System.out.println("Inside Bus.showMe()");
    }
    public void specificMethod() {
        System.out.println("Inside Bus.specificMethod()");
    }
}
class Train extends Vehicle {
    public void showMe() {
        System.out.println("Inside Train.showMe()");
    }
    public void specificMethod() {
        System.out.println("Inside Train.specificMethod()");
    }
}
class DemoUpDownCasting {
    public static void main(String[] args) {
        System.out.println("***Demo Upcasting and Downcasting.***\n");
//      ####Upcasting
        Vehicle obVehicle=new Train();//ok
        obVehicle.showMe();//Output: Inside Train.showMe()
//        obVehicle.specificMethod();//error //Since the apparent type in the code is a Vehicle,
//      but not a Train. Need downcasting Line43 and 44

//      Creating two subtype(one Bus and one Train) object
        Bus obBus=new Bus();
        Train obTrain=new Train();


//      ####Downcasting :Casting to a subtype
       obBus=(Bus)obVehicle;       //Run-time error:Train cannot be cast to Bus //If you uncomment this line, you will not receive any compilation errors, but you will
//      encounter a runtime exception

        obTrain=(Train)obVehicle;   //Ok, this time it is ok.
        obTrain.specificMethod();   //also ok //Output: Inside Train.specificMethod()
    }
}