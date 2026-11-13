package com.lokesh.springsecurity.controller;

import com.lokesh.springsecurity.model.Loans;
import com.lokesh.springsecurity.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoanRepository loanRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoans(@RequestParam long customerId) {
        return loanRepository.findByCustomerIdOrderByStartDtDesc(customerId);
    }
}
