package com.example.ooptransport.serializer;

import com.example.ooptransport.Transport;

import java.util.ArrayList;

public class TextSerializer implements Serializer {
    @Override
    public String getName() {
        return "Text";
    }

    @Override
    public String getExtension() {
        return "*.txt";
    }

    @Override
    public void serialize(ArrayList<Transport> transport, String path) {

    }

    @Override
    public ArrayList<Transport> deserialize(String path) {
        return null;
    }
}
/*
*
*
*
* */