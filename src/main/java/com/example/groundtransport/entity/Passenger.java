package com.example.groundtransport.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Passenger name cannot be blank")
    private String name;

    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 120, message = "Age cannot exceed 120")
    private int age;

    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "male|female|other", message = "Gender must be 'male', 'female', or 'other'")
    private String gender;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid contact number")
    private String contactNumber;

    @Email(message = "Invalid email format")
    private String email;

    @ManyToMany(mappedBy = "passengers")
    private List<Booking> bookings;

    // Getters and Setters
}
