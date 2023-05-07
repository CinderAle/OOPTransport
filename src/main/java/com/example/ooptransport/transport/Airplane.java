package com.example.ooptransport.transport;

import com.example.ooptransport.Controller;
import com.example.ooptransport.transportfactory.TransportFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class Airplane extends AirTransport implements Serializable {
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

    public Airplane(Controller controller){
        super(controller);
        this.airplaneClass = controller.airplaneClassTextField.getText();
        this.landings = Integer.parseInt(controller.airplaneLandingsTextField.getText());
    }

    public AnchorPane initAnchor(){
        AnchorPane anchor = super.initAnchor();
        Label airClass = addLabelWithPos("Airplane class: " + this.airplaneClass);
        Label landings = addLabelWithPos("Landings: " + this.landings);
        anchor.getChildren().addAll(airClass, landings);
        return anchor;
    }

    public Airplane fetchDataFromFields(Controller controller) {
        return new Airplane(controller);
    }

    public void setFields(Controller controller) {
        super.setFields(controller);
        controller.airTransportTypeComboBox.getSelectionModel().select(new TransportFactory("Airplane", new Airplane()));
        controller.airplaneClassTextField.setText(this.airplaneClass);
        controller.airplaneLandingsTextField.setText(Integer.toString(this.landings));
    }

    public boolean checkFields(Controller controller) {
        AirTransport transport = new AirTransport();
        boolean isCorrect = transport.checkFields(controller) &&
                            checkForEmpty(controller.airplaneClassTextField.getText());
        if(isCorrect){
            try{
                Integer.parseInt(controller.airplaneLandingsTextField.getText());
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
        this.engines = controller.objectEngines;
        super.generateFields(controller);
        controller.objectEngines = this.engines;
        if(controller.airTransportTypeComboBox.getValue() == null)
            controller.airTransportTypeComboBox.setValue(new TransportFactory("Airplane", new Airplane()));
        controller.hideThirdLevelPanes();
        controller.airplanePane.setVisible(true);
    }
}
