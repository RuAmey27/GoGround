package com.example.groundtransport.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Driver> drivers;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Route> routes;

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<Trip> trips;

    private Role role = Role.ADMIN;

    // Getters and Setters
}
