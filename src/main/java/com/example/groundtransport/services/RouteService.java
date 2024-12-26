package com.example.groundtransport.services;

import com.example.groundtransport.entity.Route;
import com.example.groundtransport.repository.BaseRepository;
import com.example.groundtransport.repository.RouteRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RouteService extends BaseService<Route, Long> {
    public RouteService(RouteRepository repository) {
        super(repository);
    }
}