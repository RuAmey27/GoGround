package com.example.groundtransport.controller;


import com.example.groundtransport.entity.Trip;
import com.example.groundtransport.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        return tripService.create(trip);
    }

    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Trip> getTripById(@PathVariable Long id) {
        return tripService.findById(id);
    }

    @PutMapping("/{id}")
    public Trip updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        return tripService.update(id, trip);
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id) {
        tripService.delete(id);
    }
}
