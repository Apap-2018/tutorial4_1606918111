package com.apap.apap_tutorial4.service;

import com.apap.apap_tutorial4.model.FlightModel;
import com.apap.apap_tutorial4.repository.FlightDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDb flightDb;

    @Override
    public void addFlight(FlightModel flight) {
        flightDb.save(flight);
	}

	@Override
	public FlightModel getFlightDetailByFlightNumber(String flightNumber) {
		return flightDb.findByFlightNumber(flightNumber);
    }

    @Override
    public void updateFlight(FlightModel flight) {
        FlightModel findFlight = flightDb.findByFlightNumber(flight.getFlightNumber());
        findFlight.setDestination(flight.getDestination());
        findFlight.setOrigin(flight.getOrigin());
        findFlight.setTime(flight.getTime());

    }

    @Override
    public boolean removeFlight(String flightNumber) {
        flightDb.delete(this.getFlightDetailByFlightNumber(flightNumber));
		return this.getFlightDetailByFlightNumber(flightNumber) == null;
        
    }
    
    
}