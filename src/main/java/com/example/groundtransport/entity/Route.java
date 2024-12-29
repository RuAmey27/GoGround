package com.example.groundtransport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "route")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long routeID;

    @Column(name = "source", nullable = false)
    @NotBlank(message = "Source cannot be blank")
    private String source;

    @Column(name = "destination", nullable = false)
    @NotBlank(message = "Destination cannot be blank")
    private String destination;

    @ManyToOne(optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Trip> trips;
}
