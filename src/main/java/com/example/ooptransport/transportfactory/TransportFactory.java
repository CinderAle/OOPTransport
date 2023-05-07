package com.example.ooptransport.transportfactory;

import com.example.ooptransport.Transport;

public class TransportFactory {
    private Transport transportType;
    private String transportTypeName;
    public TransportFactory(String transportTypeName, Transport transportType) {
        this.transportType = transportType;
        this.transportTypeName = transportTypeName;
    }
    public String getTransportTypeName() {
        return this.transportTypeName;
    }
    public Transport getTransportType() {
        return this.transportType;
    }
}