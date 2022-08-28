package com.example.bonuscalculation.controller;

import com.example.bonuscalculation.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> pay(
            @PathVariable String type,
            @PathVariable BigDecimal amount
    ) {
        try {
            paymentService.pay(type, amount);
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("failed to process payment: " + e.getMessage());
        }

        return ResponseEntity.ok("payment success");
    }
}
