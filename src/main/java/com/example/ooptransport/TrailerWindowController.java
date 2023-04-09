package com.example.ooptransport;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TrailerWindowController extends Controller {
    public Button trailerCancelButton;
    public Button trailerAddButton;
    public TextField trailerWidthField;
    public TextField trailerHeightField;
    public TextField trailerConnectionTextField;

    public boolean isAdded;

    public void closeTrailerWindow(ActionEvent mouseEvent) {
        Stage stage = (Stage)this.trailerCancelButton.getScene().getWindow();
        this.isAdded = false;
        stage.close();
    }

    public void addTrailer(ActionEvent actionEvent) {
        Stage stage = (Stage)this.trailerCancelButton.getScene().getWindow();
        this.isAdded = true;
        stage.close();
    }
}
