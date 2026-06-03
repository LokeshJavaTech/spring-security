package com.lokesh.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String getBalance() {
        return "These are the balance details from DB";
    }
}
