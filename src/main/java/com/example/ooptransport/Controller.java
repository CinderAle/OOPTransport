package com.example.ooptransport;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static javafx.collections.FXCollections.observableArrayList;

public class Controller {
    public ComboBox<TransportFactory> vehicleTypeComboBox;
    public TextField brandTextBox;
    public TextField modelTextField;
    public TextField colorTextField;
    public TextField interiorTextField;
    public TextField seatsTextField;
    public TextField yearTextField;
    public TextField mileageTextField;
    public TextField massTextField;
    public Button addVehicleButton;
    public TextArea specificationsTextArea;
    public Pane dataFieldsPane;
    public Pane basicDataFieldsPane;
    public Pane airTransportPane;
    public Pane groundTransportPane;
    public Pane seaTransportPane;
    public ComboBox<TransportFactory> airTransportTypeComboBox;
    public TextField airMaxDistanceTextField;
    public TextField airMaxHeightTextField;
    public Button airAddEngineButton;
    public TextField seaVolumeDisplacementTextField;
    public TextField seaNormalDisplacementTextField;
    public Button seaEditEngineButton;
    public TextField groundWheelsTextField;
    public TextField groundHighwayTextField;
    public TextField groundCityTextField;
    public TextField groundGearsTextField;
    public ComboBox<GroundTransport.wheelDriveTypes> groundWheelDriveComboBox;
    public TextField groundGearboxManTextField;
    public TextField groundSoundTextField;
    public CheckBox groundLeftSidedCheckbox;
    public ComboBox<TransportFactory> groundTypeComboBox;
    public Button groundEditEngineButton;
    public ComboBox<GroundTransport.gearboxTypes> groundGearboxComboBox;
    public Pane airplanePane;
    public Pane helicopterPane;
    public Pane passengerCarPane;
    public Pane truckPane;
    public TextField airplaneClassTextField;
    public TextField airplaneLandingsTextField;
    public TextField helicopterBladesTextField;
    public TextField helicopterRotorsTypeTextField;
    public TextField passengerAssemblyTextField;
    public TextField passengerEquipmentTextField;
    public TextField passengerRimsRadiusTextField;
    public ComboBox<PassengerCar.bodyTypes> passengerBodyTypeComboBox;
    public Button passengerTrailerButton;
    public Button truckTrailerButton;
    public TextField truckConnectionTextField;
    public Engine[] objectEngines;
    public Trailer objectTrailer;
    public Accordion enginesAccordion;
    public Accordion objectsAccordion;
    boolean isChanging = false;

    ArrayList<Transport> allTransport = null;

    public void hideSecondLevelPanes() {
        this.objectEngines = null;
        this.objectTrailer = null;
        airTransportPane.setVisible(false);
        groundTransportPane.setVisible(false);
        seaTransportPane.setVisible(false);
    }

    public void hideThirdLevelPanes() {
        this.airplanePane.setVisible(false);
        this.helicopterPane.setVisible(false);
        this.passengerCarPane.setVisible(false);
        this.truckPane.setVisible(false);
    }

    public void setTransportComboBoxItems(ComboBox<TransportFactory> combo, ObservableList<TransportFactory> tf) {
        combo.setConverter(new StringConverter<TransportFactory>() {
            @Override
            public String toString(TransportFactory transportFactory) {
                return transportFactory != null ? transportFactory.getTransportTypeName() : "";
            }
            @Override
            public TransportFactory fromString(String s) {
                return null;
            }
        });
        combo.setItems(tf);
        combo.valueProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal != null)
                newVal.getTransportType().generateFields(this);
        });
    }

    public void generateNewAccordItem() {

    }

    public void alertNotSetValues(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Not enough data");
        alert.setContentText("Not all of the fields were set or were set wrongly!");
        alert.showAndWait();
    }

    private TransportFactory checkSetTypesCombo(){
        TransportFactory tf = groundTypeComboBox.getValue();
        if(tf == null)
            tf = airTransportTypeComboBox.getValue();
        if(tf == null) {
            tf = vehicleTypeComboBox.getValue();
            if(tf != null && !tf.getTransportTypeName().equals("Sea transport"))
                tf = null;
        }
        return tf;
    }

    public void addVehicle(MouseEvent mouseEvent) {
        if(!isChanging) {
            this.basicDataFieldsPane.setVisible(true);
            setTransportComboBoxItems(this.vehicleTypeComboBox, observableArrayList(
                    new TransportFactory("Air transport", new AirTransport()),
                    new TransportFactory("Ground transport", new GroundTransport()),
                    new TransportFactory("Sea transport", new SeaTransport())
            ));
            isChanging = true;
        }
        else{
            TransportFactory tf = checkSetTypesCombo();
            if(tf != null){
                Transport transport = tf.getTransportType();
                if(transport.checkFields(this)){
                    transport = transport.fetchDataFromFields(this);
                    if(allTransport == null)
                        allTransport = new ArrayList<Transport>();
                    allTransport.add(transport);
                    this.objectsAccordion.getPanes().clear();
                    for(Transport t : allTransport)
                        this.objectsAccordion.getPanes().add(t.getTitledPane());
                    hideThirdLevelPanes();
                    hideSecondLevelPanes();
                    basicDataFieldsPane.setVisible(false);
                    isChanging = false;
                }
                else
                    alertNotSetValues();
            }
            else{
                alertNotSetValues();
            }
        }
    }

    private void deleteEngine(int id){
        Engine[] tempEngines = new Engine[this.objectEngines.length - 1];
        for(int i = 0;i < id;i++)
            tempEngines[i] = this.objectEngines[i];
        for(int i = 0;i + id < this.objectEngines.length;i++)
            tempEngines[i + id] = this.objectEngines[i + id + 1];
        this.objectEngines = tempEngines;
    }

    private void changeEngineInfo(int id, Engine engine){
        if(this.objectEngines != null && id < this.objectEngines.length)
            this.objectEngines[id] = engine;
        else if(id == 0){
            this.objectEngines = new Engine[1];
            this.objectEngines[0] = engine;
        }
    }

    private void addEngine(Engine engine) {
        Engine[] tempEngines = new Engine[this.objectEngines != null ? this.objectEngines.length + 1 : 1];
        for (int i = 0; i < tempEngines.length - 1; i++)
            tempEngines[i] = this.objectEngines[i];
        tempEngines[tempEngines.length - 1] = engine;
        this.objectEngines = tempEngines;

    }

    public void addEngineAccord(){
        this.enginesAccordion.getPanes().clear();
        for(Engine engine: this.objectEngines)
            enginesAccordion.getPanes().add(engine.addTile());
    }

    public void showEngineWindow(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(TransportApplication.class.getResource("engine-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        EngineWindowController controller = fxmlLoader.getController();
        if(this.objectEngines != null && this.objectEngines.length == 1)
            this.objectEngines[0].setFields(controller);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Engine editor");
        stage.setScene(scene);
        stage.showAndWait();
        if(controller.engine != null && this.vehicleTypeComboBox.getValue() != null){
            if(this.vehicleTypeComboBox.getValue().getTransportTypeName().equals("Air transport")) {
                addEngine(controller.engine);
                addEngineAccord();
            }
            else
                changeEngineInfo(0, controller.engine);
        }
    }

    public void showTrailerWindow(MouseEvent mouseEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(TransportApplication.class.getResource("trailer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);
        TrailerWindowController controller = fxmlLoader.getController();
        if(this.objectTrailer != null)
            this.objectTrailer.setFields(controller);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Trailer editor");
        stage.setScene(scene);
        stage.showAndWait();
        this.objectTrailer = controller.trailer;
    }
}