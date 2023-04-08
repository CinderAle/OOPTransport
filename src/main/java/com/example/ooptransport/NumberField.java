package com.example.ooptransport;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NumberField  extends LimitedField {
    @Override
    public void replaceText(int i, int i1, String s) {
        if(s.matches("[0-9]") || s.trim().equals("")){
            super.replaceText(i, i1, s);
        }
    }
}
