package com.example.groundtransport.services;

import com.example.groundtransport.entity.User;
import com.example.groundtransport.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}
