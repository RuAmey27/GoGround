package com.example.groundtransport.controller;

import com.example.groundtransport.entity.Admin;
import com.example.groundtransport.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//need to implement the validation
// to implement put methods correctly any put method should not update any unwanted field should only be able to
// change specific parameter
//
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.create(admin);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Admin> getAdminById(@PathVariable Long id) {
        return adminService.findById(id);
    }
// can refine this put method to update various field based on url path
    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        admin.setName(admin.getName());
        admin.setEmail(admin.getEmail());


        return adminService.update(id, admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        adminService.delete(id);
    }
}
