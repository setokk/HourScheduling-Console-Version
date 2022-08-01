package com.setokk.calculations;

public class Calculations
{
    private static int numberOfPeople = 0;

    public static int getNumberOfPeople() {
        return numberOfPeople;
    }

    public static void setNumberOfPeople(int numberOfPeople) {
        Calculations.numberOfPeople = numberOfPeople;
    }
}
