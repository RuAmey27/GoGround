package com.example.groundtransport.services;

import com.example.groundtransport.entity.Driver;
import com.example.groundtransport.repository.DriverRepository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    // Create a new Driver
    public Driver create(Driver driver) {
        return driverRepository.save(driver);
    }

    // Retrieve all Drivers
    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    // Retrieve a Driver by ID
    public Optional<Driver> findById(Long id) {
        return driverRepository.findById(id);
    }

    // Update an existing Driver
    public Driver update(Long id, Driver updatedDriver) {
        return driverRepository.findById(id).map(driver -> {
            driver.setName(updatedDriver.getName());
            driver.setLicenseNumber(updatedDriver.getLicenseNumber());
            return driverRepository.save(driver);
        }).orElseThrow(() -> new RuntimeException("Driver not found with id: " + id));
    }

    // Delete a Driver by ID
    public void delete(Long id) {
        if (driverRepository.existsById(id)) {
            driverRepository.deleteById(id);
        } else {
            throw new RuntimeException("Driver not found with id: " + id);
        }
    }
}

