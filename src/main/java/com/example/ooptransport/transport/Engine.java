package com.example.ooptransport.transport;

import com.example.ooptransport.Controller;
import com.example.ooptransport.EngineWindowController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.io.Serializable;


public class Engine implements Serializable {
    private int cylinders, horsepower, torque;
    private double volume;
    private String manufacturer;

    public Engine(int cylinders, int horsepower, int torque, double volume, String manufacturer) {
        this.cylinders = cylinders;
        this.horsepower = horsepower;
        this.torque = torque;
        this.volume = volume;
        this.manufacturer = manufacturer;
    }

    public Engine() {
        this.cylinders = 0;
        this.horsepower = 0;
        this.torque = 0;
        this.volume = 0;
        this.manufacturer = "";
        this.labelsYStart = 5;
    }

    public static boolean checkForEmpty(TextField tf) {
        return tf.getText().length() > 0;
    }

    private int labelsYStart;
    private int labelsDistance = 20;
    protected Label addLabelWithPos(String text){
        Label label = new Label(text);
        label.setLayoutX(10);
        label.setLayoutY(labelsYStart);
        labelsYStart += labelsDistance;
        return label;
    }

    public void initLabelsYStart(int start){
        this.labelsYStart = start;
    }

    public int fetchLabelsYStart(){
        return this.labelsYStart;
    }

    public AnchorPane initAnchor() {
        AnchorPane anchor = new AnchorPane();
        Label dividerStart = addLabelWithPos("------------------------------------------------");
        Label cylinderLabel = addLabelWithPos("Cylinders: " + Integer.toString(this.cylinders));
        Label horsepowerLabel = addLabelWithPos("Horsepower, hp: " + Integer.toString(this.horsepower));
        Label torqueLabel = addLabelWithPos("Torque, nm: " + Integer.toString(this.torque));
        Label volumeLabel = addLabelWithPos("Volume, l: " + Double.toString(this.volume));
        Label manLabel = addLabelWithPos("Manufacturer: " + this.manufacturer);
        Label dividerEnd = addLabelWithPos("------------------------------------------------");
        anchor.getChildren().addAll(dividerStart, cylinderLabel, horsepowerLabel, torqueLabel, volumeLabel, manLabel, dividerEnd);
        return anchor;
    }

    public void completeFields(EngineWindowController controller){
        controller.cylindersTextField.setText(Integer.toString(this.cylinders));
        controller.horsepowerTextField.setText(Integer.toString(this.horsepower));
        controller.torqueTextField.setText(Integer.toString(this.torque));
        controller.volumeTextField.setText(Double.toString(this.volume));
        controller.manufacturerTextField.setText(this.manufacturer);
    }

    public TitledPane addTile(Controller controller){
        this.labelsYStart = 5;
        TitledPane titled = new TitledPane(this.manufacturer, initAnchor());
        titled.setContextMenu(new TitledContextMenu(titled, controller));
        return titled;
    }

    public static Engine fetchFilled(EngineWindowController controller){
        Engine engine = new Engine(Integer.parseInt(controller.cylindersTextField.getText()),
                                    Integer.parseInt(controller.horsepowerTextField.getText()),
                                    Integer.parseInt(controller.torqueTextField.getText()),
                                    Double.parseDouble(controller.volumeTextField.getText()),
                                    controller.manufacturerTextField.getText());
        return engine;
    }

    public static boolean checkFields(EngineWindowController controller) {
        boolean isCorrect = checkForEmpty(controller.manufacturerTextField);
        if(isCorrect){
            try{
                Integer.parseInt(controller.cylindersTextField.getText());
                Integer.parseInt(controller.horsepowerTextField.getText());
                Integer.parseInt(controller.torqueTextField.getText());
                Double.parseDouble(controller.volumeTextField.getText());
            }
            catch (Exception e){
                return false;
            }
            return true;
        }
        else
            return false;
    }

    private class TitledContextMenu extends ContextMenu {
        public TitledContextMenu(TitledPane tp, Controller controller){
            MenuItem edit = new MenuItem("Edit");
            MenuItem delete = new MenuItem("Delete");
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    controller.deleteEngineTitledPane(tp);
                }
            });
            edit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    try {
                        controller.changeEngineTitledPane(tp);
                    }
                    catch(IOException e){
                        System.out.println("Error occurred!");
                    }
                }
            });
            getItems().add(edit);
            getItems().add(delete);
        }
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getTorque() {
        return torque;
    }

    public void setTorque(int torque) {
        this.torque = torque;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
