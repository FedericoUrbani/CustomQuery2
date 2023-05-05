package com.example.customquery2.controller;

import com.example.customquery2.entites.Flight;
import com.example.customquery2.entites.FlightStatus;
import com.example.customquery2.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("")
    @ResponseBody
    public List provisioning(@RequestParam Optional<Integer> n){
        n.orElse(100);
        return flightService.createFlightList(n);
    }

    @GetMapping("/findallasc")
    public Page<Flight> findAllFlights(){
        return flightService.getAllFlights();
    }

    @GetMapping("/findallonetime")
    public List findByOnTime(){
        return flightService.findByStatusOnTime();
    }

    @GetMapping("/findallcanc")
    public List findByCancelled(){
        return flightService.findByStatusCancelled();
    }

    @GetMapping("/findalldelayed")
    public List findByDeleyed(){
        return flightService.findByStatusDelayed();
    }

    @GetMapping("/findallcancelled&delayed")
    public List findAllCancelledAndDeleyed(@RequestParam(name="p1") FlightStatus p1,
                                           @RequestParam(name="p2") FlightStatus p2){
        return flightService.getDoubleStatus(p1,p2);
    }



}