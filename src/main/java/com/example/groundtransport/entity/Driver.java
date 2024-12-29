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
@Table(name = "driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long driverID;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Driver name cannot be blank")
    private String name;

    @Column(name = "license_number", nullable = false)
    @NotBlank(message = "License number cannot be blank")
    @Size(min = 5, max = 15, message = "License number must be between 5 and 15 characters")
    private String licenseNumber;

    @ElementCollection
    @CollectionTable(name = "driver_contact_details", joinColumns = @JoinColumn(name = "driver_id"))
    @MapKeyColumn(name = "contact_type")
    @Column(name = "contact_detail", nullable = false)
    private Map<String, String> contactDetails;

    @ManyToOne(optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;
}
