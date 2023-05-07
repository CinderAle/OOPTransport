package com.example.ooptransport.serializer;

import com.example.ooptransport.Transport;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class TextSerializer implements Serializer {
    private final String transportPackage = "com.example.ooptransport.transport.";
    private final String classNameDivider = "" + (char)1;
    private final String objectsDivider = "" + (char)2;
    private final String valueDivider = "" + (char)3;
    private final String fieldDivider = "" + (char)4;

    @Override
    public String getName() {
        return "Text";
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
        if(!line.substring(index).isEmpty())
            splits.add(line.substring(index));
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

    private void writeObjectToBufferedWriter(Object object, BufferedWriter writer) throws IOException, IllegalAccessException, InvocationTargetException {
        writer.write(classNameDivider + object.getClass().getSimpleName() + classNameDivider + "\r\n");
        HashMap<String, Method> getters = retrieveObjectGetters(object);
        for(String getterName : getters.keySet()) {
            String type = getters.get(getterName).getReturnType().getSimpleName();
            if(type.equals("int") || type.equals("double") || type.equals("boolean") || type.equals("String")) {
                writer.write(getterName + valueDivider + " " + getters.get(getterName).invoke(object) + fieldDivider + "\r\n");
            }
            else if(!type.contains("[]")) {
                writeObjectToBufferedWriter(getters.get(getterName).invoke(object), writer);
            }
            else {
                Object engines = getters.get(getterName).invoke(object);
                for(Object engine: (Object[])engines)
                    writeObjectToBufferedWriter(engine, writer);
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
            System.err.println("An error occurred while saving the list to the file!");
        }
    }

    private Transport setReflectionObjects(Object vehicle, ArrayList<Object> reflected, HashMap<String, Method> setters) throws IllegalAccessException, InvocationTargetException {
        if(vehicle.getClass().getSimpleName().equals("Airplane") || vehicle.getClass().getSimpleName().equals("Helicopter"))
            setters.get("Engines").invoke(vehicle, reflected.toArray());
        else
            for(Object object : reflected)
                setters.get(object.getClass().getSimpleName()).invoke(vehicle, object);
        return (Transport) vehicle;
    }

    private Transport objectLineToTransport(String line) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ArrayList<String> allFields = splitString(line, classNameDivider);
        ArrayList<Object> reflected = new ArrayList<>();
        Object instance = Class.forName(transportPackage + allFields.get(0).trim()).getConstructor().newInstance();
        HashMap<String, Method> setters = retrieveObjectSetters(instance);
        HashMap<String, Method> getters = retrieveObjectGetters(instance);
        int i = 1;
        int j = 0;
        ArrayList<String> instanceFields = splitString(allFields.get(1), fieldDivider);
        for(String setterName : setters.keySet()) {
            if(j < instanceFields.size() && i < allFields.size() && instanceFields.get(j).contains(valueDivider)) {
                String value = splitString(instanceFields.get(j), valueDivider).get(1).trim();
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
                }
                j++;
            }
            else if (i < allFields.size()) {
                i++;
                if(i < allFields.size() && !allFields.contains(fieldDivider)) {
                    Object reflectionInstance = Class.forName(transportPackage + allFields.get(i++)).getConstructor().newInstance();
                    HashMap<String, Method> subSetters = retrieveObjectSetters(reflectionInstance);
                    HashMap<String, Method> subGetters = retrieveObjectGetters(reflectionInstance);
                    ArrayList<String> reflectedFields = splitString(allFields.get(i), fieldDivider);
                    int k = 0;
                    for(String subSetName : subSetters.keySet()) {
                        if(k < reflectedFields.size()) {
                            String value = splitString(reflectedFields.get(k), valueDivider).get(1).trim();
                            String type = subGetters.get(subSetName).getReturnType().getSimpleName();
                            switch (type){
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
            else
                break;
        }
        return setReflectionObjects(instance, reflected, setters);
    }

    @Override
    public ArrayList<Transport> deserialize(String path) {
        ArrayList<Transport> transport = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder file = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null)
                file.append(line);
            ArrayList<String> vehicles = splitString(file.toString(), objectsDivider);
            for(String vehicle : vehicles)
                transport.add(objectLineToTransport(vehicle));
        }
        catch(Exception e){
            System.err.println("An error occurred while reading the list from the file!");
        }
        return transport;
    }
}