package com.example.ooptransport;

import javafx.geometry.Orientation;
import javafx.scene.control.*;
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

    private int labelsYStart = 5;
    private int labelsDistance = 20;
    protected Label addLabelWithPos(String text){
        Label label = new Label(text);
        label.setLayoutX(10);
        label.setLayoutY(labelsYStart);
        labelsYStart += labelsDistance;
        return label;
    }

    public void setLabelsYStart(int start){
        this.labelsYStart = start;
    }

    public int getLabelsYStart(){
        return this.labelsYStart;
    }

    public AnchorPane initAnchor(){
        AnchorPane anchor = new AnchorPane();
        Label cylinderLabel = addLabelWithPos("Cylinder: " + Integer.toString(this.cylinders));
        Label horsepowerLabel = addLabelWithPos("Horsepower: " + Integer.toString(this.horsepower));
        Label torqueLabel = addLabelWithPos("Torque: " + Integer.toString(this.torque));
        Label volumeLabel = addLabelWithPos("Volume: " + Double.toString(this.volume));
        Label manLabel = addLabelWithPos("Manufacturer: " + this.manufacturer);
        anchor.getChildren().addAll(cylinderLabel, horsepowerLabel, torqueLabel, volumeLabel, manLabel);
        return anchor;
    }

    public void setFields(EngineWindowController controller){
        controller.cylindersTextField.setText(Integer.toString(this.cylinders));
        controller.horsepowerTextField.setText(Integer.toString(this.horsepower));
        controller.torqueTextField.setText(Integer.toString(this.torque));
        controller.volumeTextField.setText(Double.toString(this.volume));
        controller.manufacturerTextField.setText(this.manufacturer);
    }

    public TitledPane addTile(){
        MenuItem editItem = new MenuItem("Edit");
        MenuItem deleteItem = new MenuItem("Delete");
        ContextMenu menu = new ContextMenu(editItem, deleteItem);
        TitledPane titled = new TitledPane(this.manufacturer, initAnchor());
        titled.setContextMenu(menu);
        return titled;
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
