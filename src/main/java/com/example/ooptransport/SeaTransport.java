package com.example.ooptransport;
public class SeaTransport extends Transport {
    protected int volumeDisplacement, normalDisplacement;
    protected Engine engine;

    public SeaTransport(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int volumeDisplacement, int normalDisplacement, Engine engine) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass);
        this.volumeDisplacement = volumeDisplacement;
        this.normalDisplacement = normalDisplacement;
        this.engine = engine;
    }

    public SeaTransport() {
        super();
        this.volumeDisplacement = 0;
        this.normalDisplacement = 0;
        this.engine = null;
    }

    @Override
    public void generateFields(Controller controller) {
        controller.hideSecondLevelPanes();
        controller.hideThirdLevelPanes();
        controller.seaTransportPane.setVisible(true);
    }
}
