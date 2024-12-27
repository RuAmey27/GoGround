package com.example.groundtransport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverID;

    @NotBlank(message = "Driver name cannot be blank")
    private String name;

    @NotBlank(message = "License number cannot be blank")
    @Size(min = 5, max = 15, message = "License number must be between 5 and 15 characters")
    private String licenseNumber;

    @ElementCollection
    private Map<String, @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid contact number") String> contactDetails;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    // Getters and Setters
}
