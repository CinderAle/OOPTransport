package com.example.ooptransport.customcontrols;

public class NumberField  extends LimitedField {
    @Override
    public void replaceText(int i, int i1, String s) {
        if(s.matches("[0-9]") || s.trim().equals("")){
            super.replaceText(i, i1, s);
        }
    }
}
