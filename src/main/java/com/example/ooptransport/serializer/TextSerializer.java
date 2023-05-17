package com.example.ooptransport.serializer;

import com.example.ooptransport.Transport;
import com.example.ooptransport.transport.GroundTransport;
import javafx.scene.control.Alert;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TextSerializer implements Serializer {
    private final String transportPackage = "com.example.ooptransport.transport.";
    private final String classNamePreDivider = "@";
    private final String classNameDivider = classNamePreDivider + '\r';
    private final String objectsDivider = ",\r";
    private final String valueDivider = ":";
    private final String fieldDivider = ";\r";

    @Override
    public String getName() {
        return "Text (*.txt)";
    }

    @Override
    public String getExtension() {
        return "*.txt";
    }

    private ArrayList<String> splitString(String line, String divider) {
        ArrayList<String> splits = new ArrayList<>();
        if(!line.contains(divider) || divider.length() == 0) {
            splits.add(line);
            return splits;
        }
        int index = 0;
        int dividerIndex = line.indexOf(divider);
        while(dividerIndex >= 0){
            if(!line.substring(index, dividerIndex).isEmpty())
                splits.add(line.substring(index, dividerIndex));
            index = dividerIndex + divider.length();
            dividerIndex = line.indexOf(divider, dividerIndex + 1);
        }
        if(!line.substring(index).trim().isEmpty())
            splits.add(line.substring(index));
        return splits;
    }

    private ArrayList<String> splitByObjectName(String line) {
        String temp = "";
        ArrayList<String> splits = new ArrayList<>();
        int nameEnd = line.indexOf(classNameDivider);
        int prevEnd = 0;
        int prevDivider = 0;
        int currDivider = line.indexOf(classNamePreDivider);
        while(nameEnd >= 0) {
            while(currDivider != nameEnd){
                prevDivider = currDivider;
                currDivider = line.indexOf(classNamePreDivider, currDivider + 1);
            }
            if(prevEnd > 0 && prevEnd != prevDivider)
                temp = line.substring(prevEnd + classNameDivider.length(), prevDivider);
            else if(prevEnd > 0)
                temp = "";
            else
                temp = line.substring(0, prevDivider).trim();
            if(!temp.isEmpty())
                splits.add(temp);
            temp = line.substring(prevDivider + 1, nameEnd);
            if(!temp.isEmpty())
                splits.add(temp);
            prevEnd = nameEnd;
            nameEnd = line.indexOf(classNameDivider, nameEnd + 1);
        }
        temp = line.substring(prevEnd + classNameDivider.length()).trim();
        if(!temp.isEmpty())
            splits.add(temp);
        return splits;
    }

    private HashMap<String, Method> sortMap(HashMap<String, Method> unsorted) {
        HashMap<String, Method> sorted = new HashMap<>();
        SortedSet<String> names = new TreeSet<>(unsorted.keySet());
        for(String name : names)
            sorted.put(name, unsorted.get(name));
        return sorted;
    }

    private HashMap<String, Method> retrieveObjectGetters(Object object) {
        HashMap<String, Method> getters = new HashMap<>();
        for(Method method : object.getClass().getMethods())
            if(method.getName().startsWith("get") && !method.getName().equals("getClass"))
                getters.put(method.getName().substring(3), method);
        return sortMap(getters);
    }

    private HashMap<String, Method> retrieveObjectSetters(Object object) {
        HashMap<String, Method> setters = new HashMap<>();
        for(Method method : object.getClass().getMethods())
            if(method.getName().startsWith("set") && !method.getName().equals("getClass"))
                setters.put(method.getName().substring(3), method);
        return sortMap(setters);
    }

    private void printHashMap(HashMap<String, Method> methods) {
        for(String method : methods.keySet())
            System.out.println(method + " - " + methods.get(method));
    }

    private String cutSpecialSymbols(String line) {
        String cutLine = "";
        for(int i = 0;i < line.length();i++) {
            if(line.charAt(i) == '\r') {
                cutLine += '\n';
                if(i + 1 < line.length() && line.charAt(i + 1) == '\n')
                    i++;
            }
            else
                cutLine += line.charAt(i);
        }
        return cutLine;
    }

    private String getSecondApperanace(String line, String divider) {
        ArrayList<String> parts = splitString(line, divider);
        if(parts.size() > 1){
            String glue = "";
            for(int i = 1;i < parts.size();i++)
                glue = glue.concat(parts.get(i));
            return glue;
        }
        else
            return null;
    }

    private boolean checkType(String type) {
        String[] usedTypes = {"int", "double", "boolean", "String", "wheelDriveTypes", "gearboxTypes", "bodyTypes"};
        return Arrays.asList(usedTypes).contains(type);
    }

    private void writeObjectToBufferedWriter(Object object, BufferedWriter writer) throws IOException, IllegalAccessException, InvocationTargetException {
        writer.write(classNamePreDivider + object.getClass().getSimpleName() + classNameDivider + "\n");
        HashMap<String, Method> getters = retrieveObjectGetters(object);
        for(String getterName : getters.keySet()) {
            String type = getters.get(getterName).getReturnType().getSimpleName();
            if(checkType(type)) {
                writer.write(getterName + valueDivider + " " + cutSpecialSymbols(getters.get(getterName).invoke(object).toString()) + fieldDivider + "\r\n");
            }
            else if(!type.contains("[]")) {
                Object reflected = getters.get(getterName).invoke(object);
                if(reflected != null)
                    writeObjectToBufferedWriter(reflected, writer);
            }
            else {
                Object engines = getters.get(getterName).invoke(object);
                for(Object engine: (Object[])engines) {
                    writeObjectToBufferedWriter(engine, writer);
                }
            }
        }
        if(!object.getClass().getSimpleName().equals("Engine") && !object.getClass().getSimpleName().equals("Trailer"))
            writer.write(objectsDivider + "\r\n");
        else
            writer.write(classNameDivider + "\r\n");
    }

    @Override
    public void serialize(ArrayList<Transport> transport, String path) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for(Transport vehicle : transport) {
                writeObjectToBufferedWriter(vehicle, writer);
            }
            writer.close();
        }
        catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "An error occurred while saving the list to the file!").showAndWait();
        }
    }

    private Transport setReflectionObjects(Object vehicle, ArrayList<Object> reflected, HashMap<String, Method> setters) throws IllegalAccessException, InvocationTargetException {
        if(vehicle.getClass().getSimpleName().equals("Airplane") || vehicle.getClass().getSimpleName().equals("Helicopter")) {
            setters.get("Engines").invoke(vehicle, reflected);
        }
        else {
            for (Object object : reflected)
                setters.get(object.getClass().getSimpleName()).invoke(vehicle, object);
        }
        return (Transport) vehicle;
    }

    private Transport objectLineToTransport(String line) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ArrayList<String> allFields = splitByObjectName(line);
        ArrayList<Object> reflected = new ArrayList<>();
        Object instance = Class.forName(transportPackage + allFields.get(0).trim()).getConstructor().newInstance();
        HashMap<String, Method> setters = retrieveObjectSetters(instance);
        HashMap<String, Method> getters = retrieveObjectGetters(instance);
        int i = 1;
        int j = 0;
        ArrayList<String> instanceFields = splitString(allFields.get(1), fieldDivider);
        for(String setterName : setters.keySet()) {
            if(j < instanceFields.size() && i < allFields.size() && instanceFields.get(j).contains(valueDivider)) {
                String value = getSecondApperanace(instanceFields.get(j), valueDivider);
                if(value != null)
                    value = value.trim();
                else
                    value = "";
                String type = getters.get(setterName).getReturnType().getSimpleName();
                switch (type) {
                    case "int":
                        setters.get(setterName).invoke(instance, Integer.parseInt(value));
                        break;
                    case "double":
                        setters.get(setterName).invoke(instance, Double.parseDouble(value));
                        break;
                    case "boolean":
                        setters.get(setterName).invoke(instance, Boolean.parseBoolean(value));
                        break;
                    case "String":
                        setters.get(setterName).invoke(instance, value);
                        break;
                    default:
                        Class<?> unknownType = getters.get(setterName).getReturnType();
                        if(unknownType.isEnum()) {
                            Enum<?> enumValue = Enum.valueOf((Class<? extends Enum>) unknownType, value);
                            setters.get(setterName).invoke(instance, enumValue);
                        }
                        else
                            j--;
                }
                j++;
            }
            else if (i < allFields.size()) {
                if(allFields.get(i).contains(valueDivider))
                    i++;
                if(i < allFields.size() && !allFields.contains(fieldDivider)) {
                    Object reflectionInstance = Class.forName(transportPackage + allFields.get(i++)).getConstructor().newInstance();
                    HashMap<String, Method> subSetters = retrieveObjectSetters(reflectionInstance);
                    HashMap<String, Method> subGetters = retrieveObjectGetters(reflectionInstance);
                    ArrayList<String> reflectedFields = splitString(allFields.get(i), fieldDivider);
                    int k = 0;
                    for(String subSetName : subSetters.keySet()) {
                        if(k < reflectedFields.size()) {
                            String value = getSecondApperanace(reflectedFields.get(k), valueDivider).trim();
                            String type = subGetters.get(subSetName).getReturnType().getSimpleName();
                            switch (type) {
                                case "int":
                                    subSetters.get(subSetName).invoke(reflectionInstance, Integer.parseInt(value));
                                    break;
                                case "double":
                                    subSetters.get(subSetName).invoke(reflectionInstance, Double.parseDouble(value));
                                    break;
                                case "boolean":
                                    subSetters.get(subSetName).invoke(reflectionInstance, Boolean.parseBoolean(value));
                                    break;
                                case "String":
                                    subSetters.get(subSetName).invoke(reflectionInstance, value);
                                    break;
                            }
                            k++;
                        }
                    }
                    if(i < allFields.size())
                        instanceFields = splitString(allFields.get(i), fieldDivider);
                    j = 0;
                    reflected.add(reflectionInstance);
                }
            }
        }
        return setReflectionObjects(instance, reflected, setters);
    }

    @Override
    public ArrayList<Transport> deserialize(String path) {
        ArrayList<Transport> transport = new ArrayList<>();
        try {
            String fileAll = Files.readString(Paths.get(path));
            ArrayList<String> vehicles = splitString(fileAll, objectsDivider);
            for(String vehicle : vehicles)
                transport.add(objectLineToTransport(vehicle));
        }
        catch(Exception e){
            new Alert(Alert.AlertType.ERROR, "An error occurred while reading the list from the file!").showAndWait();
        }
        return transport;
    }
}