package com.example.groundtransport.services;

import com.example.groundtransport.entity.Admin;
import com.example.groundtransport.repository.AdminRepository;
import org.springframework.stereotype.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Create a new Admin
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }

    // Retrieve all Admins
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    // Retrieve an Admin by ID
    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    // Update an existing Admin
    public Admin update(Long id, Admin updatedAdmin) {
        return adminRepository.findById(id).map(admin -> {
            admin.setName(updatedAdmin.getName());
            admin.setEmail(updatedAdmin.getEmail());
            admin.setPassword(updatedAdmin.getPassword());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    // Delete an Admin by ID
    public void delete(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
        } else {
            throw new RuntimeException("Admin not found with id: " + id);
        }
    }
}


