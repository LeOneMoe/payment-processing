package com.example.bonuscalculation.service;

import com.example.bonuscalculation.repository.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {
    public BigDecimal getBonus() {
        return Account.getInstance().getBonus();
    }

    public BigDecimal getFunds() {
        return Account.getInstance().getFunds();
    }
}
