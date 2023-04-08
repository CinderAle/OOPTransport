package com.example.ooptransport;
public class PassengerCar extends GroundTransport {
    public enum bodyTypes {Sedan, Coupe, Touring, Hatchback, Crossover, SUV};
    private bodyTypes bodyType;
    private String assembly, equipment;
    private int rimsRadius;
    private Trailer trailer;

    public PassengerCar(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int wheels, int highwayConsumption, int cityConsumption, int gears, wheelDriveTypes wheelDrive, gearboxTypes gearboxType, String gearboxManufacturer, String soundSystem, Engine engine, boolean leftSided, bodyTypes bodyType, String assembly, String equipment, int rimsRadius, Trailer trailer) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass, wheels, highwayConsumption, cityConsumption, gears, wheelDrive, gearboxType, gearboxManufacturer, soundSystem, engine, leftSided);
        this.bodyType = bodyType;
        this.assembly = assembly;
        this.equipment = equipment;
        this.rimsRadius = rimsRadius;
        this.trailer = trailer;
    }

    public PassengerCar() {
        super();
        this.bodyType = null;
        this.assembly = null;
        this.equipment = null;
        this.rimsRadius = 0;
        this.trailer = null;
    }

    @Override
    public void generateFields(Controller controller) {
        controller.hideThirdLevelPanes();
        controller.passengerCarPane.setVisible(true);
        controller.passengerBodyTypeComboBox.getItems().setAll(bodyTypes.values());
    }
}
