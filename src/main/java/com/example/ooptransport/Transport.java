package com.example.ooptransport;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import java.io.Serializable;

public class Transport implements Serializable {
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
        Label seats = addLabelWithPos("Seats: " + this.seats);
        Label year = addLabelWithPos("Year: " + this.manufactureYear);
        Label miles = addLabelWithPos("Miles: " + this.mileage);
        Label mass = addLabelWithPos("Mass: " + this.mass);
        Label specifications = addLabelWithPos("Specifications: " + this.specifications);
        anchor.getChildren().addAll(brand, model, color, interior, specifications, seats, year, miles, mass);
        return anchor;
    }

    public void completeFields(Controller controller){
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

    public TitledPane getTitledPane(Controller controller) {
        TitledPane titledPane = new TitledPane(this.brand + " " + this.model, this.initAnchor());
        titledPane.setContextMenu(new TitledContextMenu(titledPane, controller));
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
        controller.basicDataFieldsPane.setVisible(true);
    }

    private class TitledContextMenu extends ContextMenu {
        public TitledContextMenu(TitledPane tp, Controller controller){
            MenuItem edit = new MenuItem("Edit");
            MenuItem delete = new MenuItem("Delete");
            delete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    int id = controller.objectsAccordion.getPanes().indexOf(tp);
                    controller.allTransport.remove(id);
                    controller.objectsAccordion.getPanes().remove(id);
                }
            });
            edit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    int id = controller.objectsAccordion.getPanes().indexOf(tp);
                    controller.objectsAccordion.getPanes().remove(id);
                    controller.isChanging = true;
                    controller.allTransport.get(id).completeFields(controller);
                    controller.allTransport.get(id).generateFields(controller);
                    controller.allTransport.remove(id);
                }
            });
            getItems().add(edit);
            getItems().add(delete);
        }
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setManufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getInterior() {
        return interior;
    }

    public String getSpecifications() {
        return specifications;
    }

    public int getSeats() {
        return seats;
    }

    public int getManufactureYear() {
        return manufactureYear;
    }

    public int getMileage() {
        return mileage;
    }

    public int getMass() {
        return mass;
    }
}
