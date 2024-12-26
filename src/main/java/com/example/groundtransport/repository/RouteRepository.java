package com.example.groundtransport.repository;

import com.example.groundtransport.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends BaseRepository<Route, Long> {
}