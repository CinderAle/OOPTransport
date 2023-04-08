package com.example.ooptransport;

import static javafx.collections.FXCollections.observableArrayList;

public class AirTransport extends Transport {
    protected int maxHeight, maxDistance;
    protected Engine[] engines;

    public AirTransport(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int maxHeight, int maxDistance, Engine[] engines) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass);
        this.maxHeight = maxHeight;
        this.maxDistance = maxDistance;
        this.engines = engines;
    }

    public AirTransport() {
        super();
        this.maxHeight = 0;
        this.maxDistance = 0;
        this.engines = null;
    }

    @Override
    public void generateFields(Controller controller) {
        controller.hideSecondLevelPanes();
        controller.hideThirdLevelPanes();
        controller.airTransportPane.setVisible(true);
        controller.setTransportComboBoxItems(controller.airTransportTypeComboBox, observableArrayList(
                new TransportFactory("Airplane", new Airplane()),
                new TransportFactory("Helicopter", new Helicopter())
        ));
    }
}
