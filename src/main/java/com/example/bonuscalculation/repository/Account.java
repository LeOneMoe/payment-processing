package com.example.bonuscalculation.repository;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.RoundingMode;

// тестовый репозиторий счета клиента
@Getter
@Setter
@ToString
public class Account {
    private static Account instance;

    // как я поняд по условию, нал/безнал не разделяется
    private BigDecimal funds;
    private BigDecimal bonus;

    public Account() {
        // тестовые значения из услолвия
        this.funds = new BigDecimal(5000).setScale(2, RoundingMode.FLOOR);
        this.bonus = new BigDecimal(0).setScale(2, RoundingMode.FLOOR);
    }

    public static Account getInstance() {
        if (instance == null) {
            instance = new Account();
        }
        return instance;
    }

    // так как тестовая репа - это singleton, нужен костыль для тестов
    public static void restartInstance() {
        instance = new Account();
    }

    public void addBonus(BigDecimal sum) {
        bonus = bonus.add(sum);
    }

    public void withdrawFunds(BigDecimal sum) {
        funds = funds.subtract(sum);
    }

    public void addFunds(BigDecimal sum) {
        funds = funds.add(sum);
    }
}
