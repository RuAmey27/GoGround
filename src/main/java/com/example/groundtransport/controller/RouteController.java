package com.example.groundtransport.controller;


import com.example.groundtransport.entity.Route;
import com.example.groundtransport.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/route")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        return routeService.create(route);
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Route> getRouteById(@PathVariable Long id) {
        return routeService.findById(id);
    }

    @PutMapping("/{id}")
    public Route updateRoute(@PathVariable Long id, @RequestBody Route route) {
        return routeService.update(id, route);
    }

    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable Long id) {
        routeService.delete(id);
    }
}

