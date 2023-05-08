package com.example.ooptransport.serializer;

import com.example.ooptransport.Transport;
import com.example.ooptransport.transport.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import javafx.scene.control.Alert;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
public class JSONSerialize implements Serializer {
    private final RuntimeTypeAdapterFactory<Transport> typeAdapterFactory = RuntimeTypeAdapterFactory.of(Transport.class, "type")
            .registerSubtype(Airplane.class, "airplane").registerSubtype(Helicopter.class, "helicopter").registerSubtype(SeaTransport.class, "sea")
            .registerSubtype(PassengerCar.class, "car").registerSubtype(Truck.class, "truck").registerSubtype(Trailer.class, "trailer");
    @Override
    public String getName() {
        return "JSON (*.json)";
    }

    @Override
    public String getExtension() {
        return "*.json";
    }

    @Override
    public void serialize(ArrayList<Transport> transport, String path) {
        Gson gson = new Gson().newBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
        Type type = new TypeToken<ArrayList<Transport>>(){}.getType();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
            writer.write(gson.toJson(transport, type));
        }
        catch(IOException e){
            new Alert(Alert.AlertType.ERROR, "An error occurred while saving the list to the file!").showAndWait();
        }
    }

    @Override
    public ArrayList<Transport> deserialize(String path) {
        Gson gson = new Gson().newBuilder().registerTypeAdapterFactory(typeAdapterFactory).create();
        Type type = new TypeToken<ArrayList<Transport>>(){}.getType();
        String contents = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            contents = reader.readLine();
        }
        catch(IOException e){
            new Alert(Alert.AlertType.ERROR, "An error occurred while reading the list from the file!").showAndWait();
        }
        return gson.fromJson(contents, type);
    }


}
