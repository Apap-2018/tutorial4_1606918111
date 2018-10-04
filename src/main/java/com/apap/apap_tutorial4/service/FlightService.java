package com.apap.apap_tutorial4.service;

import com.apap.apap_tutorial4.model.FlightModel;

public interface FlightService {
    FlightModel getFlightDetailByFlightNumber(String flightNumber);
    
    void addFlight(FlightModel flight);
    void updateFlight(FlightModel flight);
    boolean removeFlight(String flightNumber);
}