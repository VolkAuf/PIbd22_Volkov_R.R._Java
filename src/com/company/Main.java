package com.company;

public class Main {
    public static void main(String[] args) {
        // write your code here
        FormAirplane formAir = new FormAirplane();
        DrawWindow drawWindow = new DrawWindow();
        formAir.addDrawWindow(drawWindow);
    }
}
