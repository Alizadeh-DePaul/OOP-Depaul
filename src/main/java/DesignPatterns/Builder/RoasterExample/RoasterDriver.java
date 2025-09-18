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

package DesignPatterns.Builder.RoasterExample;

public class RoasterDriver {

    public static void main(String[] args) {

        RoasterDirector roasterDirector = new RoasterDirector();

        Builder personalRoasterBuilder = new PersonalRoaster();
        Builder commercialRoasterBuilder = new CommercialRoaster();

        // Build a Personal Roaster
        roasterDirector.buildRoaster(personalRoasterBuilder);
        Roaster unit1 = personalRoasterBuilder.getRoaster();
        unit1.display();

        // Build a Commercial Roaster
        roasterDirector.buildRoaster(commercialRoasterBuilder);
        Roaster unit2 = commercialRoasterBuilder.getRoaster();
        unit2.display();
    }
}
