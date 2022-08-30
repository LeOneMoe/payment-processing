package com.example.bonuscalculation.controller;

import com.example.bonuscalculation.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

// рестовый контроллер для обработки платежей
// маппинг контроллера: api
// di с помощью lombok
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    // эндпоинт с двумя параметрами пути для обработки платежа
    @GetMapping("/payment/{type}/{amount}")
    public ResponseEntity<?> pay(
            @PathVariable String type,
            @PathVariable BigDecimal amount
    ) {
        // проверяем на ошибку
        try {
            paymentService.pay(type, amount);
        } catch (RuntimeException e) {
            // отдаем 500 код с сообщением ошибки
            return ResponseEntity.internalServerError().body("failed to process payment: " + e.getMessage());
        }

        // отдаем 200 с сообщением об успешном платеже
        return ResponseEntity.ok("payment success");
    }
}
