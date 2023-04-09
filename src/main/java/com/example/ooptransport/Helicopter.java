package com.example.ooptransport;
public class Helicopter extends AirTransport {
    private int blades;
    private String rotorsType;

    public Helicopter(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int maxHeight, int maxDistance, Engine[] engines, int blades, String rotorsType) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass, maxHeight, maxDistance, engines);
        this.blades = blades;
        this.rotorsType = rotorsType;
    }

    public Helicopter(){
        super();
        this.blades = 0;
        this.rotorsType = null;
    }

    public Helicopter(Controller controller){
        super(controller);
        this.blades = Integer.parseInt(controller.helicopterBladesTextField.getText());
        this.rotorsType = controller.helicopterRotorsTypeTextField.getText();
    }

    public static boolean checkFields(TrailerWindowController controller) {
        boolean isCorrect = AirTransport.checkFields(controller) &&
                            checkForEmpty(controller.helicopterRotorsTypeTextField.getText());
        if (isCorrect){
            try{
                Integer.parseInt(controller.helicopterRotorsTypeTextField.getText());
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
        controller.hideThirdLevelPanes();
        controller.helicopterPane.setVisible(true);
    }
}
