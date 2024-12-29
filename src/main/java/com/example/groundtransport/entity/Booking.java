package com.example.groundtransport.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import static com.example.groundtransport.entity.BookingBuilder.getBooking;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingID;

    @Column(name = "source", nullable = false)
    private String source;

    @Column(name = "destination", nullable = false)
    private String destination;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "time", nullable = false)
    private String time;

    @Column(name = "vehicle_type", nullable = false)
    private String vehicleType;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "booking_passenger",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id")
    )
    private List<Passenger> passengers;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private PaymentDetails paymentDetails;

    public static class BookingBuilder {
        private String source;
        private String destination;
        private Date date;
        private String time;
        private String vehicleType;
        private String status;
        private Route route;
        private User user;

        public BookingBuilder setSource(String source) {
            this.source = source;
            return this;
        }

        public BookingBuilder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public BookingBuilder setDate(Date date) {
            this.date = date;
            return this;
        }

        public BookingBuilder setTime(String time) {
            this.time = time;
            return this;
        }

        public BookingBuilder setVehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public BookingBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public BookingBuilder setRoute(Route route) {
            this.route = route;
            return this;
        }

        public BookingBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public Booking build() {
            return getBooking(source, destination, date, time, vehicleType, route, user, status);
        }

        public Route getRoute() {
            return this.route;
        }

        public String getSource() {
            return this.source;
        }
        public String getDestination() {
            return this.destination;
        }
        public Date getDate() {
            return this.date;
        }
        public String getTime() {
            return this.time;
        }
    }
}
