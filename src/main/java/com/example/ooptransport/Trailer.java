package com.example.ooptransport;
public class Trailer extends Transport {
    private String trailerType;
    private int height, width;

    public Trailer(String brand, String model, String color, String interior, String specifications, int seats, int manufactureYear, int mileage, int mass, String trailerType, int height, int width) {
        super(brand, model, color, interior, specifications, seats, manufactureYear, mileage, mass);
        this.trailerType = trailerType;
        this.height = height;
        this.width = width;
    }

    public static Trailer getFilled(TrailerWindowController controller) {
        Trailer trailer = (Trailer) Transport.getFilled(controller);
        trailer.trailerType = controller.trailerConnectionTextField.getText();
        trailer.height = Integer.parseInt(controller.trailerHeightField.getText());
        trailer.width = Integer.parseInt(controller.trailerWidthField.getText());
        return trailer;
    }

    public static boolean checkFields(TrailerWindowController controller){
        boolean isCorrect = Transport.checkFields(controller) && checkForEmpty(controller.trailerConnectionTextField.getText());
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
