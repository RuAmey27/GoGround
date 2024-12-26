package com.example.groundtransport.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerID;

    private String name;
    private int age;
    private String gender; // male | female | other
    private String contactNumber;
    private String email;

    @ManyToMany(mappedBy = "passengers")
    private List<Booking> bookings;

    // Getters and Setters
}
