package com.company;

public class AerodromeAlreadyHaveException extends Exception {
    public AerodromeAlreadyHaveException() {
        super("There is already such an airplane at the aerodrome!");
    }
}
