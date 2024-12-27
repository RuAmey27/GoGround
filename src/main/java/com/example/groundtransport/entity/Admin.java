package com.example.groundtransport.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import jakarta.validation.constraints.*;


@Getter
@Setter
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
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
}
