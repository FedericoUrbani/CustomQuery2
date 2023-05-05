package com.example.customquery2.repository;

import com.example.customquery2.entites.Flight;
import com.example.customquery2.entites.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;
import java.util.List;

@RepositoryRestResource
public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByFlightStatus(FlightStatus flightStatus);

    @Query("SELECT f FROM Flight f WHERE f.flightStatus = :status1 OR f.flightStatus = :status2")
    List<Flight> findFlightByDoubleFlightStatus(@Param("status1") FlightStatus cancelled,@Param("status2") FlightStatus delayed);


}

