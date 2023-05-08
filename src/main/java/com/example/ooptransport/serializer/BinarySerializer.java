package com.example.ooptransport.serializer;

import com.example.ooptransport.Transport;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;

public class BinarySerializer implements Serializer {

    @Override
    public String getName() {
        return "Binary (*.bin)";
    }

    @Override
    public String getExtension() {
        return "*.bin";
    }

    @Override
    public void serialize(ArrayList<Transport> transport, String path) {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
            outputStream.writeObject(transport);
        }
        catch(IOException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while saving the list to the file!").showAndWait();
        }
    }

    @Override
    public ArrayList<Transport> deserialize(String path) {
        ArrayList<Transport> transport = new ArrayList<>();
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))){
            transport = (ArrayList<Transport>) inputStream.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, "An error occurred while reading the list from the file!").showAndWait();
        }
        return transport;
    }
}
