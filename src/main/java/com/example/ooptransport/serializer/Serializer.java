package com.example.ooptransport.serializer;

import com.example.ooptransport.Transport;

import java.util.ArrayList;

public interface Serializer {
    String getName();
    String getExtension();
    void serialize(ArrayList<Transport> transport, String path);
    ArrayList<Transport> deserialize(String path);
}
