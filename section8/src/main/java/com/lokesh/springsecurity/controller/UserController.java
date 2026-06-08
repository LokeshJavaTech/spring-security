package com.lokesh.springsecurity.controller;

import com.lokesh.springsecurity.model.Customer;
import com.lokesh.springsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;     // We have already created its bean in ProjectSecurityConfig

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Customer customer)
    {
        String hashedPwd = passwordEncoder.encode(customer.getPwd());
        customer.setPwd(hashedPwd);
        customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully with username: "+customer.getEmail());
    }
}