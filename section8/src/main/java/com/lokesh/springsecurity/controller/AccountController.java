package com.lokesh.springsecurity.controller;

import com.lokesh.springsecurity.model.Accounts;
import com.lokesh.springsecurity.repository.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public Accounts getAccount(@RequestParam long customerId) {
        return accountsRepository.findByCustomerId(customerId);
    }
}
