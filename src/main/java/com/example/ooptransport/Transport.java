package com.example.ooptransport;
public class Transport {
    protected String brand, model, color, interior, specifications;
    protected int seats, manufactureYear, mileage, mass;

    public Transport(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.interior = interior;
        this.specifications = specifications;
        this.seats = seats;
        this.manufactureYear = manufactureYear;
        this.mileage = mileage;
        this.mass = mass;
    }

    public Transport() {
        this.brand = null;
        this.model = null;
        this.color = null;
        this.interior = null;
        this.specifications = null;
        this.seats = 0;
        this.manufactureYear = 0;
        this.mileage = 0;
        this.mass = 0;
    }

    public void generateFields(Controller controller) {

    }
}
