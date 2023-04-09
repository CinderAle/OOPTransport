package com.example.ooptransport;
public class PassengerCar extends GroundTransport {
    public enum bodyTypes {Sedan, Coupe, Touring, Hatchback, Crossover, SUV};
    private bodyTypes bodyType;
    private String assembly, equipment;
    private int rimsRadius;
    private Trailer trailer;

    public PassengerCar(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int wheels, int highwayConsumption, int cityConsumption, int gears, wheelDriveTypes wheelDrive, gearboxTypes gearboxType, String gearboxManufacturer, String soundSystem, Engine engine, boolean leftSided, bodyTypes bodyType, String assembly, String equipment, int rimsRadius, Trailer trailer) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass, wheels, highwayConsumption, cityConsumption, gears, wheelDrive, gearboxType, gearboxManufacturer, soundSystem, engine, leftSided);
        this.bodyType = bodyType;
        this.assembly = assembly;
        this.equipment = equipment;
        this.rimsRadius = rimsRadius;
        this.trailer = trailer;
    }

    public PassengerCar() {
        super();
        this.bodyType = null;
        this.assembly = null;
        this.equipment = null;
        this.rimsRadius = 0;
        this.trailer = null;
    }

    public static PassengerCar getFilled(Controller controller) {
        PassengerCar car = (PassengerCar) GroundTransport.getFilled(controller);
        car.bodyType = controller.passengerBodyTypeComboBox.getValue();
        car.trailer = controller.objectTrailer;
        car.assembly = controller.passengerAssemblyTextField.getText();
        car.equipment = controller.passengerEquipmentTextField.getText();
        car.rimsRadius = Integer.parseInt(controller.passengerRimsRadiusTextField.getText());
        return car;
    }

    public static boolean checkFields(TrailerWindowController controller){
        boolean isCorrect = GroundTransport.checkFields(controller) &&
                            checkForEmpty(controller.passengerAssemblyTextField.getText()) &&
                            checkForEmpty(controller.passengerEquipmentTextField.getText()) &&
                            controller.passengerBodyTypeComboBox.getValue() != null;
        if(isCorrect){
            try{
                Integer.parseInt(controller.passengerRimsRadiusTextField.getText());
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
        controller.passengerCarPane.setVisible(true);
        controller.passengerBodyTypeComboBox.getItems().setAll(bodyTypes.values());
    }
}
