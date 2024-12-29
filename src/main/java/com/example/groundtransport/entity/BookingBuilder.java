package com.example.groundtransport.entity;

import java.util.Date;

public class BookingBuilder {
    private String source;
    private String destination;
    private Date date;
    private String time;
    private String vehicleType;
    private String status;
    private Route route;
    private User user;

    public BookingBuilder setSource(String source) {
        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("Source cannot be null or empty");
        }
        this.source = source;
        return this;
    }

    public BookingBuilder setDestination(String destination) {
        if (destination == null || destination.isEmpty()) {
            throw new IllegalArgumentException("Destination cannot be null or empty");
        }
        if (destination.equalsIgnoreCase(source)) {
            throw new IllegalArgumentException("Source and destination cannot be the same");
        }
        this.destination = destination;
        return this;
    }

    public BookingBuilder setDate(Date date) {
        if (date == null || date.before(new Date())) {
            throw new IllegalArgumentException("Date cannot be in the past");
        }
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

    static Booking getBooking(String source, String destination, Date date, String time, String vehicleType, Route route, User user, String status) {
        if (source == null || destination == null || date == null || time == null || vehicleType == null || route == null || user == null) {
            throw new IllegalStateException("All required fields must be set before building the Booking");
        }

        Booking booking = new Booking();
        booking.setSource(source);
        booking.setDestination(destination);
        booking.setDate(date);
        booking.setTime(time);
        booking.setVehicleType(vehicleType);
        booking.setStatus(status);
        booking.setRoute(route);
        booking.setUser(user);

        return booking;
    }
}

