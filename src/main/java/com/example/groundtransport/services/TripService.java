package com.example.groundtransport.services;

import com.example.groundtransport.entity.Trip;
import com.example.groundtransport.repository.TripRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TripService extends BaseService<Trip, Long> {
    public TripService(TripRepository repository) {
        super(repository);
    }
}
