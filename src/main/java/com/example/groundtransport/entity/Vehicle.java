package com.example.groundtransport.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleID;

    private String name;
    private String type; // bus | cab
    private int capacity;
    private String status; // available | in_use | maintenance
    private double perKmPrice;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    // Getters and Setters
}
