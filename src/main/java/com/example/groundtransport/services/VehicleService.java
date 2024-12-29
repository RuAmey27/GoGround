package com.example.groundtransport.services;

import com.example.groundtransport.entity.Vehicle;
import com.example.groundtransport.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle update(Long id, Vehicle updatedVehicle) {
        Vehicle existingVehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + id));

        existingVehicle.setName(updatedVehicle.getName());
        existingVehicle.setType(updatedVehicle.getType());
        existingVehicle.setCapacity(updatedVehicle.getCapacity());
        existingVehicle.setStatus(updatedVehicle.getStatus());
        existingVehicle.setPerKmPrice(updatedVehicle.getPerKmPrice());

        return vehicleRepository.save(existingVehicle);
    }

    public void delete(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with ID: " + id));
        vehicleRepository.delete(vehicle);
    }
}

