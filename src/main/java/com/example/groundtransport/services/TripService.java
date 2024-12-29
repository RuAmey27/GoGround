package com.example.groundtransport.services;

import com.example.groundtransport.entity.Trip;
import com.example.groundtransport.repository.TripRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip create(Trip trip) {
        validateTrip(trip);
        return tripRepository.save(trip);
    }

    public List<Trip> findAll() {
        return tripRepository.findAll();
    }

    public Optional<Trip> findById(Long id) {
        return tripRepository.findById(id);
    }

    public Trip update(Long id, Trip trip) {
        if (!tripRepository.existsById(id)) {
            throw new RuntimeException("Trip not found with id: " + id);
        }
        return tripRepository.save(trip);
    }

    public void delete(Long id) {
        if (!tripRepository.existsById(id)) {
            throw new RuntimeException("Trip not found with id: " + id);
        }
        tripRepository.deleteById(id);
    }

    private void validateTrip(Trip trip) {
        if (trip.getRoute() == null) {
            throw new IllegalArgumentException("Route cannot be null");
        }
        if (trip.getVehicle() == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (trip.getDriver() == null) {
            throw new IllegalArgumentException("Driver cannot be null");
        }
        if (trip.getStatus() == null || trip.getStatus().isBlank()) {
            throw new IllegalArgumentException("Status cannot be null or blank");
        }
    }
}
