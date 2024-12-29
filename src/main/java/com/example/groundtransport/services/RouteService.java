package com.example.groundtransport.services;

import com.example.groundtransport.entity.Route;
import com.example.groundtransport.repository.RouteRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    @Autowired
    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    // Create a new Route
    public Route create(Route route) {
        validateRoute(route);
        return routeRepository.save(route);
    }

    // Retrieve all Routes
    public List<Route> findAll() {
        return routeRepository.findAll();
    }

    // Retrieve a Route by ID
    public Optional<Route> findById(Long id) {
        return routeRepository.findById(id);
    }

    // Update an existing Route
    public Route update(Long id, Route updatedRoute) {
        validateRoute(updatedRoute);
        return routeRepository.findById(id).map(route -> {
            route.setSource(updatedRoute.getSource());
            route.setDestination(updatedRoute.getDestination());
            route.setAdmin(updatedRoute.getAdmin());
            return routeRepository.save(route);
        }).orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
    }

    // Delete a Route by ID
    public void delete(Long id) {
        if (routeRepository.existsById(id)) {
            routeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Route not found with id: " + id);
        }
    }

    // Validate Route attributes
    private void validateRoute(Route route) {
        if (route.getSource() == null || route.getSource().isBlank()) {
            throw new IllegalArgumentException("Source cannot be blank");
        }
        if (route.getDestination() == null || route.getDestination().isBlank()) {
            throw new IllegalArgumentException("Destination cannot be blank");
        }
        if (route.getAdmin() == null) {
            throw new IllegalArgumentException("Admin cannot be null");
        }
    }



}
