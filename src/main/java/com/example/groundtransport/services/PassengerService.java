package com.example.groundtransport.services;

import com.example.groundtransport.entity.Passenger;
import com.example.groundtransport.repository.PassengerRepository;
import org.springframework.stereotype.Service;

@Service
public class PassengerService extends BaseService<Passenger, Long> {
    public PassengerService(PassengerRepository repository) {
        super(repository);
    }
}
