package com.example.ooptransport;
public class Helicopter extends AirTransport {
    private int blades;
    private String rotorsType;

    public Helicopter(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int maxHeight, int maxDistance, Engine[] engines, int blades, String rotorsType) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass, maxHeight, maxDistance, engines);
        this.blades = blades;
        this.rotorsType = rotorsType;
    }

    public Helicopter(){
        super();
        this.blades = 0;
        this.rotorsType = null;
    }
    @Override
    public void generateFields(Controller controller) {
        controller.hideThirdLevelPanes();
        controller.helicopterPane.setVisible(true);
    }
}
