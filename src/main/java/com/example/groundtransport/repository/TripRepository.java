package com.example.groundtransport.repository;

import com.example.groundtransport.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    @Query("SELECT t FROM Trip t WHERE t.route.routeID = :routeId AND t.vehicle.type = :vehicleType AND t.status = 'available'")
    List<Trip> findAvailableTrips(@Param("routeId") Long routeId, @Param("vehicleType") String vehicleType);
}