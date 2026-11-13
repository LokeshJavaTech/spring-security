package com.lokesh.springsecurity.controller;

import com.lokesh.springsecurity.model.Cards;
import com.lokesh.springsecurity.repository.CardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardsController {

    private final CardsRepository cardsRepository;

    @GetMapping("/myCards")
    public List<Cards> getCards(@RequestParam long customerId) {
        return cardsRepository.findByCustomerId(customerId);
    }
}
