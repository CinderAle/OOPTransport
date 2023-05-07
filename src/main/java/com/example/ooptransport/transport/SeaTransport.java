package com.example.ooptransport.transport;

import com.example.ooptransport.Controller;
import com.example.ooptransport.Transport;
import com.example.ooptransport.transportfactory.TransportFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.Serializable;

public class SeaTransport extends Transport implements Serializable {
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

    public SeaTransport(Controller controller){
        super(controller);
        this.volumeDisplacement = Integer.parseInt(controller.seaVolumeDisplacementTextField.getText());
        this.normalDisplacement = Integer.parseInt(controller.seaNormalDisplacementTextField.getText());
        this.engine = controller.objectEngines.clone()[0];
    }

    @Override
    public AnchorPane initAnchor(){
        AnchorPane anchor = super.initAnchor();
        Label volume = addLabelWithPos("Volume displacement: " + this.volumeDisplacement);
        Label normal = addLabelWithPos("Normal displacement: " + this.normalDisplacement);
        Label engineLabel = addLabelWithPos("Engine:");
        this.engine.setLabelsYStart(this.labelsYStart);
        anchor.getChildren().addAll(volume, normal, engineLabel, this.engine.initAnchor());
        setLabelsYStart(this.engine.getLabelsYStart());
        return anchor;
    }

    public SeaTransport fetchDataFromFields(Controller controller){
        return new SeaTransport(controller);
    }

    public void setFields(Controller controller){
        super.setFields(controller);
        controller.seaVolumeDisplacementTextField.setText(Integer.toString(this.volumeDisplacement));
        controller.seaNormalDisplacementTextField.setText(Integer.toString(this.normalDisplacement));
        controller.objectEngines = new Engine[1];
        controller.objectEngines[0] = this.engine;
    }

    public boolean checkFields(Controller controller) {
        Transport transport = new Transport();
        boolean isCorrect = transport.checkFields(controller) && controller.objectEngines != null;
        if(isCorrect){
            try{
                Double.parseDouble(controller.seaNormalDisplacementTextField.getText());
                Double.parseDouble(controller.seaVolumeDisplacementTextField.getText());
            }
            catch(Exception e){
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void generateFields(Controller controller) {
        super.generateFields(controller);
        if(controller.vehicleTypeComboBox.getValue() == null)
            controller.vehicleTypeComboBox.setValue(new TransportFactory("Sea transport", new SeaTransport()));
        controller.hideSecondLevelPanes();
        controller.hideThirdLevelPanes();
        controller.seaTransportPane.setVisible(true);
    }
}
