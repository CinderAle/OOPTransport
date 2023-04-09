package com.example.ooptransport;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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

    public Transport fetchDataFromFields(Controller controller){
        return new Transport(controller);
    }

    protected int labelsYStart = 5;
    protected int labelsDistance = 20;
    protected Label addLabelWithPos(String text){
        Label label = new Label(text);
        label.setLayoutX(10);
        label.setLayoutY(labelsYStart);
        labelsYStart += labelsDistance;
        return label;
    }

    public void setLabelsYStart(int labelsYStart) {
        this.labelsYStart = labelsYStart;
    }

    public AnchorPane initAnchor(){
        AnchorPane anchor = new AnchorPane();
        Label brand = addLabelWithPos("Brand: " + this.brand);
        Label model = addLabelWithPos("Model: " + this.model);
        Label color = addLabelWithPos("Color: " + this.color);
        Label interior = addLabelWithPos("Interior: " + this.interior);
        Label seats = addLabelWithPos("Seats: " + Integer.toString(this.seats));
        Label year = addLabelWithPos("Year: " + Integer.toString(this.manufactureYear));
        Label miles = addLabelWithPos("Miles" + Integer.toString(this.mileage));
        Label mass = addLabelWithPos("Mass" + Integer.toString(this.mass));
        Label specifications = addLabelWithPos("Specifications: " + this.specifications);
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
        MenuItem edit = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");
        ContextMenu menu = new ContextMenu(edit, delete);
        TitledPane titledPane = new TitledPane(this.brand + " " + this.model, this.initAnchor());
        titledPane.setContextMenu(menu);
        return titledPane;
    }

    protected static boolean checkForEmpty(String line) {
        return line.length() > 0;
    }

    public boolean checkFields(Controller controller) {
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
