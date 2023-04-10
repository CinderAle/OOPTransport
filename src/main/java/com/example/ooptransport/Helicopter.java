package com.example.ooptransport;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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

    public Helicopter(Controller controller){
        super(controller);
        this.blades = Integer.parseInt(controller.helicopterBladesTextField.getText());
        this.rotorsType = controller.helicopterRotorsTypeTextField.getText();
    }

    public AnchorPane initAnchor(){
        AnchorPane anchor = super.initAnchor();
        Label blades = addLabelWithPos("Blades: " + this.blades);
        Label rotorsType = addLabelWithPos("Rotors type: " + this.rotorsType);
        anchor.getChildren().addAll(blades, rotorsType);
        return anchor;
    }

    public Helicopter fetchDataFromFields(Controller controller) {
        return new Helicopter(controller);
    }

    public void setFields(Controller controller) {
        super.setFields(controller);
        controller.airTransportTypeComboBox.getSelectionModel().select(new TransportFactory("Helicopter", new Helicopter()));
        controller.helicopterBladesTextField.setText(Integer.toString(this.blades));
        controller.helicopterRotorsTypeTextField.setText(this.rotorsType);
    }

    public boolean checkFields(Controller controller) {
        AirTransport transport = new AirTransport();
        boolean isCorrect = transport.checkFields(controller) &&
                            checkForEmpty(controller.helicopterRotorsTypeTextField.getText());
        if (isCorrect){
            try{
                Integer.parseInt(controller.helicopterRotorsTypeTextField.getText());
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
        this.engines = controller.objectEngines;
        super.generateFields(controller);
        controller.objectEngines = this.engines;
        if(controller.airTransportTypeComboBox.getValue() == null)
            controller.airTransportTypeComboBox.setValue(new TransportFactory("Helicopter", new Helicopter()));
        controller.hideThirdLevelPanes();
        controller.helicopterPane.setVisible(true);
    }
}
