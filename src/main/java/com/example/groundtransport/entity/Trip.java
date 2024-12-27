package com.example.groundtransport.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tripID;

    @ManyToOne(optional = false) // Ensures a Trip must have a Route
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne(optional = false) // Ensures a Trip must have a Vehicle
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(optional = false) // Ensures a Trip must have a Driver
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @Column(nullable = false)
    private String status; // scheduled | ongoing | completed | canceled

    public void setStatus(String status) {
        if (!isValidStatus(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        this.status = status;
    }

    private boolean isValidStatus(String status) {
        return status.equals("scheduled") || status.equals("ongoing") ||
                status.equals("completed") || status.equals("canceled");
    }
}


