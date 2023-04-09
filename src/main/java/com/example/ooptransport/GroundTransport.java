package com.example.ooptransport;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import static javafx.collections.FXCollections.observableArrayList;

public class GroundTransport extends Transport {
    public enum wheelDriveTypes {FWD, RWD, AWD};
    public enum gearboxTypes {Automatic, Manual, Selective, Robot};
    protected int wheels, gears;
    protected double highwayConsumption, cityConsumption;
    protected String gearboxManufacturer, soundSystem;
    protected wheelDriveTypes wheelDrive;
    protected gearboxTypes gearboxType;
    protected Engine engine;
    protected boolean leftSided;

    public GroundTransport(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int wheels, double highwayConsumption, double cityConsumption, int gears, wheelDriveTypes wheelDrive, gearboxTypes gearboxType, String gearboxManufacturer, String soundSystem, Engine engine, boolean leftSided) {
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

    public GroundTransport(Controller controller){
        super(controller);
        this.wheels = Integer.parseInt(controller.groundWheelsTextField.getText());
        this.highwayConsumption = Double.parseDouble(controller.groundHighwayTextField.getText());
        this.cityConsumption = Double.parseDouble(controller.groundCityTextField.getText());
        this.gears = Integer.parseInt(controller.groundGearsTextField.getText());
        this.wheelDrive = controller.groundWheelDriveComboBox.getValue();
        this.gearboxType = controller.groundGearboxComboBox.getValue();
        this.gearboxManufacturer = controller.groundGearboxManTextField.getText();
        this.soundSystem = controller.groundSoundTextField.getText();
        this.engine = controller.objectEngines[0];
        this.leftSided = controller.groundLeftSidedCheckbox.isSelected();
    }

    public AnchorPane initAnchor(){
        Transport transport = this;
        AnchorPane anchor = transport.initAnchor();
        Label wheels = new Label("Wheels: " + this.wheels);
        Label highway = new Label("Highway consumption: " + this.highwayConsumption);
        Label city = new Label("City consumption: " + this.cityConsumption);
        Label gears = new Label("Gears: " + this.gears);
        Label wheelDrive = new Label("Wheel drive: " + this.wheelDrive);
        Label gearboxType = new Label("Gearbox type: " + this.gearboxType);
        Label gearManufacturer = new Label("Gearbox manufacturer: " + this.gearboxManufacturer);
        Label soundSystem = new Label("Sound system: " + this.soundSystem);
        Label leftSided = new Label("Left sided: " + (this.leftSided ? "yes" : "no"));
        Label engineLabel = new Label("Engine:");
        anchor.getChildren().addAll(wheels, highway, city, gears, wheelDrive, gearboxType, gearManufacturer, soundSystem, leftSided, engineLabel);
        anchor.getChildren().add(this.engine.initAnchor());
        return anchor;
    }

    public void setFields(Controller controller){
        Transport transport = this;
        transport.setFields(controller);
        controller.vehicleTypeComboBox.getSelectionModel().select(new TransportFactory("Ground transport", new GroundTransport()));
        controller.groundWheelsTextField.setText(Integer.toString(this.wheels));
        controller.groundHighwayTextField.setText(Double.toString(this.highwayConsumption));
        controller.groundCityTextField.setText(Double.toString(this.cityConsumption));
        controller.groundGearsTextField.setText(Integer.toString(this.gears));
        controller.groundWheelDriveComboBox.getSelectionModel().select(this.wheelDrive);
        controller.groundGearboxComboBox.getSelectionModel().select(this.gearboxType);
        controller.groundGearboxManTextField.setText(this.gearboxManufacturer);
        controller.groundSoundTextField.setText(this.soundSystem);
        controller.groundLeftSidedCheckbox.setSelected(this.leftSided);
        controller.objectEngines = new Engine[1];
        controller.objectEngines[0] = this.engine;
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
