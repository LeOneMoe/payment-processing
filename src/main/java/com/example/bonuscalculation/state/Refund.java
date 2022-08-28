package com.example.bonuscalculation.state;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class Refund implements PaymentState {

    @Override
    public void next(PaymentContext payment) {
        BigDecimal refund = payment.getPaymentSum().multiply(new BigDecimal("0.10"));

        payment.getAccount().addFunds(refund);

        payment.setState(new Bank());

        log.info("Client got 10.00% refund: {}", refund);
    }

}
