package com.example.ooptransport;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
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

    public boolean isAdded;

    public void closeEngineWindow(MouseEvent mouseEvent) {
        Stage stage = (Stage)this.engineCancelButton.getScene().getWindow();
        this.isAdded = false;
        stage.close();
    }

    public void addEngine(ActionEvent actionEvent) {
        Stage stage = (Stage)this.engineCancelButton.getScene().getWindow();
        this.isAdded = true;
        stage.close();
    }
}
