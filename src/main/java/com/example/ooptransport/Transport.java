package com.example.ooptransport;

import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

public class Transport {
    protected String brand, model, color, interior, specifications;
    protected int seats, manufactureYear, mileage, mass;

    public Transport(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.interior = interior;
        this.specifications = specifications;
        this.seats = seats;
        this.manufactureYear = manufactureYear;
        this.mileage = mileage;
        this.mass = mass;
    }

    public Transport(Controller controller) {
        this.brand = controller.brandTextBox.getText();
        this.model = controller.modelTextField.getText();
        this.color = controller.colorTextField.getText();
        this.interior = controller.interiorTextField.getText();
        this.specifications = controller.specificationsTextArea.getText();
        this.seats = Integer.parseInt(controller.seatsTextField.getText());
        this.manufactureYear = Integer.parseInt(controller.yearTextField.getText());
        this.mileage = Integer.parseInt(controller.mileageTextField.getText());
        this.mass = Integer.parseInt(controller.massTextField.getText());
    }

    public AnchorPane initAnchor(){
        AnchorPane anchor = new AnchorPane();
        Label brand = new Label("Brand: " + this.brand);
        Label model = new Label("Model: " + this.model);
        Label color = new Label("Color: " + this.color);
        Label interior = new Label("Interior: " + this.interior);
        Label seats = new Label("Seats: " + Integer.toString(this.seats));
        Label year = new Label("Year: " + Integer.toString(this.manufactureYear));
        Label miles = new Label("Miles" + Integer.toString(this.mileage));
        Label mass = new Label("Mass" + Integer.toString(this.mass));
        Label specifications = new Label("Specifications: " + this.specifications);
        anchor.getChildren().addAll(brand, model, color, interior, specifications, seats, year, miles, mass);
        return anchor;
    }

    public void setFields(Controller controller){
        controller.brandTextBox.setText(this.brand);
        controller.modelTextField.setText(this.model);
        controller.colorTextField.setText(this.color);
        controller.interiorTextField.setText(this.interior);
        controller.seatsTextField.setText(Integer.toString(this.seats));
        controller.yearTextField.setText(Integer.toString(this.manufactureYear));
        controller.mileageTextField.setText(Integer.toString(this.mileage));
        controller.massTextField.setText(Integer.toString(this.mass));
        controller.specificationsTextArea.setText(this.specifications);
    }

    public TitledPane getTitledPane() {
        return new TitledPane(this.brand + " " + this.model, initAnchor());
    }

    protected static boolean checkForEmpty(String line) {
        return line.length() > 0;
    }

    public static boolean checkFields(TrailerWindowController controller) {
        boolean isCorrect = checkForEmpty(controller.brandTextBox.getText()) &&
                            checkForEmpty(controller.modelTextField.getText()) &&
                            checkForEmpty(controller.colorTextField.getText()) &&
                            checkForEmpty(controller.interiorTextField.getText());
        if(isCorrect){
            try{
                Integer.parseInt(controller.massTextField.getText());
                Integer.parseInt(controller.mileageTextField.getText());
                Integer.parseInt(controller.yearTextField.getText());
                Integer.parseInt(controller.seatsTextField.getText());
            }
            catch(Exception e){
                return false;
            }
            return true;
        }
        else
            return false;
    }

    public Transport() {
        this.brand = null;
        this.model = null;
        this.color = null;
        this.interior = null;
        this.specifications = null;
        this.seats = 0;
        this.manufactureYear = 0;
        this.mileage = 0;
        this.mass = 0;
    }

    public void generateFields(Controller controller) {

    }
}
