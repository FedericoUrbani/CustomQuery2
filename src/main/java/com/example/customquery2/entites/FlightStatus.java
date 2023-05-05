package com.example.customquery2.entites;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

import java.util.Random;


public enum FlightStatus {
    ON_TIME,
    DELAYED,
    CANCELLED;

    private static final Random RNGState = new Random();

    public static FlightStatus randomStatus()  {
        FlightStatus[] flightValues = values();
        return flightValues[RNGState.nextInt(flightValues.length)];
    }



}
