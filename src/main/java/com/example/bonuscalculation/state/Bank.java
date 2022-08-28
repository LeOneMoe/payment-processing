package com.example.bonuscalculation.state;

import com.example.bonuscalculation.constant.PaymentType;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public class Bank implements PaymentState {


    @Override
    public void next(PaymentContext payment) {

        BigDecimal bonusPercent = getBonusPercent(
                payment.getPaymentType(),
                payment.getPaymentSum()
        );

        BigDecimal bonusFunds = payment.getPaymentSum().multiply(bonusPercent);

        payment.getAccount().addBonus(bonusFunds);

        log.info("Client got {}% bonus: {}", bonusPercent.multiply(new BigDecimal(100)), bonusFunds);

    }

    private BigDecimal getBonusPercent(PaymentType paymentType, BigDecimal paymentSum) {
        return switch (paymentType) {
            case SHOP -> new BigDecimal("0.10");
            case ONLINE -> new BigDecimal(paymentSum.compareTo(new BigDecimal(300)) > 0 ? "0.30" : "0.17");
        };
    }
}
