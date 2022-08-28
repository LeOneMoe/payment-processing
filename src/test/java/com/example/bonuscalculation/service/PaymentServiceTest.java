package com.example.bonuscalculation.service;

import com.example.bonuscalculation.constant.PaymentType;
import com.example.bonuscalculation.repository.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PaymentServiceTest {
    @Autowired
    PaymentService paymentService;

    @Autowired
    AccountService accountService;

    @Test
    void testPayment_1() {
        Account.restartInstance();

        performPaymentTest(
                PaymentType.ONLINE.toString(),
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(4900),
                BigDecimal.valueOf(17)
        );
    }

    @Test
    void testPayment_2() {
        Account.restartInstance();

        performPaymentTest(
                PaymentType.SHOP.toString(),
                BigDecimal.valueOf(120),
                BigDecimal.valueOf(4880),
                BigDecimal.valueOf(12)
        );
    }

    @Test
    void testPayment_3() {
        Account.restartInstance();

        performPaymentTest(
                PaymentType.ONLINE.toString(),
                BigDecimal.valueOf(301),
                BigDecimal.valueOf(4699),
                BigDecimal.valueOf(90.30)
        );
    }

    @Test
    void testPayment_4() {
        Account.restartInstance();

        performPaymentTest(
                PaymentType.ONLINE.toString(),
                BigDecimal.valueOf(17),
                BigDecimal.valueOf(4984.70),
                BigDecimal.valueOf(0)
        );
    }

    @Test
    void testPayment_5() {
        Account.restartInstance();

        performPaymentTest(
                PaymentType.SHOP.toString(),
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(4000),
                BigDecimal.valueOf(100)
        );
    }

    @Test
    void testPayment_6() {
        Account.restartInstance();

        performPaymentTest(
                PaymentType.ONLINE.toString(),
                BigDecimal.valueOf(21),
                BigDecimal.valueOf(4979),
                BigDecimal.valueOf(3.57)
        );
    }

    @Test
    void testPayment_7() {
        Account.restartInstance();

        performPaymentTest(
                PaymentType.SHOP.toString(),
                BigDecimal.valueOf(570),
                BigDecimal.valueOf(4430),
                BigDecimal.valueOf(57)
        );
    }
    
    @Test
    void testPayment_8() {
        Account.restartInstance();

        performPaymentTest(
                PaymentType.ONLINE.toString(),
                BigDecimal.valueOf(700),
                BigDecimal.valueOf(4300),
                BigDecimal.valueOf(210)
        );
    }

    void performPaymentTest(String type, BigDecimal paymentSum, BigDecimal expectedFunds, BigDecimal expectedBonus) {
        paymentService.pay(type, paymentSum);
        assertEquals(expectedFunds.setScale(2, RoundingMode.FLOOR), accountService.getFunds(), "Wrong funds balance");
        assertEquals(expectedBonus.setScale(2, RoundingMode.FLOOR), accountService.getBonus(), "Wrong bonus balance");
    }
}
