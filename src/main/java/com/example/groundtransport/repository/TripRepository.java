package com.example.groundtransport.repository;

import com.example.groundtransport.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends BaseRepository<Trip, Long> {
}
