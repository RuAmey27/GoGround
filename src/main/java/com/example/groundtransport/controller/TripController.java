package com.example.groundtransport.controller;


import com.example.groundtransport.entity.Trip;
import com.example.groundtransport.services.TripService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trip")
@Validated
public class TripController {

    private final TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<Trip> createTrip(@Valid @RequestBody Trip trip) {
        Trip createdTrip = tripService.create(trip);
        EntityModel<Trip> resource = EntityModel.of(createdTrip);
        WebMvcLinkBuilder linkToTrips = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getAllTrips());
        resource.add(linkToTrips.withRel("all-trips"));
        return resource;
    }

    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.findAll();
    }

    @GetMapping("/{id}")
    public EntityModel<Trip> getTripById(@PathVariable Long id) {
        Optional<Trip> trip = tripService.findById(id);
        if (trip.isEmpty()) {
            throw new ResourceNotFoundException("Trip not found with ID: " + id);
        }
        EntityModel<Trip> resource = EntityModel.of(trip.get());
        WebMvcLinkBuilder linkToTrips = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getAllTrips());
        resource.add(linkToTrips.withRel("all-trips"));
        return resource;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public EntityModel<Trip> updateTrip(@PathVariable Long id, @RequestBody Trip updatedFields) {
        Trip existingTrip = tripService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with ID: " + id));

        // Restrict updates to only the status field
        if (updatedFields.getStatus() != null) {
            existingTrip.setStatus(updatedFields.getStatus());
        }

        Trip updatedTrip = tripService.update(id, existingTrip);
        EntityModel<Trip> resource = EntityModel.of(updatedTrip);
        WebMvcLinkBuilder linkToTrips = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getAllTrips());
        resource.add(linkToTrips.withRel("all-trips"));
        return resource;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable Long id) {
        tripService.delete(id);
    }
}
