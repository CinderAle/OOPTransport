package com.example.ooptransport;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TrailerWindowController extends Controller {
    public Button trailerCancelButton;
    public Button trailerAddButton;
    public TextField trailerWidthField;
    public TextField trailerHeightField;
    public TextField trailerConnectionTextField;

    public Trailer trailer = null;

    public void closeTrailerWindow(ActionEvent mouseEvent) {
        Stage stage = (Stage)this.trailerCancelButton.getScene().getWindow();
        trailer = null;
        stage.close();
    }

    public void addTrailer(ActionEvent actionEvent) {
        Trailer temp = new Trailer();
        Stage stage = (Stage)this.trailerCancelButton.getScene().getWindow();
        if(temp.checkFields(this)){
            trailer = new Trailer(this);
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not enough data");
            alert.setContentText("Not all of the fields were set or were set wrongly!");
            alert.showAndWait();
        }
    }
}
