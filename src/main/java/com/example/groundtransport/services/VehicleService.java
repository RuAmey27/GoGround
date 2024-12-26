package com.example.groundtransport.services;

import com.example.groundtransport.entity.Vehicle;
import com.example.groundtransport.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService extends BaseService<Vehicle, Long> {
    public VehicleService(VehicleRepository repository) {
        super(repository);
    }
}
