package com.example.ooptransport.transport;

import com.example.ooptransport.*;
import com.example.ooptransport.transportfactory.TransportFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

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

    public AirTransport(Controller controller) {
        super(controller);
        this.maxHeight = Integer.parseInt(controller.airMaxHeightTextField.getText());
        this.maxDistance = Integer.parseInt(controller.airMaxDistanceTextField.getText());
        this.engines = controller.objectEngines.clone();
    }

    public AnchorPane initAnchor(){
        AnchorPane anchor = super.initAnchor();
        Label maxHeight = addLabelWithPos("Maximum height: " + this.maxHeight);
        Label maxDistance = addLabelWithPos("Maximum distance: " + this.maxDistance);
        anchor.getChildren().addAll(maxHeight, maxDistance, addLabelWithPos("Engines: "));
        for(Engine engine: this.engines) {
            engine.initLabelsYStart(labelsYStart);
            anchor.getChildren().add(engine.initAnchor());
            initLabelsYStart(engine.fetchLabelsYStart());
        }
        return anchor;
    }

    public AirTransport fetchDataFromFields(Controller controller) {
        return new AirTransport(controller);
    }

    public void completeFields(Controller controller){
        super.completeFields(controller);
        controller.vehicleTypeComboBox.getSelectionModel().select(new TransportFactory("Air transport", new AirTransport()));
        controller.airMaxDistanceTextField.setText(Integer.toString(this.maxDistance));
        controller.airMaxHeightTextField.setText(Integer.toString(this.maxHeight));
        if(this.engines != null) {
            controller.objectEngines = this.engines;
            controller.addEngineAccord();
        }
    }

    public boolean checkFields(Controller controller){
        Transport transport = new Transport();
        boolean isCorrect = transport.checkFields(controller) &&
                            controller.objectEngines != null && controller.objectEngines.length > 0;
        if(isCorrect){
            try{
                Integer.parseInt(controller.airMaxDistanceTextField.getText());
                Integer.parseInt(controller.airMaxHeightTextField.getText());
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
        super.generateFields(controller);
        if(controller.vehicleTypeComboBox.getValue() == null)
            controller.vehicleTypeComboBox.setValue(new TransportFactory("Air transport", new AirTransport()));
        controller.hideSecondLevelPanes();
        controller.hideThirdLevelPanes();
        controller.airTransportPane.setVisible(true);
        if(controller.airTransportTypeComboBox.getValue() == null)
            controller.setTransportComboBoxItems(controller.airTransportTypeComboBox, observableArrayList(
                new TransportFactory("Airplane", new Airplane()),
                new TransportFactory("Helicopter", new Helicopter())
            ));
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    public Engine[] getEngines() {
        return engines;
    }

    public void setEngines(ArrayList<Engine> engines) {
        this.engines = new Engine[engines.size()];
        for(int i = 0;i < engines.size();i++)
            this.engines[i] = engines.get(i);
    }
}
