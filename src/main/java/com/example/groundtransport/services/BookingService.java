package com.example.groundtransport.services;

import com.example.groundtransport.entity.Booking;
import com.example.groundtransport.entity.Route;
import com.example.groundtransport.entity.Trip;
import com.example.groundtransport.entity.User;
import com.example.groundtransport.repository.BookingRepository;
import com.example.groundtransport.repository.RouteRepository;
import com.example.groundtransport.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private BookingRepository bookingRepository;

    private Booking.BookingBuilder bookingBuilder = new Booking.BookingBuilder();

    public void setSourceAndDestination(String source, String destination) {
        bookingBuilder.setSource(source).setDestination(destination);
    }

    public List<Route> getAvailableRoutes() {
        return routeRepository.findBySourceAndDestination(bookingBuilder.getSource(), bookingBuilder.getDestination());
    }

    public void setRoute(Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));
        bookingBuilder.setRoute(route);
    }

    public void setDateAndTime(Date date, String time) {
        bookingBuilder.setDate(date).setTime(time);
    }

    public List<Trip> getAvailableTrips(String vehicleType) {
        bookingBuilder.setVehicleType(vehicleType);
        return tripRepository.findAvailableTrips(bookingBuilder.getRoute().getRouteID(), vehicleType);
    }

    public void setUserAndStatus(Long userId, String status) {
        User user = new User();
        user.setUserID(userId);
        bookingBuilder.setUser(user).setStatus(status);
    }

    public Booking finalizeBooking() {
        Booking booking = bookingBuilder.build();
        return bookingRepository.save(booking);
    }
}

