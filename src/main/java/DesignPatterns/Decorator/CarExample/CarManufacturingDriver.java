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
        Car basicCar = new BasicCar();

        Car sportsCar = new SportsCar(basicCar);
        sportsCar.assemble();
        System.out.println("\n*****");

        Car sportsLuxuryCar = new LuxuryCar(new SportsCar(basicCar));
        sportsLuxuryCar.assemble();

        System.out.println("\n ORDER *****");

        Car luxurySportsCar = new SportsCar(new LuxuryCar(basicCar));
        luxurySportsCar.assemble();

        System.out.println("\n*****");

        Car basic = new BasicCar();
        Car sportDec = new SportsCar(basic);
        Car luxuryDec = new LuxuryCar(sportDec);
        luxuryDec.assemble();
    }

}
