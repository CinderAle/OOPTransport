package com.example.ooptransport;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

public class Engine {
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

    public static boolean checkForEmpty(TextField tf) {
        return tf.getText().length() > 0;
    }

    public AnchorPane initAnchor(){
        AnchorPane anchor = new AnchorPane();
        Label cylinderLabel = new Label("Cylinder: " + Integer.toString(this.cylinders));
        Label horsepowerLabel = new Label("Horsepower: " + Integer.toString(this.horsepower));
        Label torqueLabel = new Label("Torque: " + Integer.toString(this.torque));
        Label volumeLabel = new Label("Volume: " + Double.toString(this.volume));
        Label manLabel = new Label("Manufacturer: " + this.manufacturer);
        anchor.getChildren().addAll(cylinderLabel, horsepowerLabel, torqueLabel, volumeLabel, manLabel);
        return anchor;
    }

    public TitledPane addTile(){
        return new TitledPane(this.manufacturer, initAnchor());
    }

    public static Engine getFilled(EngineWindowController controller){
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
}
