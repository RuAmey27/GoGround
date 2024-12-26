package com.example.groundtransport.services;

import com.example.groundtransport.entity.Admin;
import com.example.groundtransport.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService extends BaseService<Admin, Long> {
    public AdminService(AdminRepository repository) {
        super(repository);
    }
}
