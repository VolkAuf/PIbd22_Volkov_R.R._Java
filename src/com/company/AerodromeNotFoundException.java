package com.company;

public class AerodromeNotFoundException extends Exception {
    public AerodromeNotFoundException(int i) {
        super("Not found airplane number " + i);
    }
}
