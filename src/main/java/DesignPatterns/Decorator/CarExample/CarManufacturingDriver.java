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

package DesignPatterns.Decorator.CarExample;

public class CarManufacturingDriver {

    public static void main(String[] args) {
        Car sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();
        System.out.println("\n*****");

        Car sportsLuxuryCar = new LuxuryCar(new SportsCar(new BasicCar()));
        sportsLuxuryCar.assemble();

        System.out.println("\n*****");

        Car basic = new BasicCar();
        Car sportDec = new SportsCar(basic);
        Car luxuryDec = new LuxuryCar(sportDec);
        luxuryDec.assemble();
    }

}
