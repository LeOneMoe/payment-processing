package com.example.bonuscalculation.controller;

import com.example.bonuscalculation.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

// рестовый контроллер для получения данных о счете
// маппинг контроллера: api
// di с помощью lombok
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    // получение бонуса
    @GetMapping("/bankAccountOfEMoney")
    public BigDecimal getBonus() {
        return accountService.getBonus();
    }

    // получение средств
    @GetMapping("/money")
    public BigDecimal getFunds() {
        return accountService.getFunds();
    }
}
