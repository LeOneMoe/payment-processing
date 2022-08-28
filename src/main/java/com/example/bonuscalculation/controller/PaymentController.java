package com.example.bonuscalculation.controller;

import com.example.bonuscalculation.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/payment/{type}/{amount}")
    public void pay(
            @PathVariable String type,
            @PathVariable BigDecimal amount
    ) {
        paymentService.pay(type, amount);
    }
}
