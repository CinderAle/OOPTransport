package com.example.ooptransport;
public class Truck extends GroundTransport {
    private Trailer trailer;
    private String trailerConnection;

    public Truck(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int wheels, int highwayConsumption, int cityConsumption, int gears, wheelDriveTypes wheelDrive, gearboxTypes gearboxType, String gearboxManufacturer, String soundSystem, Engine engine, boolean leftSided, Trailer trailer, String trailerConnection) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass, wheels, highwayConsumption, cityConsumption, gears, wheelDrive, gearboxType, gearboxManufacturer, soundSystem, engine, leftSided);
        this.trailer = trailer;
        this.trailerConnection = trailerConnection;
    }

    public Truck(){
        super();
        this.trailer = null;
        this.trailerConnection = null;
    }

    public static Truck getFilled(Controller controller) {
        Truck truck = (Truck) GroundTransport.getFilled(controller);
        truck.trailer = controller.objectTrailer;
        truck.trailerConnection = controller.truckConnectionTextField.getText();
        return truck;
    }

    public static boolean checkFields(TrailerWindowController controller) {
        boolean isCorrect = GroundTransport.checkFields(controller) &&
                            checkForEmpty(controller.truckConnectionTextField.getText()) &&
                            controller.objectTrailer != null;
        return isCorrect;
    }

    @Override
    public void generateFields(Controller controller) {
        controller.hideThirdLevelPanes();
        controller.truckPane.setVisible(true);
    }
}
