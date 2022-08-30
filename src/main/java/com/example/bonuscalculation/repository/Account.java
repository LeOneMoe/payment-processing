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
    // используем BigDecimal чтобы не терять копейки
    private BigDecimal funds;
    private BigDecimal bonus;

    public Account() {
        // тестовые значения из услолвия
        // округляем вниз так как мы жадный банк
        this.funds = new BigDecimal(5000).setScale(2, RoundingMode.FLOOR);
        this.bonus = new BigDecimal(0).setScale(2, RoundingMode.FLOOR);
    }

    // полуяение инстанса singleton
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

    // метод добавления бонуса
    public void addBonus(BigDecimal sum) {
        bonus = bonus.add(sum);
    }

    // метод списания средств
    public void withdrawFunds(BigDecimal sum) {
        funds = funds.subtract(sum);
    }

    // метод пополнения средств
    public void addFunds(BigDecimal sum) {
        funds = funds.add(sum);
    }
}
