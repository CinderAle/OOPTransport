package com.example.ooptransport;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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

    public PassengerCar(Controller controller){
        super(controller);
        this.bodyType = controller.passengerBodyTypeComboBox.getValue();
        this.trailer = controller.objectTrailer;
        this.assembly = controller.passengerAssemblyTextField.getText();
        this.equipment = controller.passengerEquipmentTextField.getText();
        this.rimsRadius = Integer.parseInt(controller.passengerRimsRadiusTextField.getText());
    }

    public AnchorPane initAnchor(){
        AnchorPane anchor = super.initAnchor();
        Label body = addLabelWithPos("Body type: " + this.bodyType);
        Label assembly = addLabelWithPos("Assembly: " + this.assembly);
        Label equipment = addLabelWithPos("Equipment: " + this.equipment);
        Label rims = addLabelWithPos("Rims radius: " + this.rimsRadius);
        anchor.getChildren().addAll(body, assembly, equipment, rims, addLabelWithPos("Trailer:"));
        this.trailer.setLabelsYStart(labelsYStart);
        anchor.getChildren().add(this.trailer != null ? this.trailer.initAnchor() : addLabelWithPos("None"));
        return anchor;
    }

    public PassengerCar fetchDataFromFields(Controller controller) {
        return new PassengerCar(controller);
    }

    public void setFields(Controller controller) {
        super.setFields(controller);
        controller.groundTypeComboBox.getSelectionModel().select(new TransportFactory("Car", new PassengerCar()));
        controller.passengerBodyTypeComboBox.getSelectionModel().select(this.bodyType);
        controller.passengerAssemblyTextField.setText(this.assembly);
        controller.passengerEquipmentTextField.setText(this.equipment);
        controller.passengerRimsRadiusTextField.setText(Integer.toString(this.rimsRadius));
        controller.objectTrailer = this.trailer;
    }

    public boolean checkFields(Controller controller){
        GroundTransport transport = new GroundTransport();
        boolean isCorrect = transport.checkFields(controller) &&
                            checkForEmpty(controller.passengerAssemblyTextField.getText()) &&
                            checkForEmpty(controller.passengerEquipmentTextField.getText()) &&
                            controller.passengerBodyTypeComboBox.getValue() != null;
        if(isCorrect){
            try{
                Integer.parseInt(controller.passengerRimsRadiusTextField.getText());
            }
            catch (Exception e){
                return false;
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public void generateFields(Controller controller) {
        controller.hideThirdLevelPanes();
        controller.passengerCarPane.setVisible(true);
        controller.passengerBodyTypeComboBox.getItems().setAll(bodyTypes.values());
    }
}
