//package com.example.groundtransport.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers("/api/admin/**").permitAll()
//                .requestMatchers("/api/driver/**").permitAll()
//                .requestMatchers("/api/passengers/**").permitAll()
//                .requestMatchers("/api/route/**").permitAll()
//                .requestMatchers("/api/trip/**").permitAll()
//                .requestMatchers("/api/vehicle/**").permitAll()
//                .requestMatchers("/api/users/**").authenticated()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic() // Using HTTP Basic Authentication
//                .and()
//                .csrf().disable(); // CSRF can be disabled for API scenarios
//
//        return http.build(); // return the security configuration
//    }
//
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
