package com.example.ooptransport;
public class SeaTransport extends Transport {
    protected int volumeDisplacement, normalDisplacement;
    protected Engine engine;

    public SeaTransport(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, int volumeDisplacement, int normalDisplacement, Engine engine) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass);
        this.volumeDisplacement = volumeDisplacement;
        this.normalDisplacement = normalDisplacement;
        this.engine = engine;
    }

    public SeaTransport() {
        super();
        this.volumeDisplacement = 0;
        this.normalDisplacement = 0;
        this.engine = null;
    }

    public SeaTransport(Controller controller){
        super(controller);
        this.volumeDisplacement = Integer.parseInt(controller.seaVolumeDisplacementTextField.getText());
        this.normalDisplacement = Integer.parseInt(controller.seaNormalDisplacementTextField.getText());
        this.engine = controller.objectEngines[0];
    }

    public static boolean checkFields(TrailerWindowController controller) {
        boolean isCorrect = Transport.checkFields(controller) && controller.objectEngines != null;
        if(isCorrect){
            try{
                Double.parseDouble(controller.seaNormalDisplacementTextField.getText());
                Double.parseDouble(controller.seaVolumeDisplacementTextField.getText());
            }
            catch(Exception e){
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public void generateFields(Controller controller) {
        controller.hideSecondLevelPanes();
        controller.hideThirdLevelPanes();
        controller.seaTransportPane.setVisible(true);
    }
}
