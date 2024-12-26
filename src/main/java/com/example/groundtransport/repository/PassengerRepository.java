package com.example.groundtransport.repository;

import com.example.groundtransport.entity.Passenger;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends BaseRepository<Passenger, Long> { }
