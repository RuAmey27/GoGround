package com.example.groundtransport.controller;

import com.example.groundtransport.entity.Booking;
import com.example.groundtransport.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/step1")
    public ResponseEntity<?> step1(@RequestBody Map<String, String> input) {
        String source = input.get("source");
        String destination = input.get("destination");
        bookingService.setSourceAndDestination(source, destination);
        return ResponseEntity.ok(Map.of(
                "message", "Step 1 completed. Available routes fetched.",
                "nextStep", "/api/bookings/availableRoutes"
        ));
    }

    @GetMapping("/availableRoutes")
    public ResponseEntity<?> getAvailableRoutes() {
        return ResponseEntity.ok(Map.of(
                "routes", bookingService.getAvailableRoutes(),
                "nextStep", "/api/bookings/step2"
        ));
    }

    @PostMapping("/step2")
    public ResponseEntity<?> step2(@RequestBody Map<String, Long> input) {
        Long routeId = input.get("routeId");
        bookingService.setRoute(routeId);
        return ResponseEntity.ok(Map.of(
                "message", "Step 2 completed. Route selected.",
                "nextStep", "/api/bookings/step3"
        ));
    }

    @PostMapping("/step3")
    public ResponseEntity<?> step3(@RequestBody Map<String, Object> input) {
        Date date = new Date((String) input.get("date"));
        String time = input.get("time").toString();
        bookingService.setDateAndTime(date, time);
        return ResponseEntity.ok(Map.of(
                "message", "Step 3 completed. Date and time set.",
                "nextStep", "/api/bookings/availableTrips"
        ));
    }

    @GetMapping("/availableTrips")
    public ResponseEntity<?> getAvailableTrips(@RequestParam String vehicleType) {
        return ResponseEntity.ok(Map.of(
                "trips", bookingService.getAvailableTrips(vehicleType),
                "nextStep", "/api/bookings/step4"
        ));
    }

    @PostMapping("/step4")
    public ResponseEntity<?> step4(@RequestBody Map<String, Object> input) {
        Long userId = Long.parseLong(input.get("userId").toString());
        String status = input.get("status").toString();
        bookingService.setUserAndStatus(userId, status);
        return ResponseEntity.ok(Map.of(
                "message", "Step 4 completed. User and status set.",
                "nextStep", "/api/bookings/finalize"
        ));
    }

    @PostMapping("/finalize")
    public ResponseEntity<?> finalizeBooking() {
        Booking booking = bookingService.finalizeBooking();
        return ResponseEntity.ok(Map.of(
                "message", "Booking finalized successfully.",
                "booking", booking
        ));
    }
}

