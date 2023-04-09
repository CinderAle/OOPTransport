package com.example.ooptransport;

import javafx.scene.control.TextField;

public class Engine {
    private int cylinders, horsepower, torque, volume;
    private String manufacturer;

    public Engine(int cylinders, int horsepower, int torque, int volume, String manufacturer) {
        this.cylinders = cylinders;
        this.horsepower = horsepower;
        this.torque = torque;
        this.volume = volume;
        this.manufacturer = manufacturer;
    }

    public static boolean checkForEmpty(TextField tf) {
        return tf.getText().length() > 0;
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
