package com.example.ooptransport;
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

    public static Transport getFilled(Controller controller){
        Transport transport = new Transport(controller.brandTextBox.getText(), controller.modelTextField.getText(),
                                            controller.colorTextField.getText(), controller.interiorTextField.getText(),
                                            controller.specificationsTextArea.getText(), Integer.parseInt(controller.seatsTextField.getText()),
                                            Integer.parseInt(controller.yearTextField.getText()), Integer.parseInt(controller.mileageTextField.getText()),
                                            Integer.parseInt(controller.massTextField.getText()));
        return transport;
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
