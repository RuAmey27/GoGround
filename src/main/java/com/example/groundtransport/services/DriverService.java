package com.example.groundtransport.services;

import com.example.groundtransport.entity.Driver;
import com.example.groundtransport.services.BaseService ;
import com.example.groundtransport.repository.DriverRepository;
import org.springframework.stereotype.Service;

@Service
public class DriverService extends BaseService<Driver, Long> {
    public DriverService(DriverRepository repository) {
        super( repository);
    }
}
