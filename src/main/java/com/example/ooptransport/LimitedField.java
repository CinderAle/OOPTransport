package com.example.ooptransport;

import javafx.scene.control.TextField;

public class LimitedField extends TextField {
    int maxLength = 0;

    @Override
    public void replaceText(int i, int i1, String s) {
        if(this.maxLength <= 0 || this.getText().length() < this.maxLength || s.length() == 0){
            super.replaceText(i, i1, s);
        }
    }

    public int getMaxLength(){
        return this.maxLength;
    }

    public void setMaxLength(int val){
        this.maxLength = val;
    }
}
