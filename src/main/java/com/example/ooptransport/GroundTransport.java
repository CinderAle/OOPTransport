package com.example.ooptransport;

import static javafx.collections.FXCollections.observableArrayList;

public class GroundTransport extends Transport {
    public enum wheelDriveTypes {FWD, RWD, AWD};
    public enum gearboxTypes {Automatic, Manual, Selective, Robot};
    protected int wheels, highwayConsumption, cityConsumption, gears;
    protected String gearboxManufacturer, soundSystem;
    protected wheelDriveTypes wheelDrive;
    protected gearboxTypes gearboxType;
    protected Engine engine;
    protected boolean leftSided;

    public GroundTransport(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int wheels, int highwayConsumption, int cityConsumption, int gears, wheelDriveTypes wheelDrive, gearboxTypes gearboxType, String gearboxManufacturer, String soundSystem, Engine engine, boolean leftSided) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass);
        this.wheels = wheels;
        this.highwayConsumption = highwayConsumption;
        this.cityConsumption = cityConsumption;
        this.gears = gears;
        this.wheelDrive = wheelDrive;
        this.gearboxType = gearboxType;
        this.gearboxManufacturer = gearboxManufacturer;
        this.soundSystem = soundSystem;
        this.engine = engine;
        this.leftSided = leftSided;
    }

    public GroundTransport() {
        super();
        this.wheels = 0;
        this.highwayConsumption = 0;
        this.cityConsumption = 0;
        this.gears = 0;
        this.wheelDrive = null;
        this.gearboxType = null;
        this.gearboxManufacturer = null;
        this.soundSystem = null;
        this.engine = null;
        this.leftSided = true;
    }

    public static boolean checkFields(TrailerWindowController controller){
        boolean isCorrect = Transport.checkFields(controller) &&
                            controller.objectEngines != null &&
                            checkForEmpty(controller.groundSoundTextField.getText()) &&
                            checkForEmpty(controller.groundGearboxManTextField.getText()) &&
                            controller.groundWheelDriveComboBox.getValue() != null &&
                            controller.groundGearboxComboBox.getValue() != null;
        if(isCorrect){
            try{
                Integer.parseInt(controller.groundWheelsTextField.getText());
                Double.parseDouble(controller.groundHighwayTextField.getText());
                Double.parseDouble(controller.groundCityTextField.getText());
            }
            catch(Exception e){
                return false;
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public void generateFields(Controller controller) {
        controller.hideSecondLevelPanes();
        controller.hideThirdLevelPanes();
        controller.groundTransportPane.setVisible(true);
        controller.setTransportComboBoxItems(controller.groundTypeComboBox, observableArrayList(
                new TransportFactory("Passenger car", new PassengerCar()),
                new TransportFactory("Truck", new Truck())
        ));
        controller.groundWheelDriveComboBox.getItems().setAll(wheelDriveTypes.values());
        controller.groundGearboxComboBox.getItems().setAll(gearboxTypes.values());
    }
}
