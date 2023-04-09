package com.example.ooptransport;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Airplane extends AirTransport {
    private String airplaneClass;
    private int landings;

    public Airplane(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int maxHeight, int maxDistance, Engine[] engines, String airplaneClass, int landings) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass, maxHeight, maxDistance, engines);
        this.airplaneClass = airplaneClass;
        this.landings = landings;
    }

    public Airplane() {
        super();
        this.airplaneClass = null;
        this.landings = 0;
    }

    public Airplane(Controller controller){
        super(controller);
        this.airplaneClass = controller.airplaneClassTextField.getText();
        this.landings = Integer.parseInt(controller.airplaneLandingsTextField.getText());
    }

    public AnchorPane initAnchor(){
        AirTransport transport = this;
        AnchorPane anchor = transport.initAnchor();
        Label airClass = new Label("Airplane class: " + this.airplaneClass);
        Label landings = new Label("Landings: " + this.landings);
        anchor.getChildren().addAll(airClass, landings);
        return anchor;
    }

    public void setFields(Controller controller) {
        AirTransport transport = this;
        transport.setFields(controller);
        controller.airTransportTypeComboBox.getSelectionModel().select(new TransportFactory("Airplane", new Airplane()));
        controller.airplaneClassTextField.setText(this.airplaneClass);
        controller.airplaneLandingsTextField.setText(Integer.toString(this.landings));
    }

    public static boolean checkFields(TrailerWindowController controller) {
        boolean isCorrect = AirTransport.checkFields(controller) &&
                            checkForEmpty(controller.airplaneClassTextField.getText());
        if(isCorrect){
            try{
                Integer.parseInt(controller.airplaneLandingsTextField.getText());
            }
            catch (Exception e){
                return false;
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public void generateFields(Controller controller) {
        controller.hideThirdLevelPanes();
        controller.airplanePane.setVisible(true);
    }
}
