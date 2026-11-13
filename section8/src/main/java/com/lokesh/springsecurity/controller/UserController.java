package com.lokesh.springsecurity.controller;

import com.lokesh.springsecurity.model.Customer;
import com.lokesh.springsecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.sql.Date;
import java.util.Optional;

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
        customer.setCreateDt(new Date(System.currentTimeMillis()));
        Customer savedCustomer = customerRepository.save(customer);

        if (savedCustomer.getId() > 0){
            return ResponseEntity.status(HttpStatus.CREATED).body("Given user details are successfully registered.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed.");
        }
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }

    @GetMapping("/user1")
    public ResponseEntity<Authentication> getUsername1() {
//        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/user2")
    public ResponseEntity<Authentication> getUsername2(Authentication authentication) {
//        return ResponseEntity.ok(authentication.getName());
        return ResponseEntity.ok(authentication);
    }

    @GetMapping("/user3")
    public ResponseEntity<Principal> getUsername3(Principal principal) {
//        return ResponseEntity.ok(principal.getName());
        return ResponseEntity.ok(principal);
    }
}