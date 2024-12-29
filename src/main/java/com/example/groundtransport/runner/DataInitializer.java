package com.example.groundtransport.runner;



import com.example.groundtransport.entity.Role;
import com.example.groundtransport.entity.User;
import com.example.groundtransport.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
    //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setEmail("admin@example.com");
            admin.setName("admin");
            admin.setPassword("adminpassword");
            admin.setRole(Role.valueOf("ADMIN"));

            User user = new User();
            user.setEmail("user@example.com");
            user.setName("user");
            user.setPassword("userpassword");
            user.setRole(Role.valueOf("USER"));

            userRepository.save(admin);
            userRepository.save(user);
        }
    }
}
