package com.example.ooptransport;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Truck extends GroundTransport {
    private Trailer trailer;
    private String trailerConnection;

    public Truck(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int wheels, int highwayConsumption, int cityConsumption, int gears, wheelDriveTypes wheelDrive, gearboxTypes gearboxType, String gearboxManufacturer, String soundSystem, Engine engine, boolean leftSided, Trailer trailer, String trailerConnection) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass, wheels, highwayConsumption, cityConsumption, gears, wheelDrive, gearboxType, gearboxManufacturer, soundSystem, engine, leftSided);
        this.trailer = trailer;
        this.trailerConnection = trailerConnection;
    }

    public Truck(){
        super();
        this.trailer = null;
        this.trailerConnection = null;
    }

    public Truck(Controller controller){
        super(controller);
        this.trailer = controller.objectTrailer;
        this.trailerConnection = controller.truckConnectionTextField.getText();
    }

    public void setFields(Controller controller) {
        super.setFields(controller);
        controller.groundTypeComboBox.getSelectionModel().select(new TransportFactory("Truck", new Truck()));
        controller.truckConnectionTextField.setText(this.trailerConnection);
        controller.objectTrailer = this.trailer;
    }

    public Truck fetchDataFromFields(Controller controller) {
        return new Truck(controller);
    }

    public AnchorPane initAnchor(){
        AnchorPane anchor = super.initAnchor();
        Label connection = addLabelWithPos("Connection type: " + this.trailerConnection);
        this.trailer.setLabelsYStart(this.labelsYStart);
        anchor.getChildren().addAll(connection, new Label("Trailer: "), this.trailer.initAnchor());
        return anchor;
    }

    public boolean checkFields(Controller controller) {
        GroundTransport transport = new GroundTransport();
        boolean isCorrect = transport.checkFields(controller) &&
                            checkForEmpty(controller.truckConnectionTextField.getText()) &&
                            controller.objectTrailer != null;
        return isCorrect;
    }

    @Override
    public void generateFields(Controller controller) {
        controller.hideThirdLevelPanes();
        controller.truckPane.setVisible(true);
    }
}
