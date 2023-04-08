package com.example.ooptransport;
public class Engine {
    private int cylinders, horsepower, torque, volume;
    private String manufacturer;

    public Engine(int cylinders, int horsepower, int torque, int volume, String manufacturer) {
        this.cylinders = cylinders;
        this.horsepower = horsepower;
        this.torque = torque;
        this.volume = volume;
        this.manufacturer = manufacturer;
    }
}
