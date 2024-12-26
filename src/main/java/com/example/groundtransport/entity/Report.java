package com.example.groundtransport.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportID;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
    private List<Booking> bookings;

    private String generatedReport;

    // Getters and Setters
}
