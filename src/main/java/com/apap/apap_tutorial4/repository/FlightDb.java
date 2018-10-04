package com.apap.apap_tutorial4.repository;

import com.apap.apap_tutorial4.model.FlightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDb extends JpaRepository<FlightModel, Long> {
    FlightModel findByFlightNumber(String flightNumber);
}