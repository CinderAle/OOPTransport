package com.example.ooptransport.serializer;

import com.example.ooptransport.Transport;

import java.io.*;
import java.util.ArrayList;

public class BinarySerializer implements Serializer {

    @Override
    public String getName() {
        return "Binary";
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
            System.err.println("An error occurred while saving the list to the file!");
        }
    }

    @Override
    public ArrayList<Transport> deserialize(String path) {
        ArrayList<Transport> transport = new ArrayList<>();
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))){
            transport = (ArrayList<Transport>) inputStream.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            System.err.println("An error occurred while reading the list from the file!");
        }
        return transport;
    }
}
