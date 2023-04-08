package com.example.ooptransport;
public class Trailer extends Transport {
    private String trailerType;
    private int height, width;

    public Trailer(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, String trailerType, int height, int width) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass);
        this.trailerType = trailerType;
        this.height = height;
        this.width = width;
    }

    @Override
    public void generateFields(Controller controller) {

    }
}
