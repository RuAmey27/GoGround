package com.example.groundtransport.controller;



import com.example.groundtransport.entity.Role;
import com.example.groundtransport.entity.Vehicle;
import com.example.groundtransport.services.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/vehicle")
@Validated
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Vehicle>> createVehicle(@RequestBody @Validated Vehicle vehicle,
                                                              @RequestParam Role role) {
        if (!role.equals(Role.ADMIN)) {
            return ResponseEntity.status(403).build(); // Forbidden for non-admins
        }

        Vehicle createdVehicle = vehicleService.create(vehicle);
        EntityModel<Vehicle> entityModel = EntityModel.of(createdVehicle,
                linkTo(methodOn(VehicleController.class).getVehicleById(createdVehicle.getVehicleID())).withSelfRel(),
                linkTo(methodOn(VehicleController.class).getAllVehicles()).withRel("vehicles"));

        return ResponseEntity.created(linkTo(methodOn(VehicleController.class).getVehicleById(createdVehicle.getVehicleID())).toUri())
                .body(entityModel);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Vehicle>>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.findAll();
        List<EntityModel<Vehicle>> vehicleModels = vehicles.stream()
                .map(vehicle -> EntityModel.of(vehicle,
                        linkTo(methodOn(VehicleController.class).getVehicleById(vehicle.getVehicleID())).withSelfRel()))
                .toList();

        return ResponseEntity.ok(vehicleModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Vehicle>> getVehicleById(@PathVariable Long id) {
        Optional<Vehicle> vehicle = vehicleService.findById(id);
        if (vehicle.isPresent()) {
            EntityModel<Vehicle> entityModel = EntityModel.of(vehicle.get(),
                    linkTo(methodOn(VehicleController.class).getVehicleById(id)).withSelfRel(),
                    linkTo(methodOn(VehicleController.class).getAllVehicles()).withRel("vehicles"));

            return ResponseEntity.ok(entityModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Vehicle>> updateVehicle(@PathVariable Long id,
                                                              @RequestBody Vehicle updatedVehicle,
                                                              @RequestParam Role role) {
//        if (!role.equals(Role.ADMIN)) {
//            return ResponseEntity.status(403).build(); // Forbidden for non-admins
//        }

        // Restrict updates to specific fields
        Optional<Vehicle> existingVehicleOpt = vehicleService.findById(id);
        if (existingVehicleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Vehicle existingVehicle = existingVehicleOpt.get();
        existingVehicle.setName(updatedVehicle.getName());
        existingVehicle.setType(updatedVehicle.getType());
        existingVehicle.setCapacity(updatedVehicle.getCapacity());
        existingVehicle.setStatus(updatedVehicle.getStatus());
        existingVehicle.setPerKmPrice(updatedVehicle.getPerKmPrice());

        Vehicle updated = vehicleService.update(id, existingVehicle);

        EntityModel<Vehicle> entityModel = EntityModel.of(updated,
                linkTo(methodOn(VehicleController.class).getVehicleById(id)).withSelfRel(),
                linkTo(methodOn(VehicleController.class).getAllVehicles()).withRel("vehicles"));

        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id, @RequestParam Role role) {
//        if (!role.equals(Role.ADMIN)) {
//            return ResponseEntity.status(403).build(); // Forbidden for non-admins
//        }
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
