package com.example.groundtransport.repository;

import com.example.groundtransport.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> { }

