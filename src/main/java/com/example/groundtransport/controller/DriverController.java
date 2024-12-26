package com.example.groundtransport.controller;



import com.example.groundtransport.entity.Driver;

import com.example.groundtransport.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/driver")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public Driver createDriver(@RequestBody Driver driver) {
        return driverService.create(driver);
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return driverService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Driver> getDriverById(@PathVariable Long id) {
        return driverService.findById(id);
    }

    @PutMapping("/{id}")
    public Driver updateDriver(@PathVariable Long id, @RequestBody Driver driver) {
        return driverService.update(id, driver);
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Long id) {
        driverService.delete(id);
    }
}
