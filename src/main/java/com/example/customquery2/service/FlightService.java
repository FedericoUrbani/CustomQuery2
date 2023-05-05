package com.example.customquery2.service;

import com.example.customquery2.entites.Flight;
import com.example.customquery2.entites.FlightStatus;
import com.example.customquery2.repository.FlightRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

   public List<Flight> getDoubleStatus(FlightStatus cancelled, FlightStatus delayed){
       return flightRepository.findFlightByDoubleFlightStatus(cancelled,delayed);
   }

    public Page<Flight> getAllFlights(){
        Pageable pageable = PageRequest.of(0, 5, Sort.by("fromAirport").ascending());
        return  flightRepository.findAll(pageable);
    }

    public List<Flight> findByStatusOnTime() {

        return flightRepository.findByFlightStatus(FlightStatus.ON_TIME);
    }
    public List<Flight> findByStatusCancelled() {

        return flightRepository.findByFlightStatus(FlightStatus.CANCELLED);
    }
    public List<Flight> findByStatusDelayed() {

        return flightRepository.findByFlightStatus(FlightStatus.DELAYED);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    public String randomeStringGenerator(){

        int targetStringLength = 10;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for(int i=0;i<targetStringLength;i++){
            buffer.append((char) getRandomNumber(97,122));
        }
        String returnedString= buffer.toString();
        return returnedString;
    }


    public List<Flight> createFlightList(Optional<Integer> n){
        List<Flight> flightList = new ArrayList<>();

        for (int i=0;i<n.orElse(100);i++){
            Flight flight= new Flight();
            flight.setFlightStatus(FlightStatus.randomStatus());
            flight.setDescription(randomeStringGenerator());
            flight.setFromAirport(randomeStringGenerator());
            flight.setToAirport(randomeStringGenerator());
            flightRepository.save(flight);
            flightList.add(flight);
        }

        return flightList;
    }

}
