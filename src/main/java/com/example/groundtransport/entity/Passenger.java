package com.example.groundtransport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "passenger")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
    private Long passengerID;

    @Column(name = "passenger_name", nullable = false)
    @NotBlank(message = "Passenger name cannot be blank")
    private String name;

    @Column(name = "passenger_age", nullable = false)
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 120, message = "Age cannot exceed 120")
    private int age;

    @Column(name = "passenger_gender", nullable = false)
    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "male|female|other", message = "Gender must be 'male', 'female', or 'other'")
    private String gender;

    @Column(name = "contact_number", nullable = false)
    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid contact number")
    private String contactNumber;

    @Column(name = "email", nullable = false)
    @Email(message = "Invalid email format")
    private String email;

    @ManyToMany(mappedBy = "passengers")
    private List<Booking> bookings;
}
