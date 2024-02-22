package org.example.core;

public class Validation {
    public static boolean isNegative(double number) {
        return number < 0;
    }

    public static boolean min(double number, double minimumValue) {
        return number > minimumValue;
    }
}