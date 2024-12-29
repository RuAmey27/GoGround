package com.example.groundtransport.services;

import com.example.groundtransport.entity.Booking;
import com.example.groundtransport.entity.Passenger;
import com.example.groundtransport.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;



@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    // Create a new Passenger
    public Passenger create(Passenger passenger) {
        validatePassenger(passenger);
        return passengerRepository.save(passenger);
    }

    // Retrieve a Passenger by ID
    public Optional<Passenger> findById(Long id) {
        return passengerRepository.findById(id);
    }

    // Update an existing Passenger
    public Passenger update(Long id, Passenger updatedPassenger) {
        validatePassenger(updatedPassenger);
        return passengerRepository.findById(id).map(passenger -> {
            passenger.setName(updatedPassenger.getName());
            passenger.setAge(updatedPassenger.getAge());
            passenger.setGender(updatedPassenger.getGender());
            passenger.setContactNumber(updatedPassenger.getContactNumber());
            passenger.setEmail(updatedPassenger.getEmail());
            return passengerRepository.save(passenger);
        }).orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));
    }

    // Delete a Passenger by ID
    public void delete(Long id) {
        if (passengerRepository.existsById(id)) {
            passengerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Passenger not found with id: " + id);
        }
    }

    // Retrieve all Passengers
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    // Retrieve all Bookings associated with a Passenger
    public List<Booking> getPassengerBookings(Long id) {
        return passengerRepository.findById(id)
                .map(Passenger::getBookings)
                .orElseThrow(() -> new RuntimeException("Passenger not found with id: " + id));
    }

    // Validate Passenger attributes
    private void validatePassenger(Passenger passenger) {
        if (passenger.getName() == null || passenger.getName().isBlank()) {
            throw new IllegalArgumentException("Passenger name cannot be blank");
        }
        if (passenger.getAge() < 0 || passenger.getAge() > 120) {
            throw new IllegalArgumentException("Age must be between 0 and 120");
        }
        if (!passenger.getGender().matches("male|female|other")) {
            throw new IllegalArgumentException("Gender must be 'male', 'female', or 'other'");
        }
        if (!passenger.getContactNumber().matches("\\+?[0-9]{10,15}")) {
            throw new IllegalArgumentException("Invalid contact number format");
        }
        if (passenger.getEmail() == null || !passenger.getEmail().matches(".+@.+\\..+")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
}
