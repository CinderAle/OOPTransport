package com.example.ooptransport;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Trailer extends Transport {
    private String trailerType;
    private int height, width;

    public Trailer(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, String trailerType, int height, int width) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass);
        this.trailerType = trailerType;
        this.height = height;
        this.width = width;
    }
    public Trailer(TrailerWindowController controller){
        super(controller);
        this.trailerType = controller.trailerConnectionTextField.getText();
        this.height = Integer.parseInt(controller.trailerHeightField.getText());
        this.width = Integer.parseInt(controller.trailerWidthField.getText());
    }

    public Trailer() {
        super();
        this.trailerType = "";
        this.width = 0;
        this.height = 0;
    }

    public AnchorPane initAnchor(){
        Transport transport = this;
        AnchorPane anchor = transport.initAnchor();
        Label type = addLabelWithPos("Connection type: " + this.trailerType);
        Label width =addLabelWithPos("Width: " + this.width);
        Label height = addLabelWithPos("Height: " + this.height);
        anchor.getChildren().addAll(type, width, height);
        return anchor;
    }

    public void setFields(TrailerWindowController controller){
        Transport transport = this;
        transport.setFields(controller);
        controller.trailerConnectionTextField.setText(this.trailerType);
        controller.trailerWidthField.setText(Integer.toString(this.width));
        controller.trailerHeightField.setText(Integer.toString(this.height));
    }

    public boolean checkFields(TrailerWindowController controller){
        Transport transport = new Transport();
        boolean isCorrect = transport.checkFields(controller) && checkForEmpty(controller.trailerConnectionTextField.getText());
        if(isCorrect){
            try{
                Integer.parseInt(controller.trailerWidthField.getText());
                Integer.parseInt(controller.trailerHeightField.getText());
            }
            catch(Exception e){
                return false;
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public void generateFields(Controller controller) {

    }
}
