package com.example.ooptransport;
public class Airplane extends AirTransport {
    private String airplaneClass;
    private int landings;

    public Airplane(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int maxHeight, int maxDistance, Engine[] engines, String airplaneClass, int landings) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass, maxHeight, maxDistance, engines);
        this.airplaneClass = airplaneClass;
        this.landings = landings;
    }

    public Airplane() {
        super();
        this.airplaneClass = null;
        this.landings = 0;
    }
    @Override
    public void generateFields(Controller controller) {
        controller.hideThirdLevelPanes();
        controller.airplanePane.setVisible(true);
    }
}
