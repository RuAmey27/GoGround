package com.example.groundtransport.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PaymentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentID;

    private double amount;
    private String method; // credit_card | debit_card | UPI

    @OneToOne(mappedBy = "paymentDetails")
    private Booking booking;

    // Getters and Setters
}
