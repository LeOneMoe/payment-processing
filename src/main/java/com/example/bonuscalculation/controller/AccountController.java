package com.example.bonuscalculation.controller;

import com.example.bonuscalculation.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/bankAccountOfEMoney")
    public BigDecimal getBonus() {
        return accountService.getBonus();
    }

    @GetMapping("/money")
    public BigDecimal getFunds() {
        return accountService.getFunds();
    }
}
