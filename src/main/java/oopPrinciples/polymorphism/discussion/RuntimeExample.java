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

import java.util.Random;

//Demonstration of why a code will be resolved at runtime
public class RuntimeExample {
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
}
class Taxi extends Vehicle {
    public void showMe() {
        System.out.println("Inside Taxi.showMe()");
    }
}
class DemoRunTimeDiscussion {
    public static void main(String[] args) {
        System.out.println("***Demo analysing runtime polymorphism ***\n");
        Vehicle obVehicle;
        int count = 0;
        Random random = new Random();
//      Considering 5 choices
        while (count < 5) {
            int tick = random.nextInt(10);//0 to 9
            if (tick % 2 == 0) {
                obVehicle = new Bus();
            } else {
                obVehicle = new Taxi();
            }
            obVehicle.showMe();// Output will be determined at runtime
            count++;
        }
    }
}
