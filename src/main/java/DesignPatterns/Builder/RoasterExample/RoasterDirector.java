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

public class RoasterDirector {
    Builder currentBuilder;

    // roaster building steps
    public void buildRoaster(Builder builder) {

        currentBuilder = builder;
        currentBuilder.buildCoolingTray();
        currentBuilder.buildExhaustSystem();
        currentBuilder.buildGasBurner();
        currentBuilder.buildInnerDrum();
        currentBuilder.buildMainBody();
        currentBuilder.buildMotor();
        currentBuilder.buildPlatform();
        currentBuilder.buildThermocouples();
    }
}
