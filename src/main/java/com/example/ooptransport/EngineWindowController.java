package com.example.ooptransport;

import com.example.ooptransport.transport.Engine;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EngineWindowController {

    public TextField cylindersTextField;
    public TextField horsepowerTextField;
    public TextField torqueTextField;
    public TextField volumeTextField;
    public TextField manufacturerTextField;
    public Button engineCancelButton;
    public Button engineAddButton;

    public Engine engine = null;

    public void closeEngineWindow(MouseEvent mouseEvent) {
        Stage stage = (Stage)this.engineCancelButton.getScene().getWindow();
        engine = null;
        stage.close();
    }

    public void addEngine(ActionEvent actionEvent) {
        Stage stage = (Stage)this.engineCancelButton.getScene().getWindow();
        if(Engine.checkFields(this)) {
            engine = Engine.getFilled(this);
            stage.close();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not enough data");
            alert.setContentText("Not all of the fields were set or were set wrongly!");
            alert.showAndWait();
        }
    }
}
